package com.mongodb.crud.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.mongodb.crud.app.model.Purchase;

public interface PurchaseRepo extends MongoRepository<Purchase, String> {
    // No custom methods at this moment
    // Use all default MongoRepo methods

    public List<Purchase> findByDeleteFlag(Boolean deleteFlag);

    public List<Purchase> findByEventId(String eventId);

    public List<Purchase> findByUserId(String userId);
}
