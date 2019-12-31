package com.example.demo.model;

import com.example.demo.model.pk.BookBuyLaterPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "book_buy_later")
public class BookBuyLater {

    @EmbeddedId
    private BookBuyLaterPK id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

}
