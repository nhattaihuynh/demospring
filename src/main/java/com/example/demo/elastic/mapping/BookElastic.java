package com.example.demo.elastic.mapping;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(type = "book", indexName = "my_index")
public class BookElastic {
	
	@Id
	private Integer id;
	private String bookName;
	private Date createdDate;
	
}
