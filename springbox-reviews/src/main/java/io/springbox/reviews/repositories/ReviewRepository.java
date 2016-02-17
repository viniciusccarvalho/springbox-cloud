package io.springbox.reviews.repositories;

import io.springbox.reviews.domain.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByMlId(String mlId);
}
