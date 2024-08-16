package com.example.productservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;


    @ManyToOne(cascade = CascadeType.REMOVE)  //many product in one category
    private Category category;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }


}

