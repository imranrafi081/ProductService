package com.imr.productservice.controllers;

import com.imr.productservice.dtos.ProductResponseDTO;
import com.imr.productservice.models.Product;
import com.imr.productservice.services.FakeStoreService;
import com.imr.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;

@RestController
public class ProductController {
    @RequestMapping(value = "/products/{id}" ,method= RequestMethod.GET)
    public ProductResponseDTO getProductById(@PathVariable("id") long id) {
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreService fakeStoreService = new FakeStoreService(restTemplate);
        Product product = fakeStoreService.getProductById(id);
        ProductResponseDTO responseDTO = ProductResponseDTO.from(product);
        return responseDTO;
    }
}
