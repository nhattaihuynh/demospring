package com.example.demo.service.impl;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.AddressService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends AbstractBasicServiceImpl implements AddressService {

    @Override
    public ResponseEntity addNewAddress(Address address, Integer id_user) {
        ResponseEntity entity = new ResponseEntity();
        Session session = null;
        Transaction tx;
        User user = context.getCurrentUser();
        System.out.println(user);

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            address.setUser(userDao.findById(id_user));
            session.save(address);

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
