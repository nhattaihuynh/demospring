package com.example.demo.service.impl;

import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.PublishHouseService;
import org.springframework.stereotype.Service;

@Service
public class PublishHouseServiceImpl extends AbstractBasicServiceImpl implements PublishHouseService {
    @Override
    public ResponseEntity findAllBooks(Integer id) {
        ResponseEntity response = new ResponseEntity();
        try {
            response.setData(publishHouseDao.findAllBooks(id));
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }
}
