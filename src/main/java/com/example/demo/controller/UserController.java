package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-new")
    public ResponseEntity addNewUser(@RequestBody User user){
        return userService.addNewUser(user);
    }

}
