package com.mongodb.crud.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.crud.app.model.User;

public interface UserRepo extends MongoRepository<User, String> {
    // I have no idea why this work. It just work???
    // Something interface to work with mongodb
}
