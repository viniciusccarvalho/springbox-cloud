package io.springbox.recommendations.controllers;

import java.util.List;

import io.springbox.recommendations.services.RecommendationService;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinicius Carvalho
 */
@RestController

public class RecommendationController {


	@Autowired
	private RecommendationService recommendationService;

	@RequestMapping("/users/{userId}")
	public ResponseEntity<List<RecommendedItem>> userRecommendations(@PathVariable("userId") Long userId){
		ResponseEntity<List<RecommendedItem>> response = new ResponseEntity<List<RecommendedItem>>(recommendationService.userRecommendations(userId), HttpStatus.OK);
		return response;
	}

	@RequestMapping("/movies/{movieId}")
	public ResponseEntity<List<RecommendedItem>> movieRecommendations(@PathVariable("movieId") Long movieId){
		ResponseEntity<List<RecommendedItem>> response = new ResponseEntity<List<RecommendedItem>>(recommendationService.movieRecommendations(movieId), HttpStatus.OK);
		return response;
	}

}
