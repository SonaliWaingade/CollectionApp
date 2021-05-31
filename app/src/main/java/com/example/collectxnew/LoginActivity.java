package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity<Backendless> extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    RelativeLayout rellay1, rellay2;
    Button  btn_login;
    EditText txtEmail, txtPassword;


    private Button forgotpassword, registerbutton ,phoneAuthen;





    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);

        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);


        btn_login = (Button)findViewById(R.id.btn_login);

        forgotpassword = (Button)findViewById(R.id.btn_ForgotPassword);
        registerbutton = (Button)findViewById(R.id.btn_Register);

        txtEmail = (EditText)findViewById(R.id.txt_email);
        txtPassword = (EditText)findViewById(R.id.txt_password);

        firebaseAuth = FirebaseAuth.getInstance();


        handler.postDelayed(runnable, 2000);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6){
                    Toast.makeText(LoginActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(LoginActivity.this, "Login Complete", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(LoginActivity.this, "Login failed or user not available", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });



            }
        });



     forgotpassword.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(LoginActivity.this, ForgotpasswordActivity.class));
         }
     });


     registerbutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             finish();
             startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
         }
     });





    }

    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}



