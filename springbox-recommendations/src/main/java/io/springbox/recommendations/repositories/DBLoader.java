package io.springbox.recommendations.repositories;

import java.util.Scanner;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.springbox.recommendations.domain.Movie;
import io.springbox.recommendations.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vinicius Carvalho
 */
@Component
public class DBLoader {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private LikesRepository likesRepository;

	@PostConstruct
	public void postStart() throws Exception {

		if(personRepository.count() == 0){
			loadPeople();
			loadMovies();
			loadLikes();
		}

	}

	private void loadPeople() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Scanner scanner = new Scanner(DBLoader.class.getClassLoader().getResourceAsStream("people.json"));
		scanner.useDelimiter("\\n");
		while(scanner.hasNext()){
			Person p = mapper.readValue(scanner.nextLine(),Person.class);
			personRepository.save(p);
		}
	}

	private void loadMovies() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Scanner scanner = new Scanner(DBLoader.class.getClassLoader().getResourceAsStream("movies.json"));
		scanner.useDelimiter("\\n");
		while(scanner.hasNext()){
			Movie m = mapper.readValue(scanner.nextLine(),Movie.class);
			movieRepository.save(m);
		}
	}

	private void loadLikes() throws Exception{
		likesRepository.likesFor("1","mstine");
		likesRepository.likesFor("2","mstine");
		likesRepository.likesFor("2","starbuxman");
		likesRepository.likesFor("4","starbuxman");
		likesRepository.likesFor("5","starbuxman");
		likesRepository.likesFor("2","littleidea");
		likesRepository.likesFor("3","littleidea");
		likesRepository.likesFor("5","littleidea");
	}
}
