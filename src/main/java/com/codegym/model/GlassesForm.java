package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


public class GlassesForm {
//    Glasses (id, code, price, color, description, img)

    private int id;
    private String code;
    private String price;
    private String color;
    private String description;
    private MultipartFile img;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
