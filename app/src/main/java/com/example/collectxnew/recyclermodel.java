package com.example.collectxnew;

public class recyclermodel
{
    String name,account,purl;
    recyclermodel()
    {

    }


    public recyclermodel(String name, String account, String purl) {
        this.name = name;
        this.account = account;
        this.purl = purl;


    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

