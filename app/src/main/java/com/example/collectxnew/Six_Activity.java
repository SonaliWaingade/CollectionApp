package com.example.collectxnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Six_Activity extends AppCompatActivity implements UsersAdapter.SelectedUser{
Toolbar toolbar;
    RecyclerView recyclerView;

List<UserModel> userModelList = new ArrayList<>();   String[] names = {"Richard", "Alice", "Hannah", "David"}; UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_);

        recyclerView = findViewById(R.id.recycleView);
        toolbar = findViewById(R.id.toolbarR);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); for (String s:names){UserModel userModel = new UserModel(s);
     userModelList.add(userModel);}
usersAdapter = new UsersAdapter(userModelList,this);
recyclerView.setAdapter(usersAdapter);}

    @Override
    public void selectedUser(UserModel userModel) {
startActivity(new Intent(Six_Activity.this, SelectedActivity.class).putExtra("data", userModel));}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_View);
        SearchView searchView = (SearchView) menuItem.getActionView();  searchView.setMaxWidth(Integer.MAX_VALUE);  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { usersAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { int id = item.getItemId(); if(id == R.id.search_View){ return true;}
        return super.onOptionsItemSelected(item);
    }
}
