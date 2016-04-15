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

    @Query("from Movie movie where movie.hasRecommendations = true and :genre member movie.genres")
    public List<Movie> findByGenre(@Param("genre") Genre genre);


    public List<Movie> findByHasRecommendationsOrderByPopularityDesc(boolean hasRecommendations, Pageable pageable);

    public List<Movie> findByMlIdInAndHasRecommendations(List<Integer> ids, boolean hasRecommendations);

    @Query("select m.mlId from Movie m where m.hasRecommendations = true and :genre member m.genres")
    public List<Integer> findAllIdsByGenre(@Param("genre") Genre genre);

}
