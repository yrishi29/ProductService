package com.example.productservice.dto;

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

public class ProductResponse{

    private String id;
    private String name;
    private String description;
    private BigDecimal price;

   public static ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder().
                id(product.getId()).
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).build();

    }


}
