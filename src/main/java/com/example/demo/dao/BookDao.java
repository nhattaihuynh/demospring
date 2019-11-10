package com.example.demo.dao;

import com.example.demo.model.Book;

public interface BookDao extends AbstractDao<Book, Integer> {

//    public List<BookDTO> findAll(){
//        Session session = sessionFactory.openSession();
//        NativeQuery query = session.createNativeQuery("SELECT name, author, yearPublish FROM book");
//        query.addScalar("name", StandardBasicTypes.STRING);
//        query.addScalar("author", StandardBasicTypes.STRING);
//        query.addScalar("yearPublish", StandardBasicTypes.INTEGER);
//        query.setResultTransformer(Transformers.aliasToBean(BookDTO.class));
//        List<BookDTO> list = query.list();
//        session.close();
//        return list;
//    }

}
