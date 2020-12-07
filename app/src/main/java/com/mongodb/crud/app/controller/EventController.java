package com.mongodb.crud.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.crud.app.model.Event;
import com.mongodb.crud.app.service.EventService;

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
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventServ;

    @PostMapping("/addEvent")
    public ResponseEntity<Event> addEvent(Event event, HttpServletRequest request) {
        try {
            event.setEventDate(new Date());
            event.setDeleteFlag(false);
            event.setUpdatedOn(new Date());
            Event _event = eventServ.addEvent(event);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getEvents")
    public ResponseEntity<List<Event>> getUsers() {
        try {
            List<Event> eventList = eventServ.getAllEvent();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAvailableEvents")
    public ResponseEntity<List<Event>> getAvailableUsers() {
        try {
            List<Event> eventList = eventServ.getAvailableEvents();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/getEvent")
    public ResponseEntity<Event> getUserByID(String id, HttpServletRequest request) {
        try {
            Optional<Event> eventOpt = eventServ.getEventById(id);

            if (eventOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.valueOf(404)).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateEvent")
    public ResponseEntity<Event> updateUser(String _id, Event Event, HttpServletRequest request) {
        try {
            Event _event = eventServ.updateEvent(_id, Event);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/markEvent")
    public ResponseEntity<Event> markUser(String _id, HttpServletRequest request) {
        try {
            Event _event = eventServ.markEvent(_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_event);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Boolean> deleteCustomerById(@PathVariable String id, HttpServletRequest request) {
        try {
            eventServ.deleteEventById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
