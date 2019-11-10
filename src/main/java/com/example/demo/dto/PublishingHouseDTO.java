package com.example.demo.dto;

import com.example.demo.model.Book;
import com.example.demo.model.PublishingHouse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PublishingHouseDTO {
    private String name;
    private String introduce;
    private List<Book> listBook;

    public PublishingHouseDTO(){
    }
    public PublishingHouseDTO(PublishingHouse house){
        this.name = house.getName();
        this.introduce = house.getIntroduce();
        listBook = new ArrayList<>();
    }
}
