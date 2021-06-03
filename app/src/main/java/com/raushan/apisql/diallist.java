package com.raushan.apisql;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class diallist {
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("cell")
    @Expose
    private String cell;
    @SerializedName("name")
    @Expose
    private name name;
    @SerializedName("picture")
    @Expose
    private picture picture;

    public diallist(){

    }
    public diallist(String gender, String email, String phone, String cell, name name) {
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.cell = cell;
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }


    public name getName() {
        return name;
    }

    public void setName(name name) {
        this.name = name;
    }


    public picture getPicture() {
        return picture;
    }

    public void setPicture(picture picture) {
        this.picture = picture;
    }

}