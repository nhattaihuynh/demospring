package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDTO {

    private Integer id;
    private Integer quantity;
    private Integer idBook;

    public CartItemDTO() {

    }

}
