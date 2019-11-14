package com.example.demo.dao.impl;

import com.example.demo.dao.AbstractDaoImpl;
import com.example.demo.dao.CartItemDao;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.model.CartItem;
import com.example.demo.response.HTTPStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl extends AbstractDaoImpl<CartItem, Integer> implements CartItemDao {

    @Override
    public void updateCartItem(CartItemDTO cartItem) {
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            CartItem item = new CartItem();
            item.setId(cartItem.getId());
            item.setBook(bookDao.findById(cartItem.getIdBook()));
            item.setQuantity(cartItem.getQuantity());

            session.saveOrUpdate(item);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
