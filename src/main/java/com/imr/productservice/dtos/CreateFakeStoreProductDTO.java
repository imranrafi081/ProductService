package com.imr.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFakeStoreProductDTO {
    private String name;
    private String description;
    private String category;
    private double price;
    private String imageUrl;
}
