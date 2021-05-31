package com.example.collectxnew;

public class rcvmodel
{
    String name,course;
    rcvmodel()
    {

    }


    public rcvmodel(String name, String course) {
        this.name = name;
        this.course = course;


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
}

