package com.example.demo.model;

import com.example.demo.model.pk.BookBuyLaterPK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBuyLater that = (BookBuyLater) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
