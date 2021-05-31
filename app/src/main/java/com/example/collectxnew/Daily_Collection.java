package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class Daily_Collection extends AppCompatActivity {
    GridLayout mainGrid;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__collection);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);




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
                        Intent intent = new Intent(Daily_Collection.this, BalanceList.class);
                        startActivity(intent);
                    }else if (finalI == 1)
                    {
                        Intent intent = new Intent(Daily_Collection.this, AccountStatement.class);
                        startActivity(intent);
                    }else if (finalI == 2)
                    {
                        Intent intent = new Intent(Daily_Collection.this, WithdrawActivity.class);
                        startActivity(intent);
                    }else if (finalI == 3)
                    {
                        Intent intent = new Intent(Daily_Collection.this, BluetoothPrint.class);
                        startActivity(intent);
                    }else if (finalI == 4)
                    {
                        Intent intent = new Intent(Daily_Collection.this, WithdrawActivity.class);
                        startActivity(intent);
                    }else if (finalI == 5)
                    {
                        Intent intent = new Intent(Daily_Collection.this, BluetoothPrint.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }

}