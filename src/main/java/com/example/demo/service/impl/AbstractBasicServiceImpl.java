package com.example.demo.service.impl;

import com.example.demo.authentication.AppScopeContext;
import com.example.demo.dao.*;
import com.example.demo.service.AbstractService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractBasicServiceImpl implements AbstractService {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected AppScopeContext context;

    @Autowired
    @Lazy
    protected BookDao bookDao;

    @Autowired
    @Lazy
    protected MongoTemplate mongoTemplate;

    @Autowired
    @Lazy
    protected PublishHouseDao publishHouseDao;

    @Autowired
    @Lazy
    protected RatingDao ratingDao;

    @Autowired
    @Lazy
    protected AddressDao addressDao;

    @Autowired
    @Lazy
    protected UserDao userDao;

    @Autowired
    @Lazy
    protected CartItemDao cartItemDao;

    @Autowired
    @Lazy
    protected BookBuyLaterDao bookBuyLaterDao;
}
