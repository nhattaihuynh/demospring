package com.example.demo.dao.impl;

import com.example.demo.dao.AbstractDaoImpl;
import com.example.demo.dao.CartItemDao;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.model.CartItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl extends AbstractDaoImpl<CartItem, Integer> implements CartItemDao {

    @Override
    public void addNewCartItemForUser(CartItemDTO item, Integer id_user) {
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            CartItem cartItem = new CartItem();
            cartItem.setQuantity(item.getQuantity());
            cartItem.setBook(bookDao.findById(item.getIdBook()));
            cartItem.setCart(userDao.findById(id_user).getCart());

            session.save(cartItem);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
