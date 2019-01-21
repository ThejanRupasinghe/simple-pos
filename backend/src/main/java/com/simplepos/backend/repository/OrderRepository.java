package com.simplepos.backend.repository;

import com.simplepos.backend.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;

@RepositoryRestResource(collectionResourceRel = "order")
public interface OrderRepository extends MongoRepository<Order, String> {
    ArrayList<Order> findAllByStatus(String status);
}
