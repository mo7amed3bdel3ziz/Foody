package com.peter.foody.data.remote.model.models;

public class AddItemModel {

    int Record_ID;
    String ItemName;
    String AndroidID;
    String Barcode;
    String Description;
    String Editor;
    String ItemType;
    String ItemCode;
    String UnitType;
    double Price;

//    public AddItemModel(int record_ID, String itemName, String androidID, String barcode, String description, String editor, String itemType, String itemCode, String unitType, double price)
//    {
//        Record_ID = record_ID;
//        ItemName = itemName;
//        AndroidID = androidID;
//        Barcode = barcode;
//        Description = description;
//        Editor = editor;
//        ItemType = itemType;
//        ItemCode = itemCode;
//        UnitType = unitType;
//        Price = price;
//    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }

  public int getRecord_ID() {
      return Record_ID;
  }

  public void setRecord_ID(int record_ID) {
      Record_ID = record_ID;
  }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEditor() {
        return Editor;
    }

    public void setEditor(String editor) {
        Editor = editor;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getUnitType() {
        return UnitType;
    }

    public void setUnitType(String unitType) {
        UnitType = unitType;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
