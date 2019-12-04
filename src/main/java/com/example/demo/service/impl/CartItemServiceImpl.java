package com.example.demo.service.impl;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.model.CartItem;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.CartItemService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl extends AbstractBasicServiceImpl implements CartItemService {

    @Override
    public ResponseEntity addNewCartItemForUser(CartItemDTO cartDto, Integer id_user) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            NativeQuery query = session.createNativeQuery("SELECT * FROM cart_item WHERE id_book =:id_book", CartItem.class);
            query.setParameter("id_book", cartDto.getIdBook());
            List<CartItem> cartItems = query.list();
            if (cartItems.size() > 0) {
                entity.setCode(HTTPStatus.CONFLICT.getCode());
                entity.setMessage(HTTPStatus.CONFLICT.getMessage());
                return entity;
            }
            CartItem cartItem = new CartItem();

            cartItem.setQuantity(cartDto.getQuantity());
            cartItem.setBook(bookDao.findById(cartDto.getIdBook()));
            cartItem.setCart(userDao.findById(id_user).getCart());

            session.save(cartItem);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return entity;
    }

    @Override
    public ResponseEntity updateCartItem(CartItemDTO cartDto) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            NativeQuery query = session.createNativeQuery("SELECT * FROM cart_item WHERE id_book =:id_book", CartItem.class);
            query.setParameter("id_book", cartDto.getIdBook());
            List<CartItem> cartItems = query.list();
            if (cartItems.size() == 0) {
                entity.setCode(HTTPStatus.DATA_NOT_FOUND.getCode());
                entity.setMessage(HTTPStatus.DATA_NOT_FOUND.getMessage());
                return entity;
            }

            CartItem item = cartItems.get(0);
            item.setBook(bookDao.findById(cartDto.getIdBook()));
            item.setQuantity(cartDto.getQuantity());

            session.update(item);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return entity;
    }

    @Override
    public ResponseEntity deleteCartItem(Integer cart_item_id) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            CartItem item = session.find(CartItem.class, cart_item_id);
            session.delete(item);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return entity;
    }
}
