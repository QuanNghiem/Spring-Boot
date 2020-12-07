package com.mongodb.crud.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.crud.app.exception.UserException;
import com.mongodb.crud.app.model.Purchase;
import com.mongodb.crud.app.repo.PurchaseRepo;

public class PurchaseService {
    @Autowired
    PurchaseRepo repo;

    public Purchase addPurchase(Purchase purchase) {
        return repo.save(purchase);
    }

    public List<Purchase> getAllPurchase() {
        return repo.findAll();
    }

    public List<Purchase> getAvailablePurchase() {
        return repo.findByDeleteFlag(false);
    }

    public List<Purchase> getMarkedPurchase() {
        return repo.findByDeleteFlag(true);
    }

    public Optional<Purchase> getPurchaseById(String id) {
        return repo.findById(id);
    }

    public Purchase updatePurchase(String id, Purchase purchase) throws UserException {
        Optional<Purchase> purchaseOpt = this.getPurchaseById(id);

        if (!purchaseOpt.isPresent()) {
            throw new UserException("404", "Can not find a Event for updating with id = " + id);
        }

        Purchase _purchase = purchaseOpt.get();

        _purchase.setPNo(purchase.getPNo());
        _purchase.setEmail(purchase.getEmail());
        _purchase.setUpdatedOn(new Date());

        return repo.save(_purchase);
    }

    public Purchase markPurchase(String id) throws UserException {
        Optional<Purchase> purchaseOpt = this.getPurchaseById(id);

        if (!purchaseOpt.isPresent()) {
            throw new UserException("404", "Can not find a Event for updating with id = " + id);
        }

        Purchase _purchase = purchaseOpt.get();
        _purchase.setUpdatedOn(new Date());
        _purchase.setDeleteFlag(true);

        return repo.save(_purchase);
    }

    public Purchase unmarkPurchase(String id) throws UserException {
        Optional<Purchase> purchaseOpt = this.getPurchaseById(id);

        if (!purchaseOpt.isPresent()) {
            throw new UserException("404", "Can not find a Event for updating with id = " + id);
        }

        Purchase _purchase = purchaseOpt.get();
        _purchase.setUpdatedOn(new Date());
        _purchase.setDeleteFlag(false);

        return repo.save(_purchase);
    }

    public void deletePurchaseById(String id) {
        repo.deleteById(id);
    }
}
