package com.peter.foody.data.remote.model.classes;


public class ExtraReceiptDiscountDatum {
    final String COTATION = "\"";

    public double amount=0.0;

    public String description="itmdiscount";

    private String extraReceipt;

    public String getString(){
        String result=COTATION+"EXTRARECEIPTDISCOUNTDATA"+COTATION;
        result+=COTATION+"AMOUNT"+COTATION+COTATION+amount+COTATION;
        result+=COTATION+"DESCRIPTION"+COTATION+COTATION+description+COTATION;
        return  result;
    }}
