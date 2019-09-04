package com.example.iffath.style_omega.Model;

import java.util.Date;

public class Cart {
    private long userId;
    private double price;
    private boolean status;
    private String delivery;
    private Date updated;
    private Date created;

    public Cart(){

    }

    public Cart(long userId, boolean status,Date created){
        this.userId = userId;
        this.userId = userId;
        this.created = created;
    }

    public Cart(long userId, double price, boolean status, String delivery, Date updated, Date created) {
        this.userId = userId;
        this.price = price;
        this.userId = userId;
        this.delivery = delivery;
        this.updated = updated;
        this.created = created;
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

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
