package com.example.demo.controller;

import com.example.demo.response.ResponseEntity;
import com.example.demo.service.PublishHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/publish-house", produces = "application/json; charset=utf-8")
public class PublishHouseController {

    @Autowired
    private PublishHouseService publishHouseService;

    @GetMapping("/findAllBooks/{publish_house_id}")
    public ResponseEntity findAllBooks(@PathVariable("publish_house_id") Integer id) {
        return publishHouseService.findAllBooks(id);
    }


}
