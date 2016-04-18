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
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public List<Movie> movie(@PathVariable String id) {
        List<Movie> movies = movieRepository.findByIdIn(Arrays.asList(id.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList()));
        return movies;
    }

    @RequestMapping(value = "/movies/{id}/similar", method = RequestMethod.GET)
    public List<Movie> movie(@PathVariable Integer id, @RequestParam(value = "limit", defaultValue = "10")Integer limit) {
        Pageable pageable = new PageRequest(0,limit);
        List<Movie> movies = movieRepository.findSimilar(id,pageable);
        return movies;
    }

    @RequestMapping(value="/movies/popular/{limit}", method = RequestMethod.GET)
    public List<Movie> popular(@PathVariable Integer limit){
        Pageable pageable = new PageRequest(0,limit);
        return movieRepository.findByOrderByPopularityDesc(pageable);
    }

    @RequestMapping(value = "/movies/genre/{id}", method = RequestMethod.GET)
    public List<Movie> moviesByGenreMlId (@PathVariable Integer id) {
        Genre genre = genreRepository.findOne(id);
        return movieRepository.findByGenre(genre);
    }

    @RequestMapping(value = "/movies/random/genre/{genreId}",method = RequestMethod.GET)
    public List<Movie> randomMoviesByGenre(@PathVariable Integer genreId){
        Genre genre = new Genre();
        genre.setId(genreId);
        List<Integer> ids = movieRepository.findAllIdsByGenre(genre);
        Collections.shuffle(ids);
        return movieRepository.findByIdIn(ids.subList(0,Math.min(10,ids.size())));
    }

}
