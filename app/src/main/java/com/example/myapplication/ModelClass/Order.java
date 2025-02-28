package com.example.myapplication.ModelClass;

import java.io.Serializable;

public class Order implements Serializable {
    public  String itemname;
    public String phoneno;
    public  String address;
    public String quantity;
    public String itemprice;
    public String image;

    public Order() {
    }

    public Order(String itemname, String phoneno, String address, String quantity, String itemprice, String image) {
        this.itemname = itemname;
        this.phoneno=phoneno;
        this.address = address;
        this.quantity = quantity;
        this.itemprice=itemprice;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }
}
