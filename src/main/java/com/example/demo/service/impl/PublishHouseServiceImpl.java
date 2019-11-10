package com.example.demo.service.impl;

import com.example.demo.response.ResponseEntity;
import com.example.demo.service.PublishHouseService;
import org.springframework.stereotype.Service;

@Service
public class PublishHouseServiceImpl extends AbstractBasicServiceImpl implements PublishHouseService {
    @Override
    public ResponseEntity findAllBooks(Integer id) {
        ResponseEntity response = new ResponseEntity();
        response.setData(publishHouseDao.findAllBooks(id));
        return response;
    }
}
