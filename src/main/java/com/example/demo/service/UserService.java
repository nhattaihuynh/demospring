package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.response.ResponseEntity;

public interface UserService {

    ResponseEntity addNewUser(User user);

    User getUserByToken(String token);
}
