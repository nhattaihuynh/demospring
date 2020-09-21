package com.example.demo.controller;

import com.example.demo.dto.RatingDTO;
import com.example.demo.response.ResponseEntity;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/rating", produces = "application/json; charset=utf-8")
@RestController
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/addRating/{id_book}")
    public ResponseEntity addRating(@RequestBody RatingDTO dto, @PathVariable("id_book") Integer id_book){
        return ratingService.addNewRating(dto, id_book);
    }

    @GetMapping("/calc-rating-book/{id_book}")
    public ResponseEntity calcRatingForBook(@PathVariable("id_book") Integer id_book){
        return ratingService.calcRatingForBook(id_book);
    }
}
