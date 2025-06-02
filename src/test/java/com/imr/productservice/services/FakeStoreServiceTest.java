package com.imr.productservice.services;

import com.imr.productservice.dtos.FakeProductRequestDTO;
import com.imr.productservice.dtos.FakeProductResponseDTO;
import com.imr.productservice.models.Product;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class FakeStoreServiceTest {
    @MockitoBean
    RestTemplate restTemplate;
    @Autowired
    FakeStoreService fakeStoreService;

    @Test
    public void testProductByIdreturnsProduct() {
        //when(restTemplate.getForObject("https://fakestoreapi.com/products/1", FakeProductResponseDTO.class))
               // .thenReturn();
    }

    @Test
    public void testCreateProductReturnsProductWithId()
    {
        FakeProductResponseDTO dummyResponse = new FakeProductResponseDTO();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(1.0);
        dummyResponse.setImage("img.url");
        dummyResponse.setCategory("category");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeProductResponseDTO.class))).thenReturn(dummyResponse);

        Product product = fakeStoreService.createProduct("title", "description", 12.1,
                "img.url", "category");

        assertEquals(1L, product.getId());
        assertEquals("title", product.getName());
    }
}