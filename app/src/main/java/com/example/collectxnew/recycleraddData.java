package com.example.collectxnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class recycleraddData extends AppCompatActivity {

    EditText name,account;
    Button submit,back;
    TextView totalamount, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_add_data);

        name=(EditText)findViewById(R.id.add_name1);
        account=(EditText)findViewById(R.id.add_account1);






        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NewDisplayCustomer.class));
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

       /* int num1 = Integer.parseInt(balance.getText().toString());
        int num2 = Integer.parseInt(course.getText().toString());
        int sum = num1+num2;
        totalamount.setText(""+ String.valueOf(sum));*/

       /* Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE , dd-MM-yyyy hh:mm:ss a");
        String date = simpleDateFormat.format(Calendar.get);
        time.setText(date);*/






        Map<String,Object> map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("account",account.getText().toString());



        FirebaseDatabase.getInstance().getReference().child("rcvclients").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        account.setText("");




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
