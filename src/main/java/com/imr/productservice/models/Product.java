package com.imr.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private double price;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
}
