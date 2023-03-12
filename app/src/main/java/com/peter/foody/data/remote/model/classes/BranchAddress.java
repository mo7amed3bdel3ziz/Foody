package com.peter.foody.data.remote.model.classes;


public class BranchAddress {

    public String country;

    public String governate;

    public String regionCity;

    public String street;

    public String buildingNumber;

    public String postalCode;

    public String floor;

    public String room;

    public String landmark;

    public String additionalInformation;

    public BranchAddress() {
    }

    public BranchAddress(String country, String governate, String regionCity, String street, String buildingNumber, String postalCode, String floor, String room, String landmark, String additionalInformation) {
        this.country = country;
        this.governate = governate;
        this.regionCity = regionCity;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postalCode = postalCode;
        this.floor = floor;
        this.room = room;
        this.landmark = landmark;
        this.additionalInformation = additionalInformation;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String getRegionCity() {
        return regionCity;
    }

    public void setRegionCity(String regionCity) {
        this.regionCity = regionCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
