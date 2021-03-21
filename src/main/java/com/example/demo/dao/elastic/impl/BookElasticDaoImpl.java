package com.example.demo.dao.elastic.impl;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.elastic.BookElasticDao;
import com.example.demo.elastic.mapping.BookElastic;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Repository
public class BookElasticDaoImpl implements BookElasticDao {

	@Value("elasticsearch.index.name")
	private String indexName;
	
	@Value("elasticsearch.book.type")
	private String bookTypeName;
	
	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Override
	public List<BookElastic> getAllBooks() {
		SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
		return esTemplate.queryForList(query, BookElastic.class);
	}

	@Override
	public BookElastic saveBook(BookElastic book) {
		IndexQuery indexQuery = new IndexQuery();
		indexQuery.setIndexName(indexName);
		indexQuery.setType(bookTypeName);
		indexQuery.setObject(book);
		esTemplate.index(indexQuery);
		
		esTemplate.refresh(indexName);
		return book;
	}

	@Override
	public List<BookElastic> getBookByName(String name) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchQuery("nabookNameme", name))
				.build();
		return esTemplate.queryForList(searchQuery, BookElastic.class);
	}
	
	
}
