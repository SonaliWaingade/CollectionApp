package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInformation extends AppCompatActivity {
    private TextView agentName, pro_email, pro_phone, pro_address, pro_userID;
    private Button btn_show;

    DatabaseReference UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);



        agentName = findViewById(R.id.agentName);
        pro_email = findViewById(R.id.pro_email);
        pro_phone = findViewById(R.id.pro_phone);
        pro_address = findViewById(R.id.pro_address);
        pro_userID = findViewById(R.id.pro_userID);

        btn_show = findViewById(R.id.btn_sss);


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRef = FirebaseDatabase.getInstance().getReference().child("agent").child("id");
                UserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String email=dataSnapshot.child("email").getValue().toString();
                        String phone=dataSnapshot.child("contactNumber").getValue().toString();
                        String address=dataSnapshot.child("address").getValue().toString();
                        String userID=dataSnapshot.child("userID").getValue().toString();

                        pro_email.setText(email);
                        pro_phone.setText(phone);
                        pro_address.setText(address);
                        pro_userID.setText(userID);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}