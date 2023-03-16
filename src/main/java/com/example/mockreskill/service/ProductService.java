package com.example.mockreskill.service;

import com.example.mockreskill.model.entity.Product;
import com.example.mockreskill.model.request.CreateProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(String id);
    void createProduct(CreateProductRequest createProductRequest);
    void deleteProduct(String id);
    void updateProduct(CreateProductRequest createProductRequest, String id);
}
