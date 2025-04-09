package com.imr.productservice.dtos;

import com.imr.productservice.models.Category;
import com.imr.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductResponseDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setName(title);
        product.setPrice(price);
        product.setImage(image);

        Category category1 = new Category();
        category1.setProductType(category);

        product.setCategory(category1);
        return product;
    }
}
