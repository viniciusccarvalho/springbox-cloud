package io.springbox.apigateway.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Genre(){}

    public Genre(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    @ManyToMany(mappedBy = "genres")
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
