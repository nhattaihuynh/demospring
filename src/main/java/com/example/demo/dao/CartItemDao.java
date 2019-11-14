package com.example.demo.dao;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.model.CartItem;

public interface CartItemDao extends AbstractDao<CartItem, Integer> {

    void updateCartItem(CartItemDTO cartItem);
}
