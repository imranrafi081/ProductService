package com.imr.productservice.repositories;

import com.imr.productservice.models.Category;
import com.imr.productservice.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findByName(String name);
    List<Product> findAll();
    List<Product> findByCategory_ProductTypeLike(String category);
    @Query("Select p from Product p where p.category.productType=:productType")
    List<Product> findProductByProductType(@Param("productType") String type);
}
