package com.example.collectxnew;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Four_Activity extends AppCompatActivity {

   private DatabaseReference RootRef;
    private Toolbar toolbar;

    private View groupFragmentView;

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_);
        toolbar = findViewById(R.id.toolbar4);

        setSupportActionBar(toolbar);

        RootRef = FirebaseDatabase.getInstance().getReference("customer").child("Customer Names");

        listView = (ListView) findViewById(R.id.list_view);

        arrayAdapter =new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String currentGroupName = parent.getItemAtPosition(position).toString();
               Intent groupChatIntent = new Intent(getApplicationContext(), CustomerDetailActivity.class);

               groupChatIntent.putExtra("groupName", currentGroupName);
               startActivity(groupChatIntent);

           }
       });

        RootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String Name = RootRef.push().getKey();
                arrayList.add(Name);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.new_customer)
        {
            RequestNewGroup();

        }
        if (item.getItemId() == R.id.find_customer)
        {
            RequestNewCustomer();

        }

        return true;
    }

    private void RequestNewCustomer() {
        Intent newIntent = new Intent(Four_Activity.this, FindCustomerActivity.class);
        startActivity(newIntent);
    }

    private void RequestNewGroup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Four_Activity.this, R.style.AlertDialog);
        builder.setTitle("Enter new customer -");


        final EditText groupNameField = new EditText(Four_Activity.this);
        groupNameField.setHint("Customer name");
        builder.setView(groupNameField);






        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


              String groupNamei = groupNameField.getText().toString();
                if (TextUtils.isEmpty(groupNamei))

                {

                    Toast.makeText(Four_Activity.this, "Please enter customer name", Toast.LENGTH_SHORT).show();
                }
                else {

                    CreateNewGroup(groupNamei);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });

        builder.show();
    }

    private void CreateNewGroup(final String groupName) {
        RootRef.child(groupName).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    /*String id = RootRef.push().getKey();
                  RootRef.child(id).setValue(groupName);*/



                    Toast.makeText(Four_Activity.this, groupName+" is created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
