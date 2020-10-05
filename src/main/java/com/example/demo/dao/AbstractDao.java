package com.example.demo.dao;

import org.hibernate.Session;

import java.util.List;

public interface AbstractDao<T, V> {

    T save(T o);

    T findById(V o);

    T findById(V o, Session session);

    List<T> findAll();

    void deleteById(V o);

    void deleteById(V o, Session session);

    void delete(T o) ;
    
    void update(T t, Session session);
}
