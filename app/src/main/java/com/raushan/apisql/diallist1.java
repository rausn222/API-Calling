package com.raushan.apisql;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class diallist1 {

    private String image;
    private String name;
    private String choice;
    private int id;

    public diallist1(){

    }
    public diallist1(String image, String name, int id, String choice) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.choice = choice;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChoice(){
        return choice;
    }

    public void setChoice(String choice){
        this.choice = choice;
    }

}