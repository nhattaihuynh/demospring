package com.example.demo.controller.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.elastic.mapping.BookElastic;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.elastic.BookElasticService;

@RestController
@RequestMapping(value = "/elastic/book", produces = "application/json; charset=utf-8")
public class TestBookElasticController {

	@Autowired
	private BookElasticService serviceMain;
	
    @PostMapping("/add/book-elastic")
    public ResponseEntity addNewBookElastic(@RequestBody BookElastic book){
        return serviceMain.saveBook(book);
    }
	
    @GetMapping("get-all-book-elastic")
    public ResponseEntity getAllBookElastic(){
        return serviceMain.getAllBooks();
    }
}
