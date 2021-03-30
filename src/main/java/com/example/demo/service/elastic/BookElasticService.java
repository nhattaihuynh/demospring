package com.example.demo.service.elastic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.elastic.BookElasticDao;
import com.example.demo.elastic.mapping.BookElastic;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;

@Service
public class BookElasticService {

	private BookElasticDao bookDao;

	public ResponseEntity getAllBooks() {
		ResponseEntity entity = new ResponseEntity();
		List<BookElastic> list = new ArrayList<>();
		try {
			list = bookDao.getAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
			entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
			entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
		}
		entity.setData(list);
		return entity;
	}

	public ResponseEntity saveBook(BookElastic book) {
		ResponseEntity entity = new ResponseEntity();
		try {
			 bookDao.saveBook(book);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
			entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
		}
		return entity;
	}

	public ResponseEntity getBookByName(String name) {
		ResponseEntity entity = new ResponseEntity();
		List<BookElastic> list = new ArrayList<>();
		try {
			list = bookDao.getBookByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			entity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
			entity.setCode(HTTPStatus.SERVER_ERROR.getCode());
		}
		entity.setData(list);
		return entity;
	}

}
