package com.example.collectxnew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class rcvadapter extends RecyclerView.Adapter<myviewholder> {

    ArrayList<rcvmodel> data;
    Context context;

    public rcvadapter(ArrayList<rcvmodel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public rcvadapter(FirebaseRecyclerOptions<model> options) {
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitem_rv, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        final rcvmodel temp = data.get(position);

        holder.name.setText(data.get(position).getName());
        holder.account.setText(data.get(position).getCourse());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewDisplayCustomer.class);
                intent.putExtra("name",temp.getName());
                intent.putExtra("course",temp.getCourse() );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
