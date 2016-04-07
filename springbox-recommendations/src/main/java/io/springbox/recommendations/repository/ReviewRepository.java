package io.springbox.recommendations.repository;

import io.springbox.recommendations.domain.Review;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Vinicius Carvalho
 */
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

}
