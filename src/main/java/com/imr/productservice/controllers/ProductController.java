package com.imr.productservice.controllers;

import com.imr.productservice.commons.AuthenticationCommons;
import com.imr.productservice.dtos.CreateFakeStoreProductDTO;
import com.imr.productservice.dtos.ProductRequestDto;
import com.imr.productservice.dtos.ProductResponseDTO;
import com.imr.productservice.dtos.UserDto;
import com.imr.productservice.models.Product;
import com.imr.productservice.models.Role;
import com.imr.productservice.services.FakeStoreService;
import com.imr.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    RestTemplate restTemplate;
    ProductService productService ;
    AuthenticationCommons authenticationCommons;

    public ProductController(RestTemplate restTemplate, @Qualifier("productDBService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.restTemplate =restTemplate;
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @RequestMapping(value = "/products/{id}" ,method= RequestMethod.GET)
    public ProductResponseDTO getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        ProductResponseDTO responseDTO = ProductResponseDTO.from(product);
        return responseDTO;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@RequestBody ProductRequestDto productRequestDto) {
        String token = productRequestDto.getToken();
        UserDto userDto = authenticationCommons.validateUser(token);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Check if the user is an admin
        boolean isAdmin = false;
        for (Role role : userDto.getRoles()) {
            if (role.getName().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        // If the user is not an admin, return 401 Unauthorized
        if (!isAdmin) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<ProductResponseDTO> responseDTOs = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            ProductResponseDTO responseDTO = ProductResponseDTO.from(product);
            responseDTOs.add(responseDTO);
        }
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
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
