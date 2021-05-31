package com.example.collectxnew;

public class customer {
    public String fullName, userID, address, contactNumber, email;

    public customer(){

    }

    public customer(String fullName, String userID, String address, String contactNumber, String email) {
        this.fullName = fullName;
        this.userID = userID;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
