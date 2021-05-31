package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

   /*ImageView imageView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

       /* imageView.setOnTouchListener(new OnSwipeTouchListener(){
            public void onSwipeRight(){
                Toast.makeText(HistoryActivity.this, "right", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            }
            public void onSwipeLeft(){
                Toast.makeText(HistoryActivity.this, "left", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);

            }
            public void onSwipBottom(){
                Toast.makeText(HistoryActivity.this, "bottom", Toast.LENGTH_SHORT).show();

            }public void onSwipeTop(){
                Toast.makeText(HistoryActivity.this, "top", Toast.LENGTH_SHORT).show();

            }

            class OnSwipeTouchListener implements View.OnTouchListener {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return ;
                }
            }
        });*/


    }

}