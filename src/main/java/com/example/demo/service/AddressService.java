package com.example.demo.service;

import com.example.demo.model.Address;
import com.example.demo.response.ResponseEntity;

public interface AddressService {

    ResponseEntity addNewAddress(Address address, Integer id_user);

}
