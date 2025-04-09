package com.imr.productservice.services;

import com.imr.productservice.dtos.FakeProductResponseDTO;
import com.imr.productservice.dtos.ProductResponseDTO;
import com.imr.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public class FakeStoreService implements ProductService {
    RestTemplate restTemplate ;
    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        FakeProductResponseDTO fakeProductResponseDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeProductResponseDTO.class);
        return fakeProductResponseDTO.toProduct();
    }
}
