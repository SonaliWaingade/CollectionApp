package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerDetailActivity extends AppCompatActivity {

private FirebaseAuth mAuth;
    private String currentGroupName, currentUserID, currentUserName;
    private Toolbar mToolbar;


     private EditText txt_AgenName, txt_Collec, txt_dailyAmt, txt_AvailableB, txt_Phonenumber,txt_collectionA;
     private Button btn_submit;
    private DatabaseReference fireDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        fireDatabase = FirebaseDatabase.getInstance().getReference("customer ").child("Customer Names");


        currentGroupName = getIntent().getExtras().get("groupName").toString();
        Toast.makeText(this, currentGroupName, Toast.LENGTH_SHORT).show();

        mToolbar = findViewById(R.id.toolbar_customer_detail);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(currentGroupName);


        txt_AgenName = (EditText)findViewById(R.id.txt_AgentN);
        txt_Collec = (EditText)findViewById(R.id.txt_Collec);
        txt_dailyAmt = (EditText)findViewById(R.id.txt_dailyAmt);
        txt_AvailableB = (EditText)findViewById(R.id.txt_AvailableB);
        txt_Phonenumber = (EditText)findViewById(R.id.txt_Phonenumber);
        txt_collectionA = (EditText)findViewById(R.id.txt_collectionA);


        btn_submit = (Button) findViewById(R.id.btn_submit);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String AgenName = txt_AgenName.getText().toString().trim();
                String Collect = txt_Collec.getText().toString().trim();
                String dailyAmt = txt_dailyAmt.getText().toString().trim();
                String AvailableB = txt_AvailableB.getText().toString().trim();
                String Number = txt_Phonenumber.getText().toString().trim();
                String Collection = txt_collectionA.getText().toString().trim();



                if (TextUtils.isEmpty(AgenName)) {

                    Toast.makeText(CustomerDetailActivity.this, "Please enter Information", Toast.LENGTH_SHORT).show();


                } else {
                    String CD=  fireDatabase.push().getKey();
                 Custom custom = new Custom( AgenName, Collect, dailyAmt, AvailableB, Number, Collection);
                    fireDatabase.child("CD").setValue(custom);
                    Toast.makeText(CustomerDetailActivity.this, "Information Submitted", Toast.LENGTH_SHORT).show();

                }


            }


        });





        /**GetUserInfo();**/
    }

  /**  private void GetUserInfo() {

        UserRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                     currentUserName = dataSnapshot.child("fullName").getValue(String.class);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }**/
}
