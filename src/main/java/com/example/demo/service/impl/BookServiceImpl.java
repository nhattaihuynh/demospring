package com.example.demo.service.impl;

import com.example.common.entity.mongodb.BookHistory;
import com.example.common.entity.mongodb.QBookHistory;
import com.example.demo.model.Book;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.BookService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Service
public class BookServiceImpl extends AbstractBasicServiceImpl implements BookService {

    @Override
    public ResponseEntity findAll() {
        ResponseEntity response = new ResponseEntity();
        try {
            response.setData(bookDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }

    @Override
    public ResponseEntity save(Book book) {
        ResponseEntity response = new ResponseEntity();
        try {
            Book bookReturn = bookDao.save(book);

            BookHistory history = new BookHistory();
            history.setQuantity(bookReturn.getQuantity());
            history.setSku(bookReturn.getSku());
            history.setCreatedDate(new Date());
            history.setAction("CREATE");
            history.setEntity(Book.class.toString());
            history.setBookName(bookReturn.getName());
//            mongoTemplate.insert(history);

            response.setData("book_id: " + bookReturn.getId());
//            mongoTemplate.remove(new Query(Criteria.where("").));
//            mongoTemplate.dropCollection("");
//            mongoTemplate.insertAll(null);
            mongoBookHisDao.insert(history);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }

    @Override
    public ResponseEntity getBookHistory(String sku) {
        ResponseEntity response = new ResponseEntity();
        try {
            Collection<BookHistory> history = mongoBookHisDao.getAllBySku(sku);
            response.setData(history);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }

    @Override
    public ResponseEntity getBookHistoryByOrder(HashMap<String, Object> params) {
        ResponseEntity response = new ResponseEntity();
        try {
            String order = (String) params.get("order");
            if (null == order) {
                response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
                response.setCode(HTTPStatus.SERVER_ERROR.getCode());
                return response;
            }
            Sort sort;
            if (order.equals("ascending")) {
                sort = Sort.by("createdDate").ascending();
            } else {
                sort = Sort.by("createdDate").descending();
            }

            Collection<BookHistory> history = mongoBookHisDao.findAll(sort);
            response.setData(history);

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }

    @Override
    public ResponseEntity getBookHistoryByQuantityGT(Integer quantity) {
        ResponseEntity response = new ResponseEntity();
        try {

            QBookHistory query = new QBookHistory("query");
            Predicate quantityFilter = query.quantity.gt(quantity);
            Collection<BookHistory> history = (Collection<BookHistory>) mongoBookHisDao.findAll(quantityFilter);
            response.setData(history);
            response.setTotalItems((int) mongoBookHisDao.count(quantityFilter));

        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return response;
    }


}
