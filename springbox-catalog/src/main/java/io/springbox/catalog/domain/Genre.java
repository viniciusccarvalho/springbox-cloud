package io.springbox.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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
