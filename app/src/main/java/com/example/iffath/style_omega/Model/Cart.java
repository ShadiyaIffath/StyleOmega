package com.example.iffath.style_omega.Model;

import com.orm.SugarRecord;

import java.util.Date;

public class Cart extends SugarRecord {
    private long userId;
    private double price = 0;
    private boolean status;
    private String updated;
    private String created;
    private Recipient recipient;
    public Cart(){

    }

    public Cart(long userId, double price, boolean status, String updated, String created, Recipient recipient) {
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.updated = updated;
        this.created = created;
        this.recipient = recipient;
    }

    public Cart(long userId, double price, boolean status, String updated, String created) {
        this.status = status;
        this.userId = userId;
        this.price = price;
        this.updated = updated;
        this.created = created;
    }

    public Cart(long userId,double price, boolean status,String created){
        this.status = status;
        this.userId = userId;
        this.price = price;
        this.created = created;
        this.updated = created;
    }

    public Cart(long userId,String created){  //the first time
        this.userId = userId;
        status = false;
        this.created = created;
        this.updated = created;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
