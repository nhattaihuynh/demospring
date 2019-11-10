package com.example.demo.service;

import com.example.demo.dto.RatingDTO;
import com.example.demo.response.ResponseEntity;

public interface RatingService {
    
    ResponseEntity addNewRating(RatingDTO dto, Integer id_book);
}
