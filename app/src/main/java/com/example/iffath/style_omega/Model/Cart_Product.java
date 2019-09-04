package com.example.iffath.style_omega.Model;

public class Cart_Product {
    private long cartId;
    private String itemTitle;
    private double price;
    private int quantity;

    public Cart_Product() {
    }

    public Cart_Product(long cartId, String itemTitle, double price, int quantity) {
        this.cartId = cartId;
        this.itemTitle = itemTitle;
        this.price = price;
        this.quantity = quantity;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
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
