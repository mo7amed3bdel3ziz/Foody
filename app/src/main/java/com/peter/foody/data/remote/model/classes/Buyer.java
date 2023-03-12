package com.peter.foody.data.remote.model.classes;


public class Buyer {


    public String type;


    public String id;


    public String name;


    public String mobileNumber;


    public String paymentNumber;

    public Buyer(String type, String id, String name, String mobileNumber, String paymentNumber) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.paymentNumber = paymentNumber;
    }


}
