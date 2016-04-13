package io.springbox.catalog.services;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import au.com.bytecode.opencsv.CSVReader;
import com.sun.tools.javac.jvm.Gen;
import io.springbox.catalog.domain.Genre;
import io.springbox.catalog.domain.Movie;
import io.springbox.catalog.repositories.GenreRepository;
import io.springbox.catalog.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Vinicius Carvalho
 */
@Component
public class DBLoader {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GenreRepository genreRepository;

	@Value("classpath:movies.csv")
	private Resource moviesResource;

	private Map<String,Integer> genreMap = new HashMap<>();
	private AtomicInteger counter = new AtomicInteger(1);

	public void load() throws Exception{
		if(movieRepository.count() == 0){
			int count = 0;
			CSVReader reader = new CSVReader(new FileReader(moviesResource.getFile()));
			String [] contents;
			List<Movie> movies = new ArrayList<>();
			while((contents = reader.readNext()) != null){
				Movie movie = new Movie();
				movie.setId(Integer.valueOf(contents[0]));
				movie.setTitle(contents[1]);
				movie.setGenres(getGenre(contents[2]));
				movies.add(movie);
			}
			List<Genre> allGenres = new ArrayList<>();
			for(Map.Entry<String,Integer> entry : genreMap.entrySet()){
				allGenres.add(new Genre(entry.getValue(),entry.getKey()));
			}
			genreRepository.save(allGenres);
			movieRepository.save(movies);
		}
	}


	private List<Genre> getGenre(String genreColumn){
		String[] values = genreColumn.split("\\|");
		List<Genre> results = new ArrayList<>();
		for(String value : values){
			Integer id = genreMap.get(value);
			if(id == null){
				id = counter.getAndIncrement();
				genreMap.put(value,id);
			}
			results.add(new Genre(id,value));
		}

		return results;
	}

}
