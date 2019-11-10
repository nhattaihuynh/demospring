package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_book")
    private Book book;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_cart")
    @JsonIgnore
    private Cart cart;

}
