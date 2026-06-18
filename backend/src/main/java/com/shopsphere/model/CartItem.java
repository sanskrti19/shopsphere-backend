package com.shopsphere.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    private String productId;

    private String name;

    private double price;

    private int quantity;

    private String imageUrl;
}