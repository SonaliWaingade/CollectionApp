package com.example.collectxnew;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Five_Activity extends AppCompatActivity {

    private EditText agentName, pro_email, pro_phone, pro_address, pro_userID;
    DatabaseReference profileUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileUserRef = FirebaseDatabase.getInstance().getReference("agent");

        agentName = findViewById(R.id.agentName);
        pro_email = findViewById(R.id.pro_email);
        pro_phone = findViewById(R.id.pro_phone);
        pro_address = findViewById(R.id.pro_address);
        pro_userID = findViewById(R.id.pro_userID);


        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Name = profileUserRef.child("agent").child("FullName").push().getKey();
                profileUserRef.child(Name);
                CreateNewGroup(agentName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void CreateNewGroup(final EditText agentName) {
        profileUserRef.child("agent").child(String.valueOf(agentName)).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    String id = profileUserRef.push().getKey();
                    profileUserRef.child(id).setValue(agentName);

                }
            }
        });
    }


}
