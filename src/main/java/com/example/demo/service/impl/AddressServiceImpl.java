package com.example.demo.service.impl;

import com.example.demo.model.Address;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends AbstractBasicServiceImpl implements AddressService {

    @Override
    public ResponseEntity addNewAddress(Address address, Integer id_user) {
        ResponseEntity entity = new ResponseEntity();
        try {
            entity.setData(addressDao.saveNew(address, id_user));
        } catch (Exception e){
            e.printStackTrace();
            entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return entity;
    }
}
