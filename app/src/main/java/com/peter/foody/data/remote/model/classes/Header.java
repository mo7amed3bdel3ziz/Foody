package com.peter.foody.data.remote.model.classes;

/*@JsonPropertyOrder({
        "DATETIMEISSUED",
        "RECEIPTNUMBER",
        "UUID",
        "PREVIOUSUUID",
        "REFERENCEOLDUUID",
        "CURRENCY",
        "EXCHANGERATE",
        "SORDERNAMECODE",
        "ORDERDELIVERYMODE",
        "GROSSWEIGHT",
        "NETWEIGHT"
})*/
public class Header {


    public String dateTimeIssued;

    public String receiptNumber;

    public String uuid;

    public String previousUUID;

    public String referenceOldUUID;

    public String currency="EGP";

    public double exchangeRate= 0.0;

    public String sOrderNameCode="";

    public String orderdeliveryMode= "FC";

    public double grossWeight= 0.0;

    public double netWeight= 0.0;

    public Header(String dateTimeIssued, String receiptNumber, String uuid, String previousUUID, String referenceOldUUID) {
        this.dateTimeIssued = dateTimeIssued;
        this.receiptNumber = receiptNumber;
        this.uuid = uuid;
        this.previousUUID = previousUUID;
        this.referenceOldUUID = referenceOldUUID;
    }


}
