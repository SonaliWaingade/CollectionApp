package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerItems extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView rcv;
    rcvadapter adapter;
    FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_items);

        setTitle("Search here..");

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        rcv=(RecyclerView)findViewById(R.id.recview1);
        rcv.setLayoutManager(new LinearLayoutManager(this));



        adapter = new rcvadapter(dataqueue(),getApplicationContext());
        rcv.setAdapter(adapter);

       // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //rcv.setLayoutManager(layoutManager);

       // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
       // rcv.setLayoutManager(gridLayoutManager);

        fb=(FloatingActionButton)findViewById(R.id.fadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),  rcvaddData.class));
            }
        });

    }

   public ArrayList<rcvmodel> dataqueue(){
        ArrayList<rcvmodel> holder = new ArrayList<>();

       FirebaseRecyclerOptions<model> options =
               new FirebaseRecyclerOptions.Builder<model>()
                       .setQuery(FirebaseDatabase.getInstance().getReference().child("clients"), model.class)
                       .build();

       adapter=new rcvadapter(options);
       rcv.setAdapter(adapter);

        rcvmodel ob1 = new rcvmodel();
        ob1.getName();
        ob1.getCourse();
        holder.add(ob1);

       rcvmodel ob2 = new rcvmodel();
       ob2.setName("name1");
       ob2.setCourse("amount1");
       holder.add(ob2);

        return holder;

   }
}