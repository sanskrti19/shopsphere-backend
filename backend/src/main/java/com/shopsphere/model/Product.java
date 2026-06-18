package com.shopsphere.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private double price;

    private String category;

    private String brand;

    private int stock;

    private String imageUrl;

    private double rating;

    private LocalDateTime createdAt;
}