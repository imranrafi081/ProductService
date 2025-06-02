package com.imr.productservice;

import com.imr.productservice.controllers.ProductController;
import com.imr.productservice.dtos.ProductResponseDTO;
import com.imr.productservice.exceptions.ProductNotFoundException;
import com.imr.productservice.models.Product;
import com.imr.productservice.models.Category;
import com.imr.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceApplicationTests {
    @MockitoBean
    @Qualifier("productDBService")
    ProductService productService;

    @Autowired
    ProductController productController;

    @Test
    public void testGetProductByIdreturnsProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setDescription("description");
        product.setPrice(1.0);
        product.setImage("image");

        Category dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyCategory.setProductType("category");
        //dummyCategory.se("description");

        product.setCategory(dummyCategory);


        when(productService.getProductById(1L)).thenReturn(product);

        Product returnedProduct = productService.getProductById(1L);

        assertEquals(1L, returnedProduct.getId());
    }

    @Test
    public void testGetProductByIdReturnsNull() throws ProductNotFoundException
    {
        when(productService.getProductById(1L)).thenReturn(null);

        ProductResponseDTO productResponseDto = productController.getProductById(1L);

        assertNull(productResponseDto);
    }

}
