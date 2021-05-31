package com.example.collectxnew;

public class Contacts {

    public String fullName, userID, email, image;

    public Contacts (){

    }

    public Contacts(String fullName, String userID, String email, String image) {
        this.fullName = fullName;
        this.userID = userID;
        this.email = email;
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
