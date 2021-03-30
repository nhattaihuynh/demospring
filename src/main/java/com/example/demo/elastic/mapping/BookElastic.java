package com.example.demo.elastic.mapping;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookElastic {
	
	private String id;
	private String bookName;
	private Date createdDate;
	
}
