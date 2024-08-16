package com.example.productservice.service;


import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@Slf4j
@Builder
@AllArgsConstructor


public class Productservice {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;



    public void createProduct(ProductRequest productRequest) {

        Category category = categoryService.findOrCreateCategory(productRequest.getCategory());
        Product product = ProductRequest.getProduct(productRequest,category);

        productRepository.save(product);
        log.info("Product {} created", product.getId());

    }

    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::mapToProductResponse).toList();

    }


}
