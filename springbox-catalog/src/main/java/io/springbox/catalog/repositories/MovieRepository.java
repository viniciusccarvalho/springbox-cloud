package io.springbox.catalog.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import io.springbox.catalog.domain.Genre;
import io.springbox.catalog.domain.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {


    public List<Movie> findByIdIn(List<Integer> ids);
    public List<Movie> findByOrderByPopularityDesc(Pageable page);

    @Query("from Movie movie where :genre member movie.genres")
    public List<Movie> findByGenre(@Param("genre") Genre genre);

    @Query("select m.id from Movie m where :genre member m.genres")
    public List<Integer> findAllIdsByGenre(@Param("genre") Genre genre);

    @Query("select m.similar from Movie m where m.id = :movieId")
    public List<Movie> findSimilar(@Param("movieId")Integer movieId, Pageable page);





}
