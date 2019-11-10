package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDao extends AbstractDao<User, Integer> {

    void addNewUser(User user);
}
