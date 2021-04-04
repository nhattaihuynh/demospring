package com.example.demo.controller.elastic;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.elastic.mapping.BookElastic;

@RestController
@RequestMapping(value = "/elastic/book", produces = "application/json; charset=utf-8")
public class TestBookElasticController {

	@Autowired
	private Client client;

	@PostMapping("/create")
	public String create(@RequestBody BookElastic book) throws IOException {
		IndexResponse response = null;
		try {
		response = client.prepareIndex("store", "book", book.getId()).setSource(jsonBuilder()
				.startObject().field("name", book.getBookName()).endObject())
				.get();
		System.out.println("response id:" + response.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response.getResult().toString();
	}
	
	@GetMapping("/view/{id}")
	public Map<String, Object> view (@PathVariable final String id) {
		GetResponse clientGet = client.prepareGet("store", "book", id).get();
		return clientGet.getSource();
	}
	
	@GetMapping("view/name/{text}")
	public Map<String, Object> searchByName(@PathVariable final String text) {
		SearchResponse search = client.prepareSearch("store")
				.setTypes("book")
				.setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.matchQuery("bookName", text))
				.get();
		List<SearchHit> hits = Arrays.asList(search.getHits().getHits());
		
		return hits.get(0).getSourceAsMap();
	}
	
}
