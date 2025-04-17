package com.imr.productservice.controllers;

import com.imr.productservice.dtos.CreateFakeStoreProductDTO;
import com.imr.productservice.dtos.ProductResponseDTO;
import com.imr.productservice.models.Product;
import com.imr.productservice.services.FakeStoreService;
import com.imr.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    //private final FakeStoreService fakeStoreService;
    RestTemplate restTemplate ;
    ProductService productService ;

    public ProductController(RestTemplate restTemplate, @Qualifier("productDBService") ProductService productService, FakeStoreService fakeStoreService) {
        this.restTemplate =restTemplate;
        this.productService = productService;
        //this.fakeStoreService = fakeStoreService;
    }

    @RequestMapping(value = "/products/{id}" ,method= RequestMethod.GET)
    public ProductResponseDTO getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        ProductResponseDTO responseDTO = ProductResponseDTO.from(product);
        return responseDTO;
    }

    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> responseDTOs = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            ProductResponseDTO responseDTO = ProductResponseDTO.from(product);
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }

    @PostMapping("/products")
    public  ProductResponseDTO createProduct(@RequestBody CreateFakeStoreProductDTO createFakeStoreProductDTO) {
        Product product = productService.createProduct(createFakeStoreProductDTO.getName(),
                                                            createFakeStoreProductDTO.getDescription(),
                                                            createFakeStoreProductDTO.getPrice(),
                                                            createFakeStoreProductDTO.getImageUrl(),
                                                            createFakeStoreProductDTO.getCategory());
        ProductResponseDTO responseDTO = ProductResponseDTO.from(product);
        return responseDTO;
    }
}
