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

    public GlassesForm() {
    }

    public GlassesForm(int id, String code, String price, String color, String description, String img) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.color = color;
        this.description = description;
        this.img = img;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
