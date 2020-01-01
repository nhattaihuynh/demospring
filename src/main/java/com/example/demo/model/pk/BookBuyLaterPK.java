package com.example.demo.model.pk;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class BookBuyLaterPK implements Serializable {

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "user_id")
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBuyLaterPK that = (BookBuyLaterPK) o;
        return bookId == that.bookId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, userId);
    }

}
