package com.example.collectxnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;

public class ScannerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_scanning_qr, container, false);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.img_scanner);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showContent = new Intent(getActivity(), ScanningQRActivity.class);
                startActivity(showContent);
                ((Activity)getActivity()).overridePendingTransition(0, 0);
            }
        });
        return view;

}

    public void getText(Result result) {
    }
}

