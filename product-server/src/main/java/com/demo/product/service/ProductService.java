package com.demo.product.service;

import com.demo.product.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> productList();

    Product findById(int id);

    void addProduct(Product product);
}
