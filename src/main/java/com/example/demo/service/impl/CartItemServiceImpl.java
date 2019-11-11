package com.example.demo.service.impl;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl extends AbstractBasicServiceImpl implements CartItemService {

    @Override
    public ResponseEntity addNewCartItemForUser(CartItemDTO cartItem, Integer id_user) {
        ResponseEntity entity = new ResponseEntity();
        try {
            cartItemDao.addNewCartItemForUser(cartItem, id_user);
        } catch (Exception e) {
            e.printStackTrace();
            entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return entity;
    }
}
