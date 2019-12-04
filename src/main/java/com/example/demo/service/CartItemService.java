package com.example.demo.service;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.response.ResponseEntity;

public interface CartItemService {

    ResponseEntity addNewCartItemForUser(CartItemDTO cartItem, Integer id_user);

    ResponseEntity updateCartItem(CartItemDTO cartItem);

    ResponseEntity deleteCartItem(Integer cart_item_id);
}
