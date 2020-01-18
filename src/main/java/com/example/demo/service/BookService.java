package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;

import java.util.HashMap;

public interface BookService {

    ResponseEntity findAll();

    ResponseEntity save(Book book);

    ResponseEntity getBookHistory(String name);

    ResponseEntity getBookHistoryByOrder(HashMap<String, Object> params);

    ResponseEntity getBookHistoryByQuantityGT(Integer i);
}
