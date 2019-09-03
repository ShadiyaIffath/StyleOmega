package com.example.iffath.style_omega.Model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Product implements Parcelable {
    private String title;
    private String description;
    private String type;
    private String customer;
    private int quantity;
    private double price;
    private int thumbnail;
    private ArrayList<String> colors;
    private ArrayList<Integer> sizes;
    private ArrayList<Image> images;


    public Product(){

    }

    public Product(String title, String description, String type, String customer, int quantity, double price, int thumbnail, ArrayList<String> colors, ArrayList<Integer> sizes, ArrayList<Image> images) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.customer = customer;
        this.quantity = quantity;
        this.price = price;
        this.thumbnail = thumbnail;
        this.colors = colors;
        this.sizes = sizes;
        this.images = images;
    }

    public Product(String title, String description, String type, String customer, int quantity, double price, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.type = type;
        this.customer = customer;
        this.quantity = quantity;
        this.price = price;
        colors = new ArrayList<>();
        sizes = new ArrayList<>();
        images = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public ArrayList<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Integer> sizes) {
        this.sizes = sizes;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
