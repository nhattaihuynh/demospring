package com.example.demo.dao.elastic;

import java.util.List;

import com.example.demo.elastic.mapping.BookElastic;

public interface BookElasticDao {

	List<BookElastic> getAllBooks();

	BookElastic saveBook(BookElastic book);

	List<BookElastic> getBookByName(String name);
}
