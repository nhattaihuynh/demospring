package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.PublishHouseService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishHouseServiceImpl extends AbstractBasicServiceImpl implements PublishHouseService {
    @Override
    public ResponseEntity findAllBooks(Integer id) {
        ResponseEntity response = new ResponseEntity();
        Session session = null;
        List<Book> list = null;
        try {
            session = sessionFactory.openSession();
            NativeQuery query = session.createNativeQuery("SELECT * FROM book WHERE id_publish_house =:id_publish_house", Book.class);
            query.setParameter("id_publish_house", id);
            list = (List<Book>) query.list();

            response.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            response.setCode(HTTPStatus.SERVER_ERROR.getCode());
        } finally {
            session.close();
        }
        return response;
    }
}
