package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerViewCustomer extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView recview;
   recycleradapter adapter;
    DatabaseReference countref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_customer);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        countref = FirebaseDatabase.getInstance().getReference().child("clients");


        FirebaseRecyclerOptions<recyclerview> options =
                new FirebaseRecyclerOptions.Builder<recyclerview>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("clients"), recyclerview.class)
                        .build();

        adapter=new recycleradapter(options);
        recview.setAdapter(adapter);




      /*  countref.child(currentid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                }else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }


      }