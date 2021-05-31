package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AppInfo extends AppCompatActivity {
    TextView appname, version,year,license;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        appname = findViewById(R.id.add_appname);
        version = findViewById(R.id.add_version);
        img = findViewById(R.id.add_img);
        year = findViewById(R.id.add_year);
        license = findViewById(R.id.add_license);

    }
}