package com.shopsphere.service;

import com.shopsphere.model.*;
import com.shopsphere.repository.CartRepository;
import com.shopsphere.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    // ADD TO CART
    public Cart addToCart(
            String userId,
            String productId,
            int quantity
    ) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart());

        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<>());
        }

        CartItem item = new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity,
                product.getImageUrl()
        );

        cart.getProducts().add(item);

        cart.setUserId(userId);

        double total = cart.getProducts()
                .stream()
                .mapToDouble(i ->
                        i.getPrice() * i.getQuantity())
                .sum();

        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }

    // GET CART
    public Cart getCart(String userId) {

        return cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));
    }

    // REMOVE ITEM
    public Cart removeFromCart(
            String userId,
            String productId
    ) {

        Cart cart = getCart(userId);

        cart.getProducts().removeIf(
                item -> item.getProductId().equals(productId)
        );

        double total = cart.getProducts()
                .stream()
                .mapToDouble(i ->
                        i.getPrice() * i.getQuantity())
                .sum();

        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }
}