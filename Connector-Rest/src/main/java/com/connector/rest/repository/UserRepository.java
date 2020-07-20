package com.connector.rest.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.connector.rest.bo.UserBO;

@Repository
@Document(collection = "users")
public interface UserRepository extends MongoRepository<UserBO, Long> {

}
