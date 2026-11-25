package com.companyname.springapp.business.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTests {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testSetAndGetDescription() {
        String testDescription = "pruebaDescription";
        assertNull(product.getDescription());
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());
    }

    @Test
    void testSetAndGetPrice() {
        double testPrice = 150.00;
        assertNull(product.getPrice());
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);
    }
}