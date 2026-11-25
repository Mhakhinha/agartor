package com.companyname.springapp.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companyname.springapp.business.entities.Product;
import com.companyname.springapp.business.repositories.ProductRepository;

@Service
public class JPAProductManager implements ProductManager {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void increasePrice(int percentage) {
        List<Product> products = (List<Product>) productRepository.findAll();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * (100 + percentage) / 100;
                product.setPrice(newPrice);
                productRepository.save(product);
            }
        }
    }
}