package com.example.demo.dao;

import com.example.demo.model.Address;

public interface AddressDao extends AbstractDao<Address, Integer> {

    Address saveNew(Address address, Integer id_user);
}
