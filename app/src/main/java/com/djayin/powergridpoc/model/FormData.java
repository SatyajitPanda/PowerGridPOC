package com.djayin.powergridpoc.model;

public class FormData {
    private int UniqueID;
    private double lat;
    private double lon;
    private String SelectedAddress;
    private String Circle;
    private String SubDivision;
    private String ConsumerName;
    private String MobileNumber;
    private String FlatNumber;
    private String Address1;
    private String Address2;
    private String Address3;

    public int getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(int uniqueID) {
        UniqueID = uniqueID;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getSelectedAddress() {
        return SelectedAddress;
    }

    public void setSelectedAddress(String selectedAddress) {
        SelectedAddress = selectedAddress;
    }

    public String getCircle() {
        return Circle;
    }

    public void setCircle(String circle) {
        Circle = circle;
    }

    public String getSubDivision() {
        return SubDivision;
    }

    public void setSubDivision(String subDivision) {
        SubDivision = subDivision;
    }

    public String getConsumerName() {
        return ConsumerName;
    }

    public void setConsumerName(String consumerName) {
        ConsumerName = consumerName;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getFlatNumber() {
        return FlatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        FlatNumber = flatNumber;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getAddress3() {
        return Address3;
    }

    public void setAddress3(String address3) {
        Address3 = address3;
    }
}
