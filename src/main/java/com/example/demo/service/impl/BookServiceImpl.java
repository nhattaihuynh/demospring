package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends AbstractBasicServiceImpl implements BookService {

    @Override
    public ResponseEntity findAll(){
        ResponseEntity response = new ResponseEntity();
        try {
            response.setData(bookDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }

    @Override
    public ResponseEntity save(Book book) {
        ResponseEntity response = new ResponseEntity();
        try {
            Book bookReturn = bookDao.save(book);
            response.setData("book_id: " + bookReturn.getId());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }
}
