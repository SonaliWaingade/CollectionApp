package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

     RecyclerView recycler;
    private Toolbar toolbar;
    public static final String Extra_Mountain = "extra_mountain";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recycler =(RecyclerView)findViewById(R.id.R_recview);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }
}