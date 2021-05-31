package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.Month;

public class EMICalculatorActivity extends AppCompatActivity {

    private EditText principal, interest, years, Emi, totalInterest;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_m_i_calculator);

        principal = findViewById(R.id.principle);
        interest = findViewById(R.id.interest);
        years = findViewById(R.id.years);
        Emi = findViewById(R.id.emi);
        totalInterest = findViewById(R.id.totalInterest);

        calculate = findViewById(R.id.btn_calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st1 = principal.getText().toString();
                String st2 = interest.getText().toString();
                String st3 = years.getText().toString();

                if (TextUtils.isEmpty(st1)){
                    principal.setError("Enter principal amount");
                    principal.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st2)){
                    interest.setError("Enter interest per year");
                    interest.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st3)){
                    years.setError("Enter how many years");
                    years.requestFocus();
                    return;
                }

                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);

                float Principal = calPrinciple(p);

                float Rate = calInt(i);

                float Months = calMnth(y);

                float Divident= caldvdnt(Rate, Months);

                float FD = calFinalDvdnt(Principal, Rate, Divident);

                float D = calDivider(Divident);

                float emi = calEmi(FD,D);

                float TAP = calTam(emi, Months);

                float TI = calTI(TAP, Principal);

                Emi.setText(String.valueOf(emi));
                totalInterest.setText(String.valueOf(TI));
            }
        });


    }



    private float calPrinciple(float p) {


        return (float)(p);

    }

    private float calInt(float i) {

        return (float)(i/12/100);
    }

    private float calMnth(float y) {
        return (float)(y * 12);
    }

    private float caldvdnt(float Rate, float Months){
        return (float) (Math.pow(1+Rate, Months));
    }

    private float calFinalDvdnt(float Principal, float Rate , float Divident){
        return (float) (Principal * Rate * Divident);

    }

    private float calDivider(float Divident){
        return (float)(Divident - 1);
    }

    private float calEmi(float FD, float D){
        return (float)(FD/D);
    }

    private float calTam(float emi, float Months){
        return (float) (emi*Months);
    }

    private float calTI(float TAP, float Principal){
        return (float)(TAP-Principal);
    }
}
