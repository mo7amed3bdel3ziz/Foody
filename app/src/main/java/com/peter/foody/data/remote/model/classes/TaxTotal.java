package com.peter.foody.data.remote.model.classes;

//@JsonPropertyOrder({
//        "TAXTOTALS",
//        "TAXTYPE",
//        "AMOUNT"
//})
public class TaxTotal {
    public String taxType="T1";

    public double amount;

    private String taxTotal;

    public TaxTotal(double amount) {
        this.amount = amount;
    }

    final String COTATION = "\"";

    public String getString(){
        String result=COTATION+"TAXTOTALS"+COTATION;
                //+COTATION+taxTotal+COTATION;
        result+=COTATION+"TAXTYPE"+COTATION+COTATION+taxType+COTATION;
        result+=COTATION+"AMOUNT"+COTATION+COTATION+amount+COTATION;
        return  result;
    }
}
