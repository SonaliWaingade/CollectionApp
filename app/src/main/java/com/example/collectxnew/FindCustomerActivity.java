package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FindCustomerActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView findRecyclerView;
    private ViewGroup viewGroup;
    private DatabaseReference usersRef;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_customer);

        usersRef = FirebaseDatabase.getInstance().getReference().child("agent");

        findRecyclerView = findViewById(R.id.findfriends_RV);
        findRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* mToolbar = findViewById(R.id.toolbar);
        setActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Find Customers");*/

    }


    @Override
    protected void onStart() {
        super.onStart();

     FirebaseRecyclerOptions<Contacts> options = new FirebaseRecyclerOptions.Builder<Contacts>()
             .setQuery(usersRef, Contacts.class)
             .build();

        FirebaseRecyclerAdapter<Contacts, FindCustomerViewHolder> adapter = new FirebaseRecyclerAdapter<Contacts, FindCustomerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FindCustomerViewHolder holder, final int position, @NonNull Contacts model) {
                holder.agentName.setText(model.getFullName());
                holder.userID.setText(model.getUserID());
                holder.emailID.setText(model.getEmail());
                Picasso.get().load(model.getImage()).into(holder.circleImageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visit_user_id = getRef(position).getKey();

                        Intent profileIntent = new Intent(FindCustomerActivity.this, find_Profile_Activity.class);
                        profileIntent.putExtra("visit_user_id", visit_user_id);
                        startActivity(profileIntent);

                    }
                });


            }

            @NonNull
            @Override
            public FindCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
              View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_display_layout,parent, false);
              FindCustomerViewHolder ViewHolder = new FindCustomerViewHolder(view);
              return ViewHolder;
            }
        };

        findRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class FindCustomerViewHolder extends RecyclerView.ViewHolder{

        TextView agentName, userID, emailID;
       ImageView circleImageView;

        public FindCustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            agentName = itemView.findViewById(R.id.agentName_TV);
            userID = itemView.findViewById(R.id.userID_TV);
            emailID = itemView.findViewById(R.id.email_TV);
            circleImageView = itemView.findViewById(R.id.CIV);
        }
    }
}
