package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-new")
    public ResponseEntity addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @PostMapping("/save-book-buy-later")
    public ResponseEntity addBookBuyLater(@RequestBody HashMap<String, List<Integer>> params) {
        return userService.addBookBuyLater(params);
    }

    @PostMapping("/del-book-buy-later")
    public ResponseEntity deleteBookBuyLater(@RequestBody HashMap<String, Integer> params) {
        return userService.deleteBookBuyLater(params);
    }

    @GetMapping("/get-book-buy-later")
    public ResponseEntity getBookBuyLater() {
        return userService.getBookBuyLater();
    }

}