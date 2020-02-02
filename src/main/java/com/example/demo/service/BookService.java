package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface BookService {

    ResponseEntity findAll();

    ResponseEntity save(Book book);

    ResponseEntity getBookHistory(String name);

    ResponseEntity getBookHistoryByOrder(HashMap<String, Object> params);

    ResponseEntity getBookHistoryByQuantityGT(Integer i);

    ResponseEntity getBookHistoryByNameFullTextSearch(HashMap<String, String> params);

    ResponseEntity uploadImageForBook(MultipartFile fileUpload, Integer bookId);
}
