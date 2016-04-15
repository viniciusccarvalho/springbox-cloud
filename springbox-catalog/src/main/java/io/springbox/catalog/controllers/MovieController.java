package io.springbox.catalog.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.springbox.catalog.domain.Genre;
import io.springbox.catalog.domain.Movie;
import io.springbox.catalog.repositories.GenreRepository;
import io.springbox.catalog.repositories.MovieRepository;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreRepository genreRepository;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public Iterable<Movie> movies() {
        return movieRepository.findAll();
    }

    @RequestMapping(value = "/movies/{mlId}", method = RequestMethod.GET)
    public List<Movie> movie(@PathVariable String mlId) {
        return movieRepository.findByMlIdInAndHasRecommendations(Arrays.asList(mlId.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList()),true);
    }

    @RequestMapping(value="/movies/popular/{howMany}", method = RequestMethod.GET)
    public List<Movie> popular(@PathVariable Integer howMany){
        Pageable pageable = new PageRequest(0,howMany);
        return movieRepository.findByHasRecommendationsOrderByPopularityDesc(true,pageable);
    }

    @RequestMapping(value = "/movies/genre/{id}", method = RequestMethod.GET)
    public List<Movie> moviesByGenreMlId (@PathVariable Long id) {
        Genre genre = genreRepository.findOne(id);
        return movieRepository.findByGenre(genre);
    }

    @RequestMapping(value = "/movies/random/genre/{genreId}",method = RequestMethod.GET)
    public List<Movie> randomMoviesByGenre(@PathVariable Integer genreId){
        Genre genre = new Genre();
        genre.setId(genreId);
        List<Integer> ids = movieRepository.findAllIdsByGenre(genre);
        Collections.shuffle(ids);
        return movieRepository.findByMlIdInAndHasRecommendations(ids.subList(0,Math.min(10,ids.size())),true);
    }

}
