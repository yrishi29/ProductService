package com.example.productservice.repositoryTest;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    private Product product;
    private Category category;


    @BeforeEach
    void setUp() {

        category = new Category();
        category.setCategoryName("Sports");

        product = new Product();
        product.setName("Football");
        product.setDescription("Professional Football");
        product.setPrice(BigDecimal.valueOf(29.99));
        product.setCategory(category);

        categoryRepository.save(category);
        productRepository.save(product);

    }

    @AfterEach
    void tearDown() {
        product = null;
        category = null;
        productRepository.deleteAll();
    }

    @Test
    void testfindBycategoryName() {

        Optional<Category> category = categoryRepository.findBycategoryName("Sports");
        assertTrue(category.isPresent(), "Category should be found");
        assertTrue(category.get().getCategoryName().equals("Sports"), "Category name should be Sports");

    }
}
