package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {
    Button btn_appinfo, btn_pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



        btn_appinfo = findViewById(R.id.btn_AppInfo);
        btn_pref = findViewById(R.id.preference);

        btn_appinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerIntent = new Intent(SettingActivity.this, AppInfo.class);
                startActivity(scannerIntent);

            }
        });

        btn_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerIntent = new Intent(SettingActivity.this, Preference.class);
                startActivity(scannerIntent);

            }
        });
    }


}
