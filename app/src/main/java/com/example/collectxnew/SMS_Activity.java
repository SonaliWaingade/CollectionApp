package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMS_Activity extends AppCompatActivity {
    EditText sms_number, sms_message;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s_);

        sms_number = findViewById(R.id.sms_number);
        sms_message= findViewById(R.id.sms_message);

        btn_send = findViewById(R.id.btn_send);
    }

    public void send(View view) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            String sendTo = (""+ sms_number.getText());
            String myMessage = (""+ sms_message.getText());
            smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
            setTitle("Sent");
        }catch (Exception ex){
            setTitle(ex.getMessage());
        }

    }
}
