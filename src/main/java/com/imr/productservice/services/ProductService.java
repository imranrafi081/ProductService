package com.imr.productservice.services;

import com.imr.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public Product createProduct(String name, String description,double price,String imageUrl, String category);
}
