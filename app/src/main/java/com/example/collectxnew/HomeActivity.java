package com.example.collectxnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;

public class HomeActivity extends AppCompatActivity {

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }



    private void setSingleEvent(GridLayout mainGrid){
        for (int i =0; i<mainGrid.getChildCount();i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0)
                    {
                        Intent intent = new Intent(HomeActivity.this, One_Activity.class);
                        startActivity(intent);
                    }else if (finalI == 1)
                    {
                        Intent intent = new Intent(HomeActivity.this, Two_Activity.class);
                        startActivity(intent);
                    }else if (finalI == 2)
                    {
                        Intent intent = new Intent(HomeActivity.this, Three_Activity.class);
                        startActivity(intent);
                    }else if (finalI == 3)
                    {


                        Intent intent = new Intent(HomeActivity.this, Four_Activity.class);
                        startActivity(intent);
                    }else if (finalI == 4)
                    {
                        Intent intent = new Intent(HomeActivity.this, Five_Activity.class);
                        startActivity(intent);
                    }else if (finalI == 5)
                    {
                        Intent intent = new Intent(HomeActivity.this, Six_Activity.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }
}
