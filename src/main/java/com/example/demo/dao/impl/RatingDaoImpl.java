package com.example.demo.dao.impl;

import com.example.demo.Utils.NumberUtils;
import com.example.demo.dao.AbstractDaoImpl;
import com.example.demo.dao.RatingDao;
import com.example.demo.dto.RatingDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Rating;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class RatingDaoImpl extends AbstractDaoImpl<Rating, Integer> implements RatingDao {

    @Override
    public Rating addNewRating(RatingDTO dto, Integer id_book) {
        Rating rating = new Rating();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            rating.setComment(dto.getComment());
            rating.setScore(dto.getScore());
            rating.setTitle(dto.getTitle());
            Book book = session.find(Book.class, id_book);
            rating.setBook(book);
            Date current = new Date();
            rating.setCreatedDate(current);
            rating.setUpdatedDate(current);
            session.save(rating);
            //tính toán average rating score
            Double average = -1.0;
            String sql = "SELECT avg(score) as average FROM rating where id_book =:id_book";
            NativeQuery query = session.createNativeQuery(sql);
            query.setParameter("id_book", id_book);
            query.addScalar("average", StandardBasicTypes.DOUBLE);
            try {
                average = (Double) query.uniqueResult();
            } catch (Exception e){
//                average = 0.0;
            }
            average = NumberUtils.round(average, 1);
            book.setRatingScore(average);
            session.saveOrUpdate(book);

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

        return rating;
    }
}
