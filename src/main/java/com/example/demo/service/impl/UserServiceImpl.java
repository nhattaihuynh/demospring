package com.example.demo.service.impl;

import com.example.demo.Utils.HashUtils;
import com.example.demo.constant.CommonConstant;
import com.example.demo.model.BookBuyLater;
import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.model.pk.BookBuyLaterPK;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
            if (user.getUsername() != null) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(user.getPass().getBytes());
                byte[] digest = md.digest();
                user.setToken(HashUtils.hash(user.getPass() + CommonConstant.hashMD5Token));
                StringBuffer sb = new StringBuffer();
                for (byte b : digest) {
                    sb.append(Integer.toHexString((int) (b & 0xff)));
                }
                user.setPass(sb.toString());
            }
            user.setIsActive(true);
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

    @Override
    public User getUserByToken(String token) {
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT * FROM user WHERE token=:token";
            NativeQuery query = session.createNativeQuery(sql, User.class);
            query.setParameter("token", token);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public ResponseEntity addBookBuyLater(HashMap<String, List<Integer>> params) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = context.getCurrentUser();
            List<Integer> listBookID = params.get("ids");
            Date date = new Date();
            BookBuyLater bbl;
            BookBuyLaterPK pk;
            for (Integer i : listBookID) {
                bbl = new BookBuyLater();
                bbl.setCreatedDate(date);
                bbl.setUpdatedDate(date);

                pk = new BookBuyLaterPK();
                pk.setUserId(user.getId());
                pk.setBookId(i);
                bbl.setId(pk);
                bookBuyLaterDao.save(bbl);
            }

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
