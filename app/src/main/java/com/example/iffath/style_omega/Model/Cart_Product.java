package com.example.iffath.style_omega.Model;

import com.orm.SugarRecord;

public class Cart_Product extends SugarRecord {
    private long CartId;
    private int ItemID;
    private double Price;
    private int Quantity;

    public Cart_Product() {
    }

    public Cart_Product(long cartId, int itemID, double price, int quantity) {
        this.CartId = cartId;
        this.ItemID = itemID;
        this.Price = price;
        this.Quantity = quantity;
    }

    public long getCartId() {
        return CartId;
    }

    public void setCartId(long cartId) {
        this.CartId = cartId;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        this.ItemID = itemID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
}