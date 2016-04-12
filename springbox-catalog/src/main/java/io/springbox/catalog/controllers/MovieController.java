package io.springbox.catalog.controllers;

import io.springbox.catalog.domain.Genre;
import io.springbox.catalog.domain.Movie;
import io.springbox.catalog.repositories.GenreRepository;
import io.springbox.catalog.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;

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
        return movieRepository.findByIdIn(Arrays.asList(mlId.split(",")).stream().map(Long::parseLong).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/movies/genre/{genreMlId}", method = RequestMethod.GET)
    public List<Movie> moviesByGenreMlId (@PathVariable Long id) {
        Genre genre = genreRepository.findOne(id);
        return movieRepository.findByGenre(genre);
    }
}
