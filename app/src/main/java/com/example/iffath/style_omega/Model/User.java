package com.example.iffath.style_omega.Model;

import com.orm.SugarRecord;

public class User extends SugarRecord {
    public String name,username,email,password,contactNumber;

    public User(){
    }
    public User(String name, String username, String email, String password, String contactNumber) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}