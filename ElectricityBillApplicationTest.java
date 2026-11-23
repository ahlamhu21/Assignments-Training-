package com.citypower.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ElectricityBillApplicationTest {

    // Checks the first slab and the start of the second slab.
    @Test
    void testEnergyChargeForFirstSlabAndBoundary() {
        assertEquals(0.00, ElectricityBillApplication.calculateEnergyCharge(0), 0.001);
        assertEquals(240.00, ElectricityBillApplication.calculateEnergyCharge(80), 0.001);
        assertEquals(300.00, ElectricityBillApplication.calculateEnergyCharge(100), 0.001);
        assertEquals(305.00, ElectricityBillApplication.calculateEnergyCharge(101), 0.001);
    }

    // Checks the end of the second slab.
    @Test
    void testEnergyChargeAt300Units() {
        assertEquals(1300.00, ElectricityBillApplication.calculateEnergyCharge(300), 0.001);
    }

    // Checks the start of the third slab.
    @Test
    void testEnergyChargeAt301Units() {
        assertEquals(1307.50, ElectricityBillApplication.calculateEnergyCharge(301), 0.001);
    }

    // Checks units just below the surcharge threshold.
    @Test
    void testEnergyChargeAt393Units() {
        assertEquals(1997.50, ElectricityBillApplication.calculateEnergyCharge(393), 0.001);
    }

    // Checks the boundary and first units above the surcharge threshold.
    @Test
    void testEnergyChargeAt394And400Units() {
        assertEquals(2005.00, ElectricityBillApplication.calculateEnergyCharge(394), 0.001);
        assertEquals(2050.00, ElectricityBillApplication.calculateEnergyCharge(400), 0.001);
    }

    // Checks that surcharge is zero below and exactly at Rs. 2,000.
    @Test
    void testSurchargeBelowAndAtThreshold() {
        assertEquals(0.00, ElectricityBillApplication.calculateSurcharge(1997.50), 0.001);
        assertEquals(0.00, ElectricityBillApplication.calculateSurcharge(2000.00), 0.001);
    }

    // Checks the 5 percent surcharge above the threshold.
    @Test
    void testSurchargeAboveThreshold() {
        assertEquals(100.25, ElectricityBillApplication.calculateSurcharge(2005.00), 0.001);
        assertEquals(102.50, ElectricityBillApplication.calculateSurcharge(2050.00), 0.001);
    }

    // Checks totals with no surcharge and with a surcharge.
    @Test
    void testCalculateTotal() {
        assertEquals(50.00, ElectricityBillApplication.calculateTotal(0.00), 0.001);
        assertEquals(1350.00, ElectricityBillApplication.calculateTotal(1300.00), 0.001);
        assertEquals(2202.50, ElectricityBillApplication.calculateTotal(2050.00), 0.001);
    }
}
