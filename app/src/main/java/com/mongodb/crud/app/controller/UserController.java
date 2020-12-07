package com.mongodb.crud.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.crud.app.model.User;
import com.mongodb.crud.app.security.JwtTokenUtil;
import com.mongodb.crud.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userServ;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(User user, HttpServletRequest request) {
        try {
            user.setDeleteFlag(false);
            user.setUpdatedOn(new Date());
            User _user = userServ.saveUser(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            User _user = userServ.loginUser(user);
            if (_user != null) {
                result.put("token", jwtTokenUtil.generateToken(_user));
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
            } else {
                result.put("token", null);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
            }
        } catch (Exception e) {
            result.put("token", null);
            System.out.println("Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
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

    @GetMapping("/getAvailableUsers")
    public ResponseEntity<List<User>> getAvailableUsers() {
        try {
            List<User> usersList = userServ.getAvailableUsers();
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
    public ResponseEntity<User> updateUser(String _id, User user, HttpServletRequest request) {
        try {
            User _user = userServ.updateUser(_id, user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/markUser")
    public ResponseEntity<User> markUser(String _id, HttpServletRequest request) {
        try {
            User _user = userServ.markUser(_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/unmarkUser")
    public ResponseEntity<User> unmarkUser(String _id, HttpServletRequest request) {
        try {
            User _user = userServ.unmarkUser(_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable String id, HttpServletRequest request) {
        try {
            // delete a Customer from MongoDB database using ID
            userServ.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
