package com.smartstore.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BillingApplicationTest {

    @Test
    void testCalculateDiscountBelow1000() {
        double discount = BillingApplication.calculateDiscount(800.0);

        assertEquals(0.0, discount, 0.001);
    }

    @Test
    void testCalculateDiscountAtExactly1000() {
        double discount = BillingApplication.calculateDiscount(1000.0);

        assertEquals(50.0, discount, 0.001);
    }

    @Test
    void testCalculateDiscountAt4999() {
        double discount = BillingApplication.calculateDiscount(4999.0);

        assertEquals(249.95, discount, 0.001);
    }

    @Test
    void testCalculateDiscountAtExactly5000() {
        double discount = BillingApplication.calculateDiscount(5000.0);

        assertEquals(500.0, discount, 0.001);
    }

    @Test
    void testCalculateDiscountAbove5000() {
        double discount = BillingApplication.calculateDiscount(7000.0);

        assertEquals(700.0, discount, 0.001);
    }
}