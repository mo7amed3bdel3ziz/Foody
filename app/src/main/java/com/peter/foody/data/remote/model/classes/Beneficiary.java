package com.peter.foody.data.remote.model.classes;


public class Beneficiary {
    private static final String COTATION = "\"";

    public double amount= 0.0;


    public double rate= 0.0;

    public String getString(){
        String result=COTATION+ "AMOUNT" +COTATION +COTATION +amount+COTATION;
        result+= COTATION+ "RATE" +COTATION +COTATION+rate+COTATION;
        return result;
    }
}
