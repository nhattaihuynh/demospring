package com.example.demo.dao.impl;

import com.example.demo.dao.AbstractDaoImpl;
import com.example.demo.dao.AddressDao;
import com.example.demo.model.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<Address, Integer> implements AddressDao {

    @Override
    public Address saveNew(Address address, Integer id_user) {
        Session session = null;
        Transaction tx;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            address.setUser(userDao.findById(id_user));
            session.save(address);

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }

        return address;
    }
}
