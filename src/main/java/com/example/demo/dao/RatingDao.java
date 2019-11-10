package com.example.demo.dao;

import com.example.demo.dto.RatingDTO;
import com.example.demo.model.Rating;

public interface RatingDao {

    Rating addNewRating(RatingDTO dto, Integer id_book);
}
