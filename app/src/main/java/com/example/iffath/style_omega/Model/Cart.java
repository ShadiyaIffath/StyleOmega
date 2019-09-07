package com.example.iffath.style_omega.Model;

import com.orm.SugarRecord;

import java.util.Date;

public class Cart extends SugarRecord {
    private long userId;
    private double price;
    private boolean status;
    private String deliveryAddress;
    private String updated;
    private String created;

    public Cart(){

    }

    public Cart(long userId, double price, boolean status, String delivery, String updated, String created) {
        this.status = status;
        this.userId = userId;
        this.price = price;
        this.deliveryAddress = delivery;
        this.updated = updated;
        this.created = created;
    }

    public Cart(long userId,String created){  //the first time
        this.userId = userId;
        status = false;
        this.created = created;
        this.updated = created;
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
