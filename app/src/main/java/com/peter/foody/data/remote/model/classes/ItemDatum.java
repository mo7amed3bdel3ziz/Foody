package com.peter.foody.data.remote.model.classes;

import java.util.ArrayList;
//@JsonPropertyOrder({
//        "ITEMDATA",
//        "INTERNALCODE",
//        "DESCRIPTION",
//        "ITEMTYPE",
//        "ITEMCODE",
//        "UNITTYPE",
//        "QUANTITY",
//        "UNITPRICE",
//        "NETSALE",
//        "TOTALSALE",
//        "TOTAL",
//        "COMMERCIALDISCOUNTDATA",
//        "ITEMDISCOUNTDATA",
//        "VALUEDIFFERENCE",
//        "TAXABLEITEMS"
//})
public class ItemDatum {
    public String internalCode;

    public String description;

    public String itemType;

    public String itemCode;

    public String unitType;

    public double quantity;

    public double unitPrice;

    public double netSale;

    public double totalSale;

    public double total;

    public ArrayList<CommercialDiscountDatum> commercialDiscountData;

    public ArrayList<ItemDiscountDatum> itemDiscountData;

    public int valueDifference;

    public ArrayList<TaxableItem> taxableItems;


    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



    public ItemDatum(String internalCode, String description,
                     String itemType, String itemCode, String unitType, double quantity,
                     double unitPrice, double netSale, double totalSale, double total,
                     ArrayList<CommercialDiscountDatum> commercialDiscountData,
                     ArrayList<ItemDiscountDatum> itemDiscountData, int valueDifference,
                     ArrayList<TaxableItem> taxableItems) {
        this.internalCode = internalCode;
        this.description = description;
        this.itemType = itemType;
        this.itemCode = itemCode;
        this.unitType = unitType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.netSale = netSale;
        this.totalSale = totalSale;
        this.total = total;
        this.commercialDiscountData = commercialDiscountData;
        this.itemDiscountData = itemDiscountData;
        this.valueDifference = valueDifference;
        this.taxableItems = taxableItems;

    }

    final String COTATION = "\"";
    public String getString(){
        String result=COTATION+"ITEMDATA"+COTATION;
        result+=COTATION+"INTERNALCODE"+COTATION+COTATION+internalCode+COTATION;
        result+=COTATION+"DESCRIPTION"+COTATION+COTATION+description+COTATION;
        result+=COTATION+"ITEMTYPE"+COTATION+COTATION+itemType+COTATION;
        result+=COTATION+"ITEMCODE"+COTATION+COTATION+itemCode+COTATION;
        result+=COTATION+"UNITTYPE"+COTATION+COTATION+unitType+COTATION;
        result+=COTATION+"QUANTITY"+COTATION+COTATION+quantity+COTATION;
        result+=COTATION+"UNITPRICE"+COTATION+COTATION+unitPrice+COTATION;
        result+=COTATION+"NETSALE"+COTATION+COTATION+netSale+COTATION;
        result+=COTATION+"TOTALSALE"+COTATION+COTATION+totalSale+COTATION;
        result+=COTATION+"TOTAL"+COTATION+COTATION+total+COTATION;
        //Loop For each item in commercialDiscountData
        result+=COTATION+"COMMERCIALDISCOUNTDATA"+COTATION;
        for (CommercialDiscountDatum itemDatum : commercialDiscountData) {
            result+=itemDatum.getString();
        }
        //Loop in each item in itemDiscountData
        result+=COTATION+"ITEMDISCOUNTDATA"+COTATION;
        for (ItemDiscountDatum itemDatum : itemDiscountData) {
            result+=itemDatum.getString();
        }
        result+=COTATION+"VALUEDIFFERENCE"+COTATION+COTATION+valueDifference+COTATION;
        result+=COTATION+"TAXABLEITEMS"+COTATION;
        for (TaxableItem itemDatum : taxableItems) {
            result+=itemDatum.getString();
        }
        return  result;
    }


}
