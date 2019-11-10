package com.example.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDaoImpl<T, V extends Serializable> implements AbstractDao<T, V> {
    @Autowired
    @Lazy
    protected BookDao bookDao;
    @Autowired
    @Lazy
    protected PublishHouseDao publishHouseDao;

    @Autowired
    @Lazy
    protected RatingDao ratingDao;

    @Autowired
    @Lazy
    protected AddressDao addressDao;

    @Autowired
    @Lazy
    protected UserDao userDao;
    @Autowired
    protected SessionFactory sessionFactory;

    private final Class<T> clazz;

    public AbstractDaoImpl(){
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T o) {
        Session session = null;
        Transaction tran = null;
        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();
            session.save(o);
            tran.commit();
        } catch (Exception e){
            e.printStackTrace();
            tran.rollback();
        } finally {
            session.close();
        }
        return o;
    }

    @Override
    public T findById(V o) {
        Session session = null;
        Transaction tran = null;
        T t = null;
        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();
            t = (T) session.get(clazz.getName(), o);
            tran.commit();
        } catch (Exception e){
            e.printStackTrace();
            tran.rollback();
        } finally {
            session.close();
        }
        return t;
    }

    @Override
    public List<T> findAll() {
        Session session = null;
        List<T> list = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from " + clazz.getName());
            list = query.list();
        } catch (Exception e){
            e.printStackTrace();

        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void deleteById(V o) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.delete(findById(o));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(T o) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.delete(o);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}