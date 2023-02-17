package com.peter.foody.data.remote.model.classes;

//@JsonPropertyOrder({
//        "ITEMDISCOUNTDATA",
//        "AMOUNT",
//        "DESCRIPTION"
//})
public class ItemDiscountDatum {
    public double amount= 0.0;

    public String description="itemDiscount";

    final String COTATION = "\"";

    public String getString(){
        String result=COTATION+"ITEMDISCOUNTDATA"+COTATION;
        result+=COTATION+"AMOUNT"+COTATION+COTATION+amount+COTATION;
        result+=COTATION+"DESCRIPTION"+COTATION+COTATION+description+COTATION;
        return  result;
    }
}
