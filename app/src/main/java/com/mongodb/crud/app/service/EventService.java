package com.mongodb.crud.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.crud.app.exception.UserException;
import com.mongodb.crud.app.model.Event;
import com.mongodb.crud.app.repo.EventRepo;

@Service
public class EventService {
    @Autowired
    EventRepo repo;

    public Event addEvent(Event Event) {
        return repo.save(Event);
    }

    public List<Event> getAllEvent() {
        return repo.findAll();
    }

    public List<Event> getAvailableEvents() {
        return repo.findByDeleteFlag(false);
    }

    public List<Event> getMarkedEvents() {
        return repo.findByDeleteFlag(true);
    }

    public Optional<Event> getEventById(String id) {
        return repo.findById(id);
    }

    public Event updateEvent(String id, Event event) throws UserException {
        Optional<Event> eventOpt = this.getEventById(id);

        if (!eventOpt.isPresent()) {
            throw new UserException("404", "Can not find a Event for updating with id = " + id);
        }

        Event _event = eventOpt.get();

        _event.setName(event.getName());
        _event.setDescription(event.getDescription());
        _event.setLocation(event.getLocation());
        _event.setEventDate(event.getEventDate());
        _event.setImageURL(event.getImageURL());
        _event.setPrice(event.getPrice());
        _event.setUpdatedOn(new Date());

        repo.save(_event);

        return (_event);
    }

    public Event markEvent(String id) throws UserException {
        Optional<Event> eventOpt = this.getEventById(id);

        if (!eventOpt.isPresent()) {
            throw new UserException("404", "Can not find a Event for updating with id = " + id);
        }

        Event _event = eventOpt.get();
        _event.setUpdatedOn(new Date());
        _event.setDeleteFlag(true);

        repo.save(_event);

        return (_event);
    }

    public Event unmarkEvent(String id) throws UserException {
        Optional<Event> eventOpt = this.getEventById(id);

        if (!eventOpt.isPresent()) {
            throw new UserException("404", "Can not find a Event for updating with id = " + id);
        }

        Event _event = eventOpt.get();
        _event.setUpdatedOn(new Date());
        _event.setDeleteFlag(false);

        repo.save(_event);

        return (_event);
    }

    public void deleteEventById(String id) {
        repo.deleteById(id);
    }
}
