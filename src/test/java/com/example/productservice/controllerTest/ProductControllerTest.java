package com.example.productservice.controllerTest;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Category;
import com.example.productservice.repositoryTest.TestH2Repository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProductControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository testH2Repository;

    @BeforeAll
    static void init() {

        restTemplate = new RestTemplate();

    }

    @BeforeEach
    public  void setup(){
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/product/");

    }

    @Test
    public void testAddProduct(){
        baseUrl = baseUrl.concat("add");
        ProductRequest productRequest = ProductRequest.builder().
                name("Book").price(BigDecimal.valueOf(3000)).description("A good book").category("Books").build();

        restTemplate.postForObject(baseUrl, productRequest, Void.class);
        assertEquals(1, testH2Repository.count());


    }

    @Test
    @Sql(statements = "INSERT INTO category (category_id, category_name) VALUES (1, 'Books')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO product (id, description, name, price, category_category_id)" +
            " VALUES ('1', 'A sample description', 'Sample Product', 100.00, 1)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

    public void testGetProduct(){
        baseUrl = baseUrl.concat("getall");

        List<ProductResponse> ls = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1, ls.size());
        assertEquals(1, testH2Repository.count());

    }
}


