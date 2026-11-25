package com.companyname.springapp.business.services;

import java.util.List;

import com.companyname.springapp.business.entities.Product;

public interface ProductManager {

    void increasePrice(int percentage);

    List<Product> getProducts();
}