package com.example.demo.dao.mongo;

import com.example.common.entity.mongodb.BookHistory;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MongoBookHisDao extends MongoRepository<BookHistory, String>, QuerydslPredicateExecutor<BookHistory> {
        Collection<BookHistory> getAllBySku(String sku);

        List<BookHistory> findAllBy(TextCriteria criteria);
}
