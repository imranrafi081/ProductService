package com.imr.productservice.services;

import com.imr.productservice.dtos.CreateFakeStoreProductDTO;
import com.imr.productservice.dtos.FakeProductRequestDTO;
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

    @Override
    public Product createProduct(String name, String description,double price,String imageUrl, String category) {
        FakeProductRequestDTO fakeProductRequestDTO = new FakeProductRequestDTO();
        fakeProductRequestDTO.setTitle(name);
        fakeProductRequestDTO.setDescription(description);
        fakeProductRequestDTO.setPrice(price);
        fakeProductRequestDTO.setImage(imageUrl);
        fakeProductRequestDTO.setCategory(category);

        FakeProductResponseDTO responseDTO = restTemplate.postForObject("https://fakestoreapi.com/products", fakeProductRequestDTO, FakeProductResponseDTO.class);

        return responseDTO.toProduct();
    }
}
