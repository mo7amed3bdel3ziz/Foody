package com.peter.foody.data.remote.model.classes;


public class Buyer {
    final String COTATION = "\"";

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
    public  String getString(){
        String result=COTATION+"TYPE"+COTATION+COTATION+type+COTATION;

        result+=  COTATION+"ID"+COTATION+COTATION+id+COTATION;
        result+=COTATION+"NAME"+COTATION+COTATION+name+COTATION;
       // result+=COTATION+"TYPE"+COTATION+COTATION+type+COTATION;
        result+=COTATION+"MOBILENUMBER"+COTATION+COTATION+mobileNumber+COTATION;
        result+=COTATION+"PAYMENTNUMBER"+COTATION+COTATION+paymentNumber+COTATION;
        return  result;
    }

}
