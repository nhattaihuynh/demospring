package com.example.demo.service.impl;

import com.example.demo.dto.RatingDTO;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl extends AbstractBasicServiceImpl implements RatingService {

    @Override
    public ResponseEntity addNewRating(RatingDTO dto, Integer id_book) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData(ratingDao.addNewRating(dto, id_book));
        return responseEntity;
    }
}
