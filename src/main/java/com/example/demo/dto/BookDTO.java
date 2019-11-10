package com.example.demo.dto;

import lombok.Data;

@Data
public class BookDTO {
//    private Integer id;
    private String name;
    private String author;
    private Integer yearPublish;
    public BookDTO(){

    }
    public BookDTO(String name, String author, Integer yearPublish) {
        this.name = name;
        this.author = author;
        this.yearPublish = yearPublish;
    }
}
