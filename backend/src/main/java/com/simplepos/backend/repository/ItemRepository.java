package com.simplepos.backend.repository;

import com.simplepos.backend.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "item")
public interface ItemRepository extends MongoRepository<Item, String> {

    Optional<Item> findByName(String name);

    Boolean existsByName(String name);

    Optional<ArrayList<Item>> findAllByCategory(String category);

    // TODO: 1/20/19 check findallbycategory
}
