package com.mongodb.crud.app.service;

import java.util.Date;
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

    public List<User> getAvailableUsers() {
        return repo.findByDeleteFlag(false);
    }

    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    public User loginUser(User user) {

        Optional<User> userOpt = repo.findByUsername(user.getUsername());

        if (userOpt.isPresent()) {
            User _user = userOpt.get();
            if (user.getPassword().equals(_user.getPassword())) {
                return _user;
            }
        }
        return null;
    }

    public boolean verifyUser(String id) {
        Optional<User> userOpt = this.getUserById(id);

        if (!userOpt.isPresent()) {
            return false;
        }

        User _user = userOpt.get();

        if (_user.getType() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public User updateUser(String id, User user) throws UserException {
        Optional<User> userOpt = this.getUserById(id);

        if (!userOpt.isPresent()) {
            throw new UserException("404", "Can not find a user for updating with id = " + id);
        }

        User _user = userOpt.get();

        _user.setUsername(user.getUsername());
        _user.setPassword(user.getPassword());
        _user.setType(user.getType());
        _user.setEmail(user.getEmail());
        _user.setPNo(user.getPNo());
        _user.setUpdatedOn(new Date());

        repo.save(_user);

        return (_user);
    }

    public User markUser(String id) throws UserException {
        Optional<User> userOpt = this.getUserById(id);

        if (!userOpt.isPresent()) {
            throw new UserException("404", "Can not find a user for updating with id = " + id);
        }

        User _user = userOpt.get();
        _user.setDeleteFlag(true);
        _user.setUpdatedOn(new Date());

        repo.save(_user);

        return (_user);
    }

    public void deleteUserById(String id) {
        repo.deleteById(id);
    }
}
