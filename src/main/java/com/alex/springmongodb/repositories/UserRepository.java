package com.alex.springmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alex.springmongodb.domain.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
