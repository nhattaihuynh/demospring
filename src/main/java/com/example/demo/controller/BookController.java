package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/book", produces = "application/json; charset=utf-8")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/findAll")
    public ResponseEntity findAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestPart("book") Book book, @RequestPart("file") MultipartFile img) {
        return bookService.save(book, img);
    }

    @GetMapping("/history/{sku}")
    public ResponseEntity getBookHistory(@PathVariable String sku) {
        return bookService.getBookHistory(sku);
    }

    @PostMapping("/get-history")
    public ResponseEntity getBookHistoryByOrder(@RequestBody HashMap<String, Object> params) {
        return bookService.getBookHistoryByOrder(params);
    }

    @GetMapping("/get-history-by-order/{quantity}")
    public ResponseEntity getBookHistoryByQuantityGT(@PathVariable Integer quantity) {
        return bookService.getBookHistoryByQuantityGT(quantity);
    }

    @PostMapping("/get-history-by-name")
    public ResponseEntity getBookHistoryByNameFullTextSearch(@RequestBody HashMap<String, String> params) {
        return bookService.getBookHistoryByNameFullTextSearch(params);
    }

    @PostMapping("/upload/{bookId}")
    public ResponseEntity uploadImageForBook(@RequestParam("file") MultipartFile fileUpload, @PathVariable ("bookId") Integer bookId) {
        return bookService.uploadImageForBook(fileUpload, bookId);
    }
    
    @PostMapping("/add-question-for-book")
    public ResponseEntity addQuestionForBook(@RequestBody HashMap<String, Object> params) {
        return bookService.addQuestionForBook(params);
    }
}
