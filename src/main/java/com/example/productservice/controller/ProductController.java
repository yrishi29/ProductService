package com.example.productservice.controller;


import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.service.Productservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {

    private final Productservice productService;

    @PostMapping("/add")
    public void createProduct(@RequestBody ProductRequest productRequest ){
        productService.createProduct(productRequest);
    }

    @GetMapping("getall")
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProducts();

    }

}
