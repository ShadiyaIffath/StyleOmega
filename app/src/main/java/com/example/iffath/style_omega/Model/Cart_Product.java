package com.example.iffath.style_omega.Model;

import com.orm.SugarRecord;

public class Cart_Product extends SugarRecord {
    private long cartId;
    private int itemID;
    private double price;
    private int quantity;

    public Cart_Product() {
    }

    public Cart_Product(long cartId, int itemID, double price, int quantity) {
        this.cartId = cartId;
        this.itemID = itemID;
        this.price = price;
        this.quantity = quantity;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
