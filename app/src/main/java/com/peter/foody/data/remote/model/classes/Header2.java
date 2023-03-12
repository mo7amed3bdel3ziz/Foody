package com.peter.foody.data.remote.model.classes;

public class Header2 {



        public String dateTimeIssued;

        public String receiptNumber;

        public String uuid;

        public String previousUUID;

        public String referenceOldUUID;

        public String referenceUUID;

        public String currency="EGP";

        public double exchangeRate= 0.0;

        public String sOrderNameCode="";

        public String orderdeliveryMode= "FC";

        public double grossWeight= 0.0;

        public double netWeight= 0.0;

        public Header2(String dateTimeIssued, String receiptNumber, String uuid, String previousUUID, String referenceOldUUID,String referenceUUID) {
            this.dateTimeIssued = dateTimeIssued;
            this.receiptNumber = receiptNumber;
            this.uuid = uuid;
            this.referenceUUID = referenceUUID;
            this.previousUUID = previousUUID;
            this.referenceOldUUID = referenceOldUUID;
        }


    }

