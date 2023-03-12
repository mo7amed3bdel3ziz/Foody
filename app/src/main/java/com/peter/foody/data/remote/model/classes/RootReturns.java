package com.peter.foody.data.remote.model.classes;

import java.util.ArrayList;

public class RootReturns {
    public Header2 header;
    public DocumentType2 documentType;
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




    public RootReturns() {
    }

    public RootReturns(Header2 header, DocumentType2 documentType, Seller seller, Buyer buyer, ArrayList<ItemDatum> itemData, double totalSales, double totalCommercialDiscount, double totalItemsDiscount, ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData, double netAmount, int feesAmount, double totalAmount, ArrayList<TaxTotal> taxTotals, String paymentMethod, int adjustment, Contractor contractor, Beneficiary beneficiary) {
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
