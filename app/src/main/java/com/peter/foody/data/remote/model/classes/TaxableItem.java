package com.peter.foody.data.remote.model.classes;

//@JsonPropertyOrder({
//        "TAXABLEITEMS",
//        "TAXTYPE",
//        "AMOUNT",
//        "SUBTYPE",
//        "RATE"
//})
public class TaxableItem {
    public String taxType="T1";
    public double amount;
    public double rate;
    public String subType="V009";

    private String taxableType;

    final String COTATION = "\"";
    public TaxableItem(double amount, double rate) {
        this.amount = amount;
        this.rate = rate;
    }

    public String getString(){
        String result=COTATION+"TAXABLEITEMS"+COTATION;
        result+=COTATION+"TAXTYPE"+COTATION+COTATION+taxType+COTATION;
        result+=COTATION+"AMOUNT"+COTATION+COTATION+amount+COTATION;
        result+=COTATION+"SUBTYPE"+COTATION+COTATION+subType+COTATION;
        result+=COTATION+"RATE"+COTATION+COTATION+rate+COTATION;
        return  result;
    }
}
