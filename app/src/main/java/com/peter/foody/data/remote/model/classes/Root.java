package com.peter.foody.data.remote.model.classes;

import java.util.ArrayList;

//@JsonPropertyOrder({
//        "HEADER",
//        "DOCUMENTTYPE",
//        "SELLER",
//        "BUYER",
//        "ITEMDATA",
//        "TOTALSALES",
//        "TOTALCOMMERCIALDISCOUNT",
//        "TOTALITEMSDISCOUNT",
//        "EXTRARECEIPTDISCOUNTDATA",
//        "NETAMOUNT",
//        "FEESAMOUNT",
//        "TOTALAMOUNT",
//        "TAXTOTALS",
//        "PAYMENTMETHOD",
//        "ADJUSTMENT",
//        "CONTRACTOR",
//        "BENEFICIARY"
//})
public class Root {

    public Header header;
    public DocumentType documentType;
    public Seller seller;
    public Buyer buyer;
    public ArrayList<ItemDatum> itemData;
    public double totalSales;
    public double totalCommercialDiscount;
    public double totalItemsDiscount;
    public ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData;
    public double netAmount;
    public int feesAmount;
    public double totalAmount;
    public ArrayList<TaxTotal> taxTotals;
    public String paymentMethod="C";
    public int adjustment=0;
    public Contractor contractor;
    public Beneficiary beneficiary;




    public Root() {
    }

    public Root(Header header, DocumentType documentType, Seller seller, Buyer buyer, ArrayList<ItemDatum> itemData, double totalSales, double totalCommercialDiscount, double totalItemsDiscount, ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData, double netAmount, int feesAmount, double totalAmount, ArrayList<TaxTotal> taxTotals, String paymentMethod, int adjustment, Contractor contractor, Beneficiary beneficiary) {
        this.header = header;
        this.documentType = documentType;
        this.seller = seller;
        this.buyer = buyer;
        this.itemData = itemData;
        this.totalSales = totalSales;
        this.totalCommercialDiscount = totalCommercialDiscount;
        this.totalItemsDiscount = totalItemsDiscount;
        this.extraReceiptDiscountData = extraReceiptDiscountData;
        this.netAmount = netAmount;
        this.feesAmount = feesAmount;
        this.totalAmount = totalAmount;
        this.taxTotals = taxTotals;
        this.paymentMethod = paymentMethod;
        this.adjustment = adjustment;
        this.contractor = contractor;
        this.beneficiary = beneficiary;
    }




}
