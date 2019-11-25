package com.example.demo.dao.impl;

import com.example.demo.dao.PublishHouseDao;
import com.example.demo.model.PublishingHouse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PublishHouseDaoImpl extends AbstractDaoImpl<PublishingHouse, Integer> implements PublishHouseDao {

}
