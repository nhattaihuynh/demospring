package com.example.demo.dao;

import com.example.demo.model.Book;

import java.util.List;

public interface PublishHouseDao {

    List<Book> findAllBooks(Integer id);
}
