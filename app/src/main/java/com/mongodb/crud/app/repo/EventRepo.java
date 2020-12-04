package com.mongodb.crud.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

import com.mongodb.crud.app.model.Event;

public interface EventRepo extends MongoRepository<Event, String> {
    // No custom methods at this moment
    // Use all default MongoRepo methods

    public List<Event> findByDeleteFlag(Boolean deleteFlag);

    List<Event> findByEventDateOrderByEventDateDesc(Date eventDate);
}