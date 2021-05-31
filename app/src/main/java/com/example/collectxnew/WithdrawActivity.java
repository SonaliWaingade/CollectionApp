package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WithdrawActivity extends AppCompatActivity {

    private EditText txt_AgentName, txt_AgentNumber, txt_AccNo, txt_AccountName,txt_AvaiBalance, txt_AccountOpening, txt_Penalty,txt_WithDraw,txt_penaltyAmount,txt_MobNo;
    private Button buttonSubmit;
    private DatabaseReference fireBaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        fireBaseDatabase = FirebaseDatabase.getInstance().getReference("customer ").child("Withdraw");

        txt_AgentName = (EditText)findViewById(R.id.txt_AgentName);
        txt_AgentNumber = (EditText)findViewById(R.id.txt_AgentNumber);
        txt_AccNo = (EditText)findViewById(R.id.txt_AccNo);
        txt_AccountName = (EditText)findViewById(R.id.txt_AccountName);
        txt_AvaiBalance = (EditText)findViewById(R.id.txt_AvaiBalance);
        txt_AccountOpening = (EditText)findViewById(R.id.txt_AccountOpening);
        txt_Penalty = (EditText)findViewById(R.id.txt_Penalty);
        txt_WithDraw = (EditText)findViewById(R.id.txt_WithDraw);
        txt_penaltyAmount = (EditText)findViewById(R.id.txt_penaltyAmount);
        txt_MobNo  = (EditText)findViewById(R.id.txt_MobNo);


        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String agentName = txt_AgentName.getText().toString().trim();
                String agentNumber = txt_AgentNumber.getText().toString().trim();
                String accountNumber = txt_AccNo.getText().toString().trim();
                String accountName = txt_AccountName.getText().toString().trim();
                String availableBalance = txt_AvaiBalance.getText().toString().trim();
                String accountOpening = txt_AccountOpening.getText().toString().trim();
                String penalty = txt_Penalty.getText().toString().trim();
                String withdrawl = txt_WithDraw.getText().toString().trim();
                String penaltyAmount = txt_penaltyAmount.getText().toString().trim();
                String mobNum = txt_MobNo.getText().toString().trim();



                if (TextUtils.isEmpty(agentName)) {

                    Toast.makeText(WithdrawActivity.this, "Please enter Information", Toast.LENGTH_SHORT).show();


                } else {
                    String CD=  fireBaseDatabase.push().getKey();
                    withdraw Withdraw = new withdraw(agentName, agentNumber,accountNumber, accountName, availableBalance, accountOpening, penalty, withdrawl, penaltyAmount, mobNum );
                    fireBaseDatabase.child("WD").setValue(Withdraw);
                    Toast.makeText(WithdrawActivity.this, "Information Submitted", Toast.LENGTH_SHORT).show();

                }


            }


        });


    }
}