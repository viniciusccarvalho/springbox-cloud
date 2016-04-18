package io.springbox.catalog.repositories;

import org.springframework.data.repository.CrudRepository;

import io.springbox.catalog.domain.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
