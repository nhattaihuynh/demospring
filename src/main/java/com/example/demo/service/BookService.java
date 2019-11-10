package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;

public interface BookService {

    ResponseEntity findAll();

    ResponseEntity save(Book book);
}
