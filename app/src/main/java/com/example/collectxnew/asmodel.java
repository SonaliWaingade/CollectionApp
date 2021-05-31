package com.example.collectxnew;

public class asmodel
{
    String name,course,balance,email,purl,total;
    asmodel()
    {

    }


    public asmodel(String name, String course, String email, String purl, String balance, String total) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.purl = purl;
        this.balance = balance;
        this.total = total;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}

