package com.example.demo.service.impl;

import com.example.demo.Utils.NumberUtils;
import com.example.demo.dto.RatingDTO;
import com.example.demo.model.Book;
import com.example.demo.model.Rating;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.RatingService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RatingServiceImpl extends AbstractBasicServiceImpl implements RatingService {

    @Override
    public ResponseEntity addNewRating(RatingDTO dto, Integer id_book) {
        ResponseEntity responseEntity = new ResponseEntity();
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
            } catch (Exception e) {
//                average = 0.0;
            }
            average = NumberUtils.round(average, 1);
            book.setRatingScore(average);
            session.saveOrUpdate(book);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            responseEntity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        } finally {
            session.close();
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity calcRatingForBook(Integer id_book) {
        return null;
    }
}
