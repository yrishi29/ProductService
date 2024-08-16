package com.example.productservice.serviceTest;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.CategoryService;
import com.example.productservice.service.Productservice;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ProductserviceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private Productservice productService;


    @BeforeEach
    void setUp() {
        // Initialize mocks and inject them into the productService
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createProductTest(){

        // Arrange
        Category mockCategory = new Category();
        mockCategory.setCategoryName("Sports");

        ProductRequest productRequest = new ProductRequest(
                "Football",
                "A standard football",
                BigDecimal.valueOf(29.99),
                "Sports"
        );

        // Mock the behavior of categoryService.findOrCreateCategory
        when(categoryService.findOrCreateCategory("Sports")).thenReturn(mockCategory);
        productService.createProduct(productRequest);
        Product p = ProductRequest.getProduct(productRequest,mockCategory);

        when(productRepository.save(p)).thenReturn(p);
        assertEquals("Football", p.getName());
        assertEquals("A standard football", p.getDescription());
        assertEquals(BigDecimal.valueOf(29.99), p.getPrice());
        assertEquals(mockCategory.getCategoryName(), p.getCategory().getCategoryName());


    }

    @Test
    void getAllProductsTest(){

        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Football");
        product1.setDescription("A standard football");
        product1.setPrice(BigDecimal.valueOf(29.99));
        product1.setCategory(new Category(1, "Sports"));

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Basketball");
        product2.setDescription("A standard basketball");
        product2.setPrice(BigDecimal.valueOf(19.99));
        product2.setCategory(new Category(2, "Sports"));


        List<Product> products = List.of(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductResponse> expectedResponses = products.stream()
                .map(ProductResponse::mapToProductResponse)
                .collect(Collectors.toList());

        List<ProductResponse> actualResponses = productService.getAllProducts();
        assertEquals(expectedResponses, actualResponses);




    }

    @AfterAll
    static void afterAll() {

        System.out.println("All test cases are executed");
    }
}