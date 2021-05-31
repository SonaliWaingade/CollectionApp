package com.example.collectxnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewRecyclerDisplayCustomer extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView recview;
   myrecycleradapter adapter;
    FloatingActionButton fb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recycler_display_customer);

        setTitle("Search here..");

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));




        FirebaseRecyclerOptions<recyclermodel> options =
                new FirebaseRecyclerOptions.Builder<recyclermodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("rcvclients"), recyclermodel.class)
                        .build();

        adapter=new myrecycleradapter(options, getApplicationContext());
        recview.setAdapter(adapter);

        fb=(FloatingActionButton)findViewById(R.id.fadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), recycleraddData.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchview,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<recyclermodel> options =
                new FirebaseRecyclerOptions.Builder<recyclermodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("rcvclients").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), recyclermodel.class)
                        .build();

        adapter=new myrecycleradapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }



    }
