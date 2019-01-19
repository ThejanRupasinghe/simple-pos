package com.simplepos.backend.repository;

import com.simplepos.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "user")
public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
