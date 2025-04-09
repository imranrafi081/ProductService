package com.imr.productservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private double price;
    private String description;
    private String image;
    private Category category;
}
