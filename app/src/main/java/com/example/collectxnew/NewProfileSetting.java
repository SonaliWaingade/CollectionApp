package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class NewProfileSetting extends AppCompatActivity {

    EditText agentName, pro_email, pro_phone, pro_address, pro_userID;
     Button btn_update;
    DatabaseReference reference;
    String fullName, userID, address, contactNumber, email;
    profilesettingadapter psadapter;
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_);

        reference = FirebaseDatabase.getInstance().getReference("agent");


        pro_email = findViewById(R.id.ET_email);
        pro_phone = findViewById(R.id.ET_phone);
        pro_address = findViewById(R.id.ET_address);
        pro_userID = findViewById(R.id.ET_userID);

        btn_update = findViewById(R.id.btn_update);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<customer> options =
                new FirebaseRecyclerOptions.Builder<customer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("agent"), customer.class)
                        .build();

        psadapter = new profilesettingadapter(options, this);
        recview.setAdapter(psadapter);

        psadapter.startListening();



/*
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("agent").child("id");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String email=dataSnapshot.child("email").getValue().toString();
                        String phone=dataSnapshot.child("contactNumber").getValue().toString();
                        String address=dataSnapshot.child("address").getValue().toString();
                        String userID=dataSnapshot.child("userID").getValue().toString();

                        pro_email.getText(email);
                        pro_phone.getText(phone);
                        pro_address.getText(address);
                        pro_userID.getText(userID);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/

    }

}