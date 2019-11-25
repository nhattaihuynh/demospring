package com.example.demo.dao.impl;

import com.example.demo.dao.CartItemDao;
import com.example.demo.model.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl extends AbstractDaoImpl<CartItem, Integer> implements CartItemDao {

}
