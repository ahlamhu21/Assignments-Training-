package com.smartstore.billing;

import java.util.Scanner;

public class BillingApplication {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Accept customer and purchase details
        System.out.print("Enter customer name: ");
        String customerName = inputScanner.nextLine();

        System.out.print("Enter product name: ");
        String productName = inputScanner.nextLine();

        System.out.print("Enter product price: Rs. ");
        double productPrice = inputScanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = inputScanner.nextInt();

        // Calculate purchase and discount values
        double totalAmount = productPrice * quantity;
        double discountAmount = calculateDiscount(totalAmount);
        double discountPercentage = calculateDiscountPercentage(totalAmount);
        double finalAmount = totalAmount - discountAmount;

        printBill(customerName, productName, totalAmount,
                discountPercentage, discountAmount, finalAmount);

        inputScanner.close();
    }

    // Calculates the discount amount in rupees
    public static double calculateDiscount(double totalAmount) {
        double discountPercentage = calculateDiscountPercentage(totalAmount);
        return totalAmount * discountPercentage / 100.0;
    }

    // Determines the discount percentage based on the purchase amount
    public static double calculateDiscountPercentage(double totalAmount) {
        if (totalAmount >= 5000.0) {
            return 10.0;
        } else if (totalAmount >= 1000.0) {
            return 5.0;
        } else {
            return 0.0;
        }
    }

    // Prints all customer and billing details
    public static void printBill(String customerName, String productName,
            double totalAmount, double discountPercentage,
            double discountAmount, double finalAmount) {

        System.out.println("\n--- SmartStore Bill ---");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Product Name: " + productName);
        System.out.printf("Total Amount: Rs. %.2f%n", totalAmount);
        System.out.printf("Discount Percentage: %.2f%%%n", discountPercentage);
        System.out.printf("Discount Amount: Rs. %.2f%n", discountAmount);
        System.out.printf("Final Amount: Rs. %.2f%n", finalAmount);
    }
}