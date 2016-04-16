package io.springbox.apigateway.services;

import java.util.Collections;
import java.util.List;

import io.springbox.apigateway.domain.Genre;
import io.springbox.apigateway.domain.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vinicius Carvalho
 */
@Service
public class CatalogService {

	private final RestTemplate template;

	private final String base_url = "http://image.tmdb.org/t/p";
	private final String popular_size = "w1280";
	private final String genre_size = "w300";
	private final String poster_size = "w342";


	@Autowired
	public CatalogService(RestTemplate template){
		this.template = template;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public List<Movie> findPopular(){
		List<Movie> movies = template.exchange("http://SPRINGBOX-CATALOG/movies/popular/{howMany}",
												HttpMethod.GET,
												null,
												new ParameterizedTypeReference<List<Movie>>(){},
												5).getBody();
		return movies;
	}

	public List<Genre> randomGenres(int howMany){
		List<Genre> genres = template.exchange("http://SPRINGBOX-CATALOG/genres",
												HttpMethod.GET,
												null,
												new ParameterizedTypeReference<List<Genre>>(){}).getBody();
		Collections.shuffle(genres);
		return genres.subList(0,howMany);
	}

	public List<Movie> randomMovies(int genreId){
		List<Movie> movies = template.exchange("http://SPRINGBOX-CATALOG/movies/random/genre/{genreId}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>(){}, genreId).getBody();
		return movies;
	}




}
