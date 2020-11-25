package com.mongodb.crud.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.crud.app.model.User;
import com.mongodb.crud.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServ;

    @PostMapping("/addUser")
    public ResponseEntity<User> addQuotes(User user) {
        try {
            user.setDeleteFlag(false);
            user.setUpdatedOn(new Date());
            User _user = userServ.saveUser(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> usersList = userServ.getAllUsers();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(usersList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/getUser")
    public ResponseEntity<User> getUserByID(String id, HttpServletRequest request) {
        try {
            Optional<User> userOpt = userServ.getUserById(id);

            if (userOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(userOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.valueOf(404)).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateCustomer(String id, User user, HttpServletRequest request) {
        try {
            user.setUpdatedOn(new Date());
            User _user = userServ.updateUser(id, user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
