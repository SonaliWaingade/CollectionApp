package com.example.collectxnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;

public class HomeFragment extends Fragment {


    private Object mainGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

/*View view = inflater.inflate(R.layout.activity_bank, container, false);
      mainGrid = view.findViewById(R.id.mainGrid);

      CardView infobase = view.findViewById(R.id.cardview);
      infobase.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getActivity(), BankActivity.class);
              Log.e("Cardview", "CLICK");
              startActivity(intent);
          }
      });
     ImageView icon1 = view.findViewById(R.id.icon_only);
      icon1.setImageResource(R.layout.activity_bank);

*/



        return inflater.inflate(R.layout.activity_home, container, false);

}


}

