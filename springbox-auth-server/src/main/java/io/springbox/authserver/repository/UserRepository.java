package io.springbox.authserver.repository;

import io.springbox.authserver.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vinicius Carvalho
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	User findByEmail(String email);
}
