package com.example.demo.service.impl;

import com.example.demo.dao.*;
import com.example.demo.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AbstractBasicServiceImpl implements AbstractService {

    @Autowired
    @Lazy
    protected BookDao bookDao;
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
}
