package com.example.demo.controller.elastic;

import java.io.IOException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.elastic.mapping.BookElastic;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
@RequestMapping(value = "/elastic/book", produces = "application/json; charset=utf-8")
public class TestBookElasticController {

	@Autowired
	private Client client;

	@PostMapping("/create")
	public String create(@RequestBody BookElastic book) throws IOException {
		IndexResponse response = null;
		try {
		response = client.prepareIndex("users", "employee", book.getId()).setSource(jsonBuilder()
				.startObject().field("name", book.getBookName()).endObject())
				.get();
		System.out.println("response id:" + response.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
