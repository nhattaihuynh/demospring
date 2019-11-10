package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends AbstractBasicServiceImpl implements BookService {

    @Override
    public ResponseEntity findAll(){
        ResponseEntity response = new ResponseEntity();
        response.setData(bookDao.findAll());
        return response;
    }

    @Override
    public ResponseEntity save(Book book) {
        ResponseEntity response = new ResponseEntity();
        response.setData(bookDao.save(book));
        return response;
    }
}
