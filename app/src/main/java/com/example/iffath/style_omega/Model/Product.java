package com.example.iffath.style_omega.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Product implements Parcelable{
    private int id;
    private String title;
    private String description;
    private String type;
    private String consumer;
    private String thumb;
    private int quantity;
    private double price;
    private String[] colors;
    private String[] sizes;
    private String[] images;


    public Product(){

    }

    public Product(int id, String title, String description, String type, String consumer, String thumb, int quantity, double price, String[] colors, String[] sizes,String[] images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.consumer = consumer;
        this.thumb = thumb;
        this.quantity = quantity;
        this.price = price;
        this.colors = colors;
        this.sizes = sizes;
        this.images = images;
    }

    public Product(int id, String title, String description, String type, String customer, int quantity, double price, String[] colors, String[] sizes, String[] images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.consumer = customer;
        this.quantity = quantity;
        this.price = price;
        this.colors = colors;
        this.sizes = sizes;
        this.images = images;
    }

    public Product(int id, String title, String description, String type, String customer, int quantity, double price,String[] colors, String[] sizes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.consumer = customer;
        this.quantity = quantity;
        this.price = price;
        this.colors = colors;
        this.sizes = sizes;

    }

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        type = in.readString();
        consumer = in.readString();
        thumb = in.readString();
        quantity = in.readInt();
        price = in.readDouble();
        colors = in.createStringArray();
        sizes = in.createStringArray();
        images = in.createStringArray();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
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

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String[] getSizes() {
        return sizes;
    }

    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String allColors(){
        String color="";
        for (String s: colors) {
            color=s+", "+color;
        }
        return color;
    }

    public String allSizes(){
        String size = "";
        for(String s: sizes){
            size= s+", "+size;
        }
        return size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(type);
        parcel.writeString(consumer);
        parcel.writeString(thumb);
        parcel.writeInt(quantity);
        parcel.writeDouble(price);
        parcel.writeStringArray(colors);
        parcel.writeStringArray(sizes);
        parcel.writeStringArray(images);
    }
}

