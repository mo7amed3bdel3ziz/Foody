package com.peter.foody.data.remote.model.classes;

//@JsonPropertyOrder({
//        "NAME",
//        "AMOUNT",
//        "RATE"
//})
public class Contractor {
    final String COTATION = "\"";


    public String name="0";


    public double amount=0.0;


    public double rate=0.0;

    public  String  getString(){
        String result=COTATION+"NAME"+COTATION+COTATION+name+COTATION;
        result+=COTATION+"AMOUNT"+COTATION+COTATION+amount+COTATION;
        result+=COTATION+"RATE"+COTATION+COTATION+rate+COTATION;
        return  result;
    }
}
