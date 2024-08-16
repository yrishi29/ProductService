package com.example.productservice.dto;


import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequestMapping
@Builder

public class ProductRequest {

    String name;
    String description;
    BigDecimal price;
    String category;

    public static Product getProduct(ProductRequest productRequest, Category category) {

        return Product.builder().
                name(productRequest.getName()).
                description(productRequest.getDescription()).
                price(productRequest.getPrice()).
                category(category)
                .build();
    }

}


