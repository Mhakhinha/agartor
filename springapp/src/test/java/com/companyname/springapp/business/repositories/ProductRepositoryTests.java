package com.companyname.springapp.business.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.companyname.springapp.business.SpringappBusinessConfig;
import com.companyname.springapp.business.entities.Product;

@SpringJUnitConfig(classes = { SpringappBusinessConfig.class })
class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testGetProductList() {
        List<Product> products = (List<Product>) productRepository.findAll();
        assertEquals(3, products.size());
    }

    @Test
    void testSaveProduct() {
        List<Product> products = (List<Product>) productRepository.findAll();

        Product p = products.get(0);
        Double price = p.getPrice();
        p.setPrice(200.12);
        productRepository.save(p);

        List<Product> updatedProducts = (List<Product>) productRepository.findAll();
        Product p2 = updatedProducts.get(0);
        assertEquals(200.12, p2.getPrice(), 0.001);

        p2.setPrice(price);
        productRepository.save(p2);
    }
}