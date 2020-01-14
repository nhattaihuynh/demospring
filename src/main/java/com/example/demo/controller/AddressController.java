package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address-user", produces = "application/json; charset=utf-8")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add-new/{id_user}")
    public ResponseEntity save(@RequestBody Address address, @PathVariable("id_user") Integer id_user) {
        return addressService.addNewAddress(address, id_user);
    }
}
