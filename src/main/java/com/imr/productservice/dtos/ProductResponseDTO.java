package com.imr.productservice.dtos;

import com.imr.productservice.models.Category;
import com.imr.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private long id;
    private String productName;
    private double price;
    private String description;
    private String imageURL;
    private String category;

    public static ProductResponseDTO from(Product product)
    {
        ProductResponseDTO productResponseDto = new ProductResponseDTO();
        productResponseDto.setId(product.getId());
        productResponseDto.setProductName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImageURL(product.getImage());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setCategory(product.getCategory().getProductType());

        return productResponseDto;
    }
}
