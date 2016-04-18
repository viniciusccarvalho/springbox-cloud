package io.springbox.ratings.repository;

import java.util.List;

import io.springbox.ratings.domain.Rating;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Vinicius Carvalho
 */
public interface RatingRepository extends CrudRepository<Rating,Rating.RatingId> {

	List<Rating> findByUserId(Integer userId);
}
