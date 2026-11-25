package com.companyname.springapp.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.companyname.springapp.business.entities.Product;

class SimpleProductManagerTests {

    private SimpleProductManager productManager;
    private List<Product> products;

    private static final int PRODUCT_COUNT = 2;
    private static final int POSITIVE_PRICE_INCREASE = 10;

    private static final Double CHAIR_PRICE = Double.valueOf(20.50);
    private static final String CHAIR_DESCRIPTION = "Chair";

    private static final String TABLE_DESCRIPTION = "Table";
    private static final Double TABLE_PRICE = Double.valueOf(150.10);

    @BeforeEach
    void setUp() {
        productManager = new SimpleProductManager();
        products = new ArrayList<>();

        Product product = new Product();
        product.setDescription(CHAIR_DESCRIPTION);
        product.setPrice(CHAIR_PRICE);
        products.add(product);

        product = new Product();
        product.setDescription(TABLE_DESCRIPTION);
        product.setPrice(TABLE_PRICE);
        products.add(product);

        productManager.setProducts(products);
    }

    @Test
    void testGetProductsWithNoProducts() {
        productManager = new SimpleProductManager();
        assertNull(productManager.getProducts());
    }

    @Test
    void testGetProducts() {
        List<Product> products = productManager.getProducts();
        assertNotNull(products);
        assertEquals(PRODUCT_COUNT, products.size());

        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());

        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());
    }

    @Test
    void testIncreasePriceWithNullListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch (NullPointerException ex) {
            fail("Products list is null.");
        }
    }

    @Test
    void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProducts(new ArrayList<Product>());
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch (Exception ex) {
            fail("Products list is empty.");
        }
    }

    @Test
    void testIncreasePriceWithPositivePercentage() {
        productManager.increasePrice(POSITIVE_PRICE_INCREASE);

        List<Product> products = productManager.getProducts();
        assertEquals(22.55, products.get(0).getPrice(), 0.001);
        assertEquals(165.11, products.get(1).getPrice(), 0.001);
    }
}