package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "publish_house")
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String introduce;

    @OneToMany(mappedBy = "house")
    @JsonIgnore
    private Set<Book> booksByHouse;

}
