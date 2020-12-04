package com.mongodb.crud.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Event {
    @Id
    private String _id;

    private String name;
    private String description;
    private String location;
    private Date eventDate;
    private String imageURL;
    private Double price;
    private Date updatedOn;
    private Boolean deleteFlag;

    public Event() {
    }

    public Event(String name, String description, String location, Date eventDate, String imageURL, Double price,
            Date updatedOn, Boolean deleteFlag) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.imageURL = imageURL;
        this.price = price;
        this.updatedOn = updatedOn;
        this.deleteFlag = deleteFlag;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Event _id(String _id) {
        this._id = _id;
        return this;
    }

    public Event name(String name) {
        this.name = name;
        return this;
    }

    public Event description(String description) {
        this.description = description;
        return this;
    }

    public Event location(String location) {
        this.location = location;
        return this;
    }

    public Event eventDate(Date eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public Event imageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public Event price(Double price) {
        this.price = price;
        return this;
    }

    public Event updatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Event deleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }
}