package com.example.iffath.style_omega.Model;

import com.orm.SugarRecord;


public class Cart extends SugarRecord {
    private long UserId;
    private double Price = 0;
    private boolean Status;
    private String Updated;
    private String Created;
    private Recipient recipient;
    public Cart(){

    }

    public Cart(long userId, double price, boolean status, String updated, String created, Recipient recipient) {
        this.UserId = userId;
        this.Price = price;
        this.Status = status;
        this.Updated = updated;
        this.Created = created;
        this.recipient = recipient;
    }

    public Cart(long userId, double price, boolean status, String updated, String created) {
        this.Status = status;
        this.UserId = userId;
        this.Price = price;
        this.Updated = updated;
        this.Created = created;
    }

    public Cart(long userId,double price, boolean status,String created){
        this.Status = status;
        this.UserId = userId;
        this.Price = price;
        this.Created = created;
        this.Updated = created;
    }

    public Cart(long userId,String created){  //the first time
        this.UserId = userId;
        Status = false;
        this.Created = created;
        this.Updated = created;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        this.UserId = userId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        this.Status = status;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        this.Updated = updated;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        this.Created = created;
    }
}