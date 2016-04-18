package io.springbox.ratings;

import java.util.List;

import io.springbox.ratings.domain.Rating;
import io.springbox.ratings.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinicius Carvalho
 */
@RestController
public class RatingsController {

	private RatingRepository repository;

	@Autowired
	public RatingsController(RatingRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createRating(@RequestBody  Rating rating){
		if(rating.getUserId() == null){
			rating.setUserId(1);
		}
		repository.save(rating);
		return new ResponseEntity<String>("", HttpStatus.CREATED);
	}

	@RequestMapping(value="/user/{userId}")
	public ResponseEntity<List<Rating>> userRatings(@PathVariable("userId") Integer userId){
		List<Rating> userRatings = repository.findByUserId(userId);
		return new ResponseEntity<List<Rating>>(userRatings,HttpStatus.OK);
	}

}
