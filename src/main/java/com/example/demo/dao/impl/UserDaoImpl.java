package com.example.demo.dao.impl;

import com.example.demo.dao.AbstractDaoImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Cart;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao {

    @Override
    public void addNewUser(User user) {
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Cart cart = new Cart();
            user.setCart(cart);
            session.save(user);
            tx.commit();
        } catch (Exception e){
            throw e;
        } finally {
            session.close();
        }
    }
}
