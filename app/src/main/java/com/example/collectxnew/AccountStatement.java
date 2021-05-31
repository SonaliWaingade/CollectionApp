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
import com.google.firebase.database.FirebaseDatabase;

public class AccountStatement extends AppCompatActivity {

    private Toolbar toolbar;
    RecyclerView recview;
   asadapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_statement);

        setTitle("Search here..");

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<asmodel> options =
                new FirebaseRecyclerOptions.Builder<asmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("clients"), asmodel.class)
                        .build();

        adapter = new asadapter(options);

        adapter=new asadapter(options);
        recview.setAdapter(adapter);



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
        FirebaseRecyclerOptions<asmodel> options =
                new FirebaseRecyclerOptions.Builder<asmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("clients").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), asmodel.class)
                        .build();

        adapter = new asadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

}