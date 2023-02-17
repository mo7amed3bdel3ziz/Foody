package com.peter.foody.data.remote.model.models;

public class EditItemModel {

    int comId;
    String barCode;
    double newPrice;
    String AndroidID;

//    public EditItemModel(int comId, String barCode, double newPrice, String androidID) {
//        this.comId = comId;
//        this.barCode = barCode;
//        this.newPrice = newPrice;
//        AndroidID = androidID;
//    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}

