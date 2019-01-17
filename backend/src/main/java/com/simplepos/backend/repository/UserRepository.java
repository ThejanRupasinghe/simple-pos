package com.simplepos.backend.repository;

import com.simplepos.backend.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user")
public interface UserRepository extends MongoRepository<User, Integer> {

    User findByName(String name);
    User findBy_id(ObjectId _id);
}
