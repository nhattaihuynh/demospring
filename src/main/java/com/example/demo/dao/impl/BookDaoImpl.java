package com.example.demo.dao.impl;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookDaoImpl extends AbstractDaoImpl<Book, Integer> implements BookDao {

}
