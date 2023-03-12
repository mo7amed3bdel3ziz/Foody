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


    public TaxableItem(double amount, double rate) {
        this.amount = amount;
        this.rate = rate;
    }


}
