package com.mongodb.crud.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.crud.app.model.Purchase;
import com.mongodb.crud.app.security.JwtTokenUtil;
import com.mongodb.crud.app.service.PurchaseService;

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
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService serv;

    @Autowired
    JwtTokenUtil jwtUtil;

    @PutMapping("/addPurchase")
    public ResponseEntity<Purchase> addPurchase(Purchase purchase, HttpServletRequest request) {
        try {
            String userId = jwtUtil.getUserIdFromToken(request);
            purchase.setUserId(userId);
            purchase.setPurchaseDate(new Date());
            purchase.setDeleteFlag(false);
            purchase.setUpdatedOn(new Date());
            Purchase _purchase = serv.addPurchase(purchase);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPurchases")
    public ResponseEntity<List<Purchase>> getPurchases() {
        try {
            List<Purchase> list = serv.getAllPurchase();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAvailablePurchases")
    public ResponseEntity<List<Purchase>> getAvailablePurchases() {
        try {
            List<Purchase> list = serv.getAvailablePurchase();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPurchase/{id}")
    public ResponseEntity<Purchase> getPurchaseByID(@PathVariable String id, HttpServletRequest request) {
        try {
            Optional<Purchase> purchaseOpt = serv.getPurchaseById(id);

            if (purchaseOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(purchaseOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.valueOf(404)).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/updatePurchase")
    public ResponseEntity<Purchase> updatePurchase(String _id, Purchase purchase, HttpServletRequest request) {
        try {
            Purchase _purchase = serv.updatePurchase(_id, purchase);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/markPurchase")
    public ResponseEntity<Purchase> markPurchase(String _id, HttpServletRequest request) {
        try {
            Purchase _purchase = serv.markPurchase(_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/unmarkPurchase")
    public ResponseEntity<Purchase> unmarkPurchase(String _id, HttpServletRequest request) {
        try {
            Purchase _purchase = serv.unmarkPurchase(_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(_purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Boolean> deletePurchaseById(@PathVariable String id, HttpServletRequest request) {
        try {
            serv.deletePurchaseById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
