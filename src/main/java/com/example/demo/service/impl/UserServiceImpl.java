package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractBasicServiceImpl implements UserService {

    @Override
    public ResponseEntity addNewUser(User user) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Cart cart = new Cart();
            user.setCart(cart);
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        } finally {
            session.close();
        }
        return entity;
    }
}
