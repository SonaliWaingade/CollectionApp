package com.example.collectxnew;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {
    TextView name, account;
    CardView cvpl;
    public myviewholder(@NonNull View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.rv_nametext1);
        account=(TextView)itemView.findViewById(R.id.rv_acctext);
        cvpl = itemView.findViewById(R.id.CVPL);
    }
}
