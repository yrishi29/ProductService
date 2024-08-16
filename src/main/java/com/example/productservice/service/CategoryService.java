package com.example.productservice.service;

import com.example.productservice.model.Category;
import com.example.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Find or create a category based on its name

    public Category findOrCreateCategory(String categoryName) {
        // Try to find the category by name
        return categoryRepository.findBycategoryName(categoryName)
                .orElseGet(() -> {
                    // If not found, create a new category
                    Category newCategory = new Category();
                    newCategory.setCategoryName(categoryName);
                    return categoryRepository.save(newCategory);
                });
    }
}

