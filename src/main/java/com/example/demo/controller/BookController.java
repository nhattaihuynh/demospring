package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity save(@RequestBody Book book){
        return bookService.save(book);
    }

    @GetMapping("/history/{sku}")
    public ResponseEntity getBookHistory(@PathVariable String sku){
        return bookService.getBookHistory(sku);
    }

    @PostMapping("/get-history")
    public ResponseEntity getBookHistoryByOrder(@RequestBody HashMap<String, Object> params){
        return bookService.getBookHistoryByOrder(params);
    }
}
