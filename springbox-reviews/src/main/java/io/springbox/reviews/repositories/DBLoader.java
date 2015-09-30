package io.springbox.reviews.repositories;

import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.springbox.reviews.domain.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vinicius Carvalho
 */
@Component
public class DBLoader {

	@Autowired
	private ReviewRepository repository;

	@PostConstruct
	public void postStart() throws Exception{
		Iterable<Review> reviews = repository.findByMlId("1");
		if(reviews == null || !reviews.iterator().hasNext()){
			ObjectMapper mapper = new ObjectMapper();
			Scanner scanner = new Scanner(DBLoader.class.getClassLoader().getResourceAsStream("reviews.json"));
			scanner.useDelimiter("\\n");
			while(scanner.hasNext()){
				Review review = mapper.readValue(scanner.nextLine(),Review.class);
				repository.save(review);
			}
		}
	}

}
