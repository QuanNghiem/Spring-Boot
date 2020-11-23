package com.mongodb.crud.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.crud.app.exception.UserException;
import com.mongodb.crud.app.model.User;
import com.mongodb.crud.app.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    public User updateUser(String id, User user) throws UserException {
        Optional<User> userOpt = this.getUserById(id);

        if (!userOpt.isPresent()) {
            throw new UserException("404", "Can not find a customer for updating with id = " + id);
        }

        User _user = userOpt.get();

        _user.setUsername(user.getUsername());
        _user.setPassword(user.getPassword());
        _user.setType(user.getType());
        _user.setEmail(user.getEmail());
        _user.setPNo(user.getPNo());
        _user.setUpdatedOn(user.getUpdatedOn());

        repo.save(_user);

        return (_user);
    }
}
