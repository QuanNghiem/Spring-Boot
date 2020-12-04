package com.mongodb.crud.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

import com.mongodb.crud.app.model.User;

public interface UserRepo extends MongoRepository<User, String> {
    // No custom methods at this moment
    // Use all default MongoRepo methods

    public Optional<User> findByUsername(String username);

    public List<User> findByDeleteFlag(Boolean deleteFlag);
}
