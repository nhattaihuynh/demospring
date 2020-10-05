package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface BookService {

    ResponseEntity findAll();

    ResponseEntity save(Book book, MultipartFile img);

    ResponseEntity getBookHistory(String name);

    ResponseEntity getBookHistoryByOrder(HashMap<String, Object> params);

    ResponseEntity getBookHistoryByQuantityGT(Integer i);

    ResponseEntity getBookHistoryByNameFullTextSearch(HashMap<String, String> params);

    ResponseEntity uploadImageForBook(MultipartFile fileUpload, Integer bookId);

	ResponseEntity addQuestionForBook(HashMap<String, Object> params);

	ResponseEntity addAnswerForBook(HashMap<String, Object> params);
}
