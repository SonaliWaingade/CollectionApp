package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class find_Profile_Activity extends AppCompatActivity {
    private String receiverUserID;

    private ImageView userProfileImage;
    private TextView userProfileName, userProfileEmail;
    private Button sendMsgReqBtn, msg_req_btn;

    private DatabaseReference UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__profile_);

        UserRef = FirebaseDatabase.getInstance().getReference().child("agent");

        receiverUserID = getIntent().getExtras().get("visit_user_id").toString();


        userProfileImage = findViewById(R.id.visit_profile_img);

        userProfileName = findViewById(R.id.visit_user_name);
        userProfileEmail = findViewById(R.id.visit_email);

        sendMsgReqBtn = findViewById(R.id.msg_req_btn);

        RetrieveUserInfo();

    }

    private void RetrieveUserInfo() {
        UserRef.child(receiverUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("image"))){
                   String userImage = dataSnapshot.child("image").getValue().toString();
                   String userName = dataSnapshot.child("fullName").getValue().toString();
                   String userEmail = dataSnapshot.child("email").getValue().toString();

                   Picasso.get().load(userImage).placeholder(R.drawable.profile).into(userProfileImage);
                   userProfileName.setText(userName);
                   userProfileEmail.setText(userEmail);
               }
               else {
                   String userName = dataSnapshot.child("fullName").getValue().toString();
                   String userEmail = dataSnapshot.child("email").getValue().toString();


                   userProfileName.setText(userName);
                   userProfileEmail.setText(userEmail);
               }

                msg_req_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent scannerIntent = new Intent(find_Profile_Activity.this, ChatActivity.class);
                        startActivity(scannerIntent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
