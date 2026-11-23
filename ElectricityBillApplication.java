package com.citypower.billing;

import java.util.Scanner;

public class ElectricityBillApplication {

    // Named constants for the electricity billing rules
    public static final int FIRST_SLAB_UNITS = 100;
    public static final int SECOND_SLAB_UNITS = 300;
    public static final double FIRST_SLAB_RATE = 3.00;
    public static final double SECOND_SLAB_RATE = 5.00;
    public static final double THIRD_SLAB_RATE = 7.50;
    public static final double FIXED_CHARGE = 50.00;
    public static final double SURCHARGE_THRESHOLD = 2000.00;
    public static final double SURCHARGE_RATE = 0.05;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Ask for the consumer details
        System.out.print("Enter consumer number: ");
        String consumerNumber = inputScanner.nextLine();

        // nextLine() allows the consumer name to contain spaces
        System.out.print("Enter consumer name: ");
        String consumerName = inputScanner.nextLine();

        System.out.print("Enter previous meter reading: ");
        int previousReading = inputScanner.nextInt();

        System.out.print("Enter current meter reading: ");
        int currentReading = inputScanner.nextInt();

        // Stop if the current reading is smaller than the previous reading
        if (currentReading < previousReading) {
            System.out.println("Invalid readings: current reading cannot be less than previous");
            inputScanner.close();
            return;
        }

        // Calculate the number of units consumed
        int units = currentReading - previousReading;

        // Perform calculations using separate methods
        double energyCharge = calculateEnergyCharge(units);
        double surcharge = calculateSurcharge(energyCharge);
        double totalAmount = calculateTotal(energyCharge);

        printBill(consumerNumber, consumerName, units, energyCharge,
                surcharge, totalAmount);

        inputScanner.close();
    }

    // Calculates the energy charge using separate progressive slabs
    public static double calculateEnergyCharge(int units) {
        double energyCharge = 0.0;

        if (units <= FIRST_SLAB_UNITS) {
            energyCharge = units * FIRST_SLAB_RATE;
        } else if (units <= SECOND_SLAB_UNITS) {
            int remainingUnits = units - FIRST_SLAB_UNITS;
            energyCharge = (FIRST_SLAB_UNITS * FIRST_SLAB_RATE)
                         + (remainingUnits * SECOND_SLAB_RATE);
        } else {
            int unitsAboveSecondSlab = units - SECOND_SLAB_UNITS;
            energyCharge = (FIRST_SLAB_UNITS * FIRST_SLAB_RATE)
                         + ((SECOND_SLAB_UNITS - FIRST_SLAB_UNITS)
                         * SECOND_SLAB_RATE)
                         + (unitsAboveSecondSlab * THIRD_SLAB_RATE);
        }

        return energyCharge;
    }

    // Calculates a 5 percent surcharge only above Rs. 2,000
    public static double calculateSurcharge(double energyCharge) {
        if (energyCharge > SURCHARGE_THRESHOLD) {
            return energyCharge * SURCHARGE_RATE;
        }

        return 0.0;
    }

    // Calculates the fixed charge, surcharge, and final total
    public static double calculateTotal(double energyCharge) {
        double surcharge = calculateSurcharge(energyCharge);
        return energyCharge + FIXED_CHARGE + surcharge;
    }

    // Prints all consumer and bill details
    public static void printBill(String consumerNumber, String consumerName,
            int units, double energyCharge, double surcharge,
            double totalAmount) {

        System.out.println("\nCityPower Electricity Bill");
        System.out.println("Consumer Number: " + consumerNumber);
        System.out.println("Consumer Name: " + consumerName);
        System.out.println("Units Consumed: " + units);
        System.out.printf("Energy Charge: Rs. %.2f%n", energyCharge);
        System.out.printf("Fixed Charge: Rs. %.2f%n", FIXED_CHARGE);
        System.out.printf("Surcharge: Rs. %.2f%n", surcharge);
        System.out.printf("Total Amount: Rs. %.2f%n", totalAmount);
    }
}