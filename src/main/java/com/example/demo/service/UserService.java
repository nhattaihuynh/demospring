package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.response.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    ResponseEntity addNewUser(User user);

    User getUserByToken(String token);

    ResponseEntity addBookBuyLater(HashMap<String, List<Integer>> params);

    ResponseEntity deleteBookBuyLater(HashMap<String, Integer> params);

    ResponseEntity getBookBuyLater(Integer page, Integer numberItem);
    
    User saveUser(User user);
    
    Role saveRole(Role role);
    
    void addRoleToUser(String username, String roleName);
    
    User getUser(String username);
    
    List<User> getUsers();
}
