package com.mongodb.crud.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;
    private Integer type;
    private Integer pNo;
    private String email;
    private Date updatedOn;
    private Boolean deleteFlag;

    public User() {
    }

    public User(String id, String username, String password, Integer type, Integer pNo, String email, Date updatedOn,
            Boolean deleteFlag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.pNo = pNo;
        this.email = email;
        this.updatedOn = updatedOn;
        this.deleteFlag = deleteFlag;
    }

    // #region Getter/Setter
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPNo() {
        return this.pNo;
    }

    public void setPNo(Integer pNo) {
        this.pNo = pNo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public User id(String id) {
        this.id = id;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User type(Integer type) {
        this.type = type;
        return this;
    }

    public User pNo(Integer pNo) {
        this.pNo = pNo;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User updatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public User deleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }
    // #endregion
}
