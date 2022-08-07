package com.example.demo.service.impl;

import com.example.demo.Utils.HashUtils;
import com.example.demo.constant.CommonConstant;
import com.example.demo.model.BookBuyLater;
import com.example.demo.model.Cart;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.pk.BookBuyLaterPK;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl extends AbstractBasicServiceImpl implements UserService {

    @Override
    public ResponseEntity addNewUser(User user) {
    	log.debug("");
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

    @Override
    public ResponseEntity deleteBookBuyLater(HashMap<String, Integer> params) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = context.getCurrentUser();
            Integer bookId = params.get("ids");

            BookBuyLaterPK pk = new BookBuyLaterPK();
            pk.setUserId(user.getId());
            pk.setBookId(bookId);
            bookBuyLaterDao.deleteById(pk, session);
            //delete cart-item
            Integer cartItemId = params.get("cartItemId");
            cartItemDao.deleteById(cartItemId, session);

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
    public ResponseEntity getBookBuyLater(Integer page, Integer numberItem) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            List<BookBuyLater> list;
            User user = context.getCurrentUser();
            Integer userId = user.getId();
            String sqlCount = "SELECT COUNT(*) FROM book_buy_later WHERE user_id=:userId";

            NativeQuery queryCount = session.createNativeQuery(sqlCount);

            queryCount.setParameter("userId", userId);

            Integer totalItems = ((BigInteger) queryCount.uniqueResult()).intValue();

            String sql = "SELECT * FROM book_buy_later WHERE user_id=:userId LIMIT :start, :num";

            NativeQuery query = session.createNativeQuery(sql, BookBuyLater.class);

            query.setParameter("userId", userId);
            query.setParameter("start", page - 1);
            query.setParameter("num", page * numberItem);

            list = (List<BookBuyLater>) query.list();

            entity.setTotalItems(totalItems);
            entity.setData(list);

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
	public User saveUser(User user) {
		log.info("Saving user {} to database", user);
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving role {} to database", role);
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {} from database", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all user from database");
		return userRepo.findAll();
	}
}
