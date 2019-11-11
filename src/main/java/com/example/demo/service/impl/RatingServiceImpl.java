package com.example.demo.service.impl;

import com.example.demo.dto.RatingDTO;
import com.example.demo.response.HTTPStatus;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl extends AbstractBasicServiceImpl implements RatingService {

    @Override
    public ResponseEntity addNewRating(RatingDTO dto, Integer id_book) {
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            responseEntity.setData(ratingDao.addNewRating(dto, id_book));
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setMessage(HTTPStatus.SERVER_ERROR.getMessage());
            responseEntity.setCode(HTTPStatus.SERVER_ERROR.getCode());
        }
        return responseEntity;
    }
}
