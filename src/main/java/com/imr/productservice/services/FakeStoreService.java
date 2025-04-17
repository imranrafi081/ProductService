package com.imr.productservice.services;

import com.imr.productservice.dtos.FakeProductResponseDTO;
import com.imr.productservice.dtos.ProductResponseDTO;
import com.imr.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
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

    public List<Product> getAllProducts() {
        FakeProductResponseDTO[] fakeProductResponseDTO = restTemplate.getForObject("https://fakestoreapi.com/products", FakeProductResponseDTO[].class);
        List <Product> products = new ArrayList<>();
        for(FakeProductResponseDTO response : fakeProductResponseDTO) {
            Product product = response.toProduct();
            products.add(product);
        }
        return products;
    }
}
