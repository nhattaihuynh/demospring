package com.example.demo.dao;

import java.util.List;

public interface AbstractDao<T, V> {

    T save(T o);

    T findById(V o);

    List<T> findAll();

    void deleteById(V o);

    void delete(T o) ;

}
