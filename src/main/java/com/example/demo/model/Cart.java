package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer totalBooks;

    private Double totalPrice;

    private Double saveAmount;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    @JsonIgnore
    @OneToOne(mappedBy = "cart")
    private User user;

    public Cart(){
        this.totalBooks = 0;
        this.totalPrice = 0.0;
        this.saveAmount = 0.0;
    }
}
