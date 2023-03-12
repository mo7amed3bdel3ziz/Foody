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




}
