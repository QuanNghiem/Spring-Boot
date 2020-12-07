package com.mongodb.crud.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Purchase {
    @Id
    private String _id;

    private String userId;
    private String eventId;
    private Integer amount;
    private Double price;
    private String pNo;
    private String email;
    private Date purchaseDate;
    private Date updatedOn;
    private Boolean deleteFlag;

    public Purchase(String userId, String eventId, Integer amount, Double price, String pNo, String email,
            Date purchaseDate, Date updatedOn, Boolean deleteFlag) {
        this.userId = userId;
        this.eventId = eventId;
        this.amount = amount;
        this.price = price;
        this.pNo = pNo;
        this.email = email;
        this.purchaseDate = purchaseDate;
        this.updatedOn = updatedOn;
        this.deleteFlag = deleteFlag;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPNo() {
        return this.pNo;
    }

    public void setPNo(String pNo) {
        this.pNo = pNo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Boolean isDeleteFlag() {
        return this.deleteFlag;
    }

    public Boolean getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
