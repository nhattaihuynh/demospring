package com.example.demo.dao.impl;

import com.example.demo.model.User;
import com.example.demo.dao.UserDao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao {

}
