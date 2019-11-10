package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "bookauthors", joinColumns = {@JoinColumn(name = "id_book")},
    inverseJoinColumns = {@JoinColumn(name = "id_author")})
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Rating> ratings = new HashSet<>();

    private String yearPublish;
    private String size;
    private Boolean isHardCover;
    private Double realPrice;
    private Double paperPrice;
    private String savePercent;
    private Integer quantity;
    private Short totalPages;
    private String introduce;
    private String sku;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "id_publish_house", nullable = false)
    private PublishingHouse house;

    private Double ratingScore;

    @JsonIgnore
    @OneToOne(mappedBy = "book")
    private CartItem cartItem;

    public Book() {
    }

}
