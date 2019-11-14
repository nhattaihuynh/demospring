package com.example.demo.controller;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart-item", produces = "application/json; charset=utf-8")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add/{id_user}")
    public ResponseEntity addNewItem(@RequestBody CartItemDTO cartItem, @PathVariable("id_user") Integer id_user){
        return cartItemService.addNewCartItemForUser(cartItem, id_user);
    }

    @PutMapping("/add/{id_user}")
    public ResponseEntity updateItem(@RequestBody CartItemDTO cartItem, @PathVariable("id_user") Integer id_user){
        return cartItemService.addNewCartItemForUser(cartItem, id_user);
    }

}
