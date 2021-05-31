package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class CreatingNotification extends AppCompatActivity {

    private static final String CHANNEL_ID = "simplified 1";
    private static final String CHANNEL_NAME = "simplified 2";
    private static final String CHANNEL_DESC = "simplified 3";

    private TextView textView;
    private FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        textView = findViewById(R.id.TV_FCM);

        findViewById(R.id.btn_Notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();





    }

    private void startOne_Activity(){
        Intent intent = new Intent(this, One_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void displayNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.notification)
                .setContentTitle("Hello").setContentText("1st Notification").setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat mNotifiMgr = NotificationManagerCompat.from(this);
        mNotifiMgr.notify(1, mBuilder.build());

    }
}