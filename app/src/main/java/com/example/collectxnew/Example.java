package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Example extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView rcv;
    exampleadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);



        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        rcv = (RecyclerView) findViewById(R.id.recview);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new exampleadapter(dataqueue(),getApplicationContext());
        rcv.setAdapter(adapter);

    }

    public ArrayList<recyclermodel> dataqueue()
    {
        ArrayList<recyclermodel> holder=new ArrayList<>();

        recyclermodel ob1=new recyclermodel ();
        ob1.setName("Aditi Musunur");
        ob1.setAccount("1");

        holder.add(ob1);

        recyclermodel  ob2=new recyclermodel ();
        ob2.setName("Advitiya Sujeet");
        ob2.setAccount("2");

        holder.add(ob2);

        recyclermodel  ob3=new recyclermodel ();
        ob3.setName("Devasru Subramanyan");
        ob3.setAccount("3");

        holder.add(ob3);

        recyclermodel  ob4=new recyclermodel ();
        ob4.setName("Dhritiman Salim");
        ob4.setAccount("4");

        holder.add(ob4);

        recyclermodel  ob5=new recyclermodel();
        ob5.setName("Hardeep Suksma");
        ob5.setAccount("5");

        holder.add(ob5);

        recyclermodel ob6=new recyclermodel();
        ob6.setName("Naveen Tikaram");
        ob6.setAccount("6");

        holder.add(ob6);

        recyclermodel ob7=new recyclermodel();
        ob7.setName("Vijai Sritharan");
        ob7.setAccount("7");

        holder.add(ob7);




        return holder;
    }

}