package com.peter.foody.data.remote.model.models;

public class RequestModel {
    String ComName;
    String BranchName;
    int BranchID;
    String AndroidID;
    String PosSerial;
    int comID;

    public RequestModel(String comName, String branchName, int branchID, String androidID, String posSerial, int comID) {
        ComName = comName;
        BranchName = branchName;
        BranchID = branchID;
        AndroidID = androidID;
        PosSerial = posSerial;
        this.comID = comID;
    }

    public String getComName() {
        return ComName;
    }

    public void setComName(String comName) {
        ComName = comName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }

    public String getPosSerial() {
        return PosSerial;
    }

    public void setPosSerial(String posSerial) {
        PosSerial = posSerial;
    }

    public int getComID() {
        return comID;
    }

    public void setComID(int comID) {
        this.comID = comID;
    }
}
