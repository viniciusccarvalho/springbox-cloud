package io.springbox.apigateway.controllers;

import java.util.List;

import io.springbox.apigateway.domain.Genre;
import io.springbox.apigateway.domain.Movie;
import io.springbox.apigateway.services.CatalogService;
import io.springbox.apigateway.services.RecommendationService;
import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vinicius Carvalho
 */
@Controller
public class ApiController {

	private CatalogService catalogService;
	private RecommendationService recommendationService;

	@Autowired
	public ApiController(CatalogService catalogService, RecommendationService recommendationService){
		this.catalogService = catalogService;
		this.recommendationService = recommendationService;
	}

	@RequestMapping(value = "/movies/recommendations/{mlId}",method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> recommendationsFor(@PathVariable("mlId")Integer movieId){
		List<Movie> results = recommendationService.recommendations(movieId);
		return new ResponseEntity<List<Movie>>(results,HttpStatus.OK);
	}

	@RequestMapping(value = "/movies/popular", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> popularMovies(){
		List<Movie> results = catalogService.findPopular();
		ResponseEntity<List<Movie>> response = new ResponseEntity<List<Movie>>(results,HttpStatus.OK);
		return response;
	}
	@RequestMapping(value = "/genres/random", method = RequestMethod.GET)
	public ResponseEntity<List<Genre>> randomGenres(){
		return new ResponseEntity<List<Genre>>(catalogService.randomGenres(4),HttpStatus.OK);
	}

	@RequestMapping(value="/movies/random/genre/{genreId}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>>randomMoviesByGenre(@PathVariable Integer genreId){
		return new ResponseEntity<List<Movie>>(catalogService.randomMovies(genreId),HttpStatus.OK);
	}


}
