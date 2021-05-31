package com.example.collectxnew;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class newdisplayaddData extends AppCompatActivity {

    EditText name,course,email,purl, balance;
    TextView Date;
    Button submit,back;
    TextView totalamount, time;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_display_add_data);


        course=(EditText)findViewById(R.id.add_course);
        purl=(EditText)findViewById(R.id.add_purl);


        Date=(TextView) findViewById(R.id.add_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(newdisplayaddData.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                Date.setText(date);
            }
        };





        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NewDisplay.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();


            }
        });
    }

    private void processinsert()
    {



       /* Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE , dd-MM-yyyy hh:mm:ss a");
        String date = simpleDateFormat.format(Calendar.get);
        time.setText(date);*/






        Map<String,Object> map=new HashMap<>();

        map.put("course",course.getText().toString());

        map.put("purl",purl.getText().toString());

        map.put("date",Date.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("collect").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        course.setText("");

                        purl.setText("");

                        Date.setText("");





                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }

   /* private Long getCurrentTime(){
        Long timestamp = System.currentTimeMillis()/1000;
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(timestamp*1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm", calendar).toString();
        return timestamp;
    }

   private String getDate(Long timestamp){
       Calendar cal = Calendar.getInstance(Locale.getDefault());
       cal.setTimeInMillis(timestamp*1000);
       String date = DateFormat.format("dd-MM-yyyy hh:mm", cal).toString();
       return  date;
   }*/




    }
