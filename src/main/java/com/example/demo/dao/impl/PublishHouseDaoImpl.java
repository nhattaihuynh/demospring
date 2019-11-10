package com.example.demo.dao.impl;

import com.example.demo.dao.AbstractDaoImpl;
import com.example.demo.dao.PublishHouseDao;
import com.example.demo.model.Book;
import com.example.demo.model.PublishingHouse;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PublishHouseDaoImpl extends AbstractDaoImpl<PublishingHouse, Integer> implements PublishHouseDao {

    @Override
    public List<Book> findAllBooks(Integer id) {
        Session session = null;
        List<Book> list = null;
        try {
            session = sessionFactory.openSession();
            NativeQuery query = session.createNativeQuery("SELECT * FROM book WHERE id_publish_house =:id_publish_house", Book.class);
            query.setParameter("id_publish_house", id);
            list = (List<Book>) query.list();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
