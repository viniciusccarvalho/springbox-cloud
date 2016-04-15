package io.springbox.apigateway.domain;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Genre {

    private Integer id;

    private String name;

    public Genre(){}

    public Genre(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    @JsonIgnore
    private List<Movie> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
