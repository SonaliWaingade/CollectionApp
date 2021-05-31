package com.example.collectxnew;

public class Custom {

    String AgenName;
    String Collect;
    String dailyAmt;
    String AvailableB;
    String Number;
    String Collection;

    public Custom(String groupNamei){

    }

    public Custom(String agenName, String collect, String dailyAmt, String availableB, String number, String collection) {
        AgenName = agenName;
        Collect = collect;
        this.dailyAmt = dailyAmt;
        AvailableB = availableB;
        Number = number;
        Collection = collection;
    }

    public String getAgenName() {
        return AgenName;
    }

    public String getCollect() {
        return Collect;
    }

    public String getDailyAmt() {
        return dailyAmt;
    }

    public String getAvailableB() {
        return AvailableB;
    }

    public String getNumber() {
        return Number;
    }

    public String getCollection() {
        return Collection;
    }
}

