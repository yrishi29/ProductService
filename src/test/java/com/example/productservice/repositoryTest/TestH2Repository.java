package com.example.productservice.repositoryTest;

import com.example.productservice.model.Product;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;


@DataJpaTest
public interface TestH2Repository extends JpaRepository<Product, String> {




}
