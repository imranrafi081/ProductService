package com.imr.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    String title;
    double price;
    String description;
    String image;
    String category;
    long id;
    String token;
}
