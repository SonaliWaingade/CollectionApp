package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ListViewDailyCollect extends AppCompatActivity {
    private RecyclerView LVRecyclerView;
    private ViewGroup viewGroup;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_daily_collect);

        Ref = FirebaseDatabase.getInstance().getReference("customer ").child("CD");

        LVRecyclerView = findViewById(R.id.lv_DailyCollect);
       LVRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Custom> options = new FirebaseRecyclerOptions.Builder<Custom>()
                .setQuery(Ref, Custom.class)
                .build();

        FirebaseRecyclerAdapter<Custom, ListViewDailyCollect.FindCustomerViewHolder> adapter = new FirebaseRecyclerAdapter<Custom, ListViewDailyCollect.FindCustomerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ListViewDailyCollect.FindCustomerViewHolder holder, final int position, @NonNull Custom model) {
                holder.AgenName.setText(model.getAgenName());
                holder.Collection.setText(model.getCollection());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visit_user_id = getRef(position).getKey();

                        Intent profileIntent = new Intent(ListViewDailyCollect.this, find_Profile_Activity.class);
                        profileIntent.putExtra("visit_user_id", visit_user_id);
                        startActivity(profileIntent);

                    }
                });


            }

            @NonNull
            @Override
            public ListViewDailyCollect.FindCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_display_layout,parent, false);
               ListViewDailyCollect.FindCustomerViewHolder ViewHolder = new FindCustomerViewHolder(view);
                return ViewHolder;
            }
        };

        LVRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class FindCustomerViewHolder extends RecyclerView.ViewHolder{

        TextView AgenName, Collection;


        public FindCustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            AgenName = itemView.findViewById(R.id.TV_agentName);
            Collection= itemView.findViewById(R.id.TV_Amount);


        }
    }
}