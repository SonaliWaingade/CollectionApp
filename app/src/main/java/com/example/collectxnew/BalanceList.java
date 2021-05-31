package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BalanceList extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView recview;
    bladapter adapter;
    DatabaseReference countref;
    FirebaseAuth mAuth;
    private String currentid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_collection_new);

        setTitle("Search here..");

        mAuth= FirebaseAuth.getInstance();
        currentid = mAuth.getCurrentUser().getUid();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        countref = FirebaseDatabase.getInstance().getReference().child("clients");


        FirebaseRecyclerOptions<blmodel> options =
                new FirebaseRecyclerOptions.Builder<blmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("clients"), blmodel.class)
                        .build();

        adapter=new bladapter(options);
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
        FirebaseRecyclerOptions<blmodel> options =
                new FirebaseRecyclerOptions.Builder<blmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("clients").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), blmodel.class)
                        .build();

        adapter= new bladapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

}