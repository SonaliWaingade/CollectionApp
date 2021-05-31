package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedActivity extends AppCompatActivity {
    TextView tvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        tvUsers =findViewById(R.id.selectedUser);

        Intent intent = getIntent();

        if (intent.getExtras() != null){
            UserModel userModel = (UserModel) intent.getSerializableExtra("data");

            tvUsers.setText(userModel.getUserName());
        }
    }
}
