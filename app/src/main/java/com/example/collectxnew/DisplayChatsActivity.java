package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DisplayChatsActivity extends AppCompatActivity {
    private View PrivateChatsView;

    private RecyclerView chatsList;
    private DatabaseReference chatsRef, userRef;
    private FirebaseAuth mAuth;
    private String currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chats);
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        chatsList = findViewById(R.id.chat_list);
        chatsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        chatsRef = FirebaseDatabase.getInstance().getReference().child("Contacts").child(currentUserID);
        userRef = FirebaseDatabase.getInstance().getReference().child("agent");


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Contacts>options = new FirebaseRecyclerOptions.Builder<Contacts>().setQuery(chatsRef, Contacts.class).build();

        FirebaseRecyclerAdapter<Contacts, chatsViewHolder > adapter = new FirebaseRecyclerAdapter<Contacts, chatsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final chatsViewHolder holder, int position, @NonNull Contacts model) {
                final  String userIDs = getRef(position).getKey();

                userRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("image")){
                            final String retImage = dataSnapshot.child("image").getValue().toString();
                            Picasso.get().load(retImage).into(holder.profileImage);
                        }

                        final String retName = dataSnapshot.child("fullName").getValue().toString();
                        final String retStatus = dataSnapshot.child("email").getValue().toString();

                        holder.userName.setText(retName);
                        holder.userEmail.setText("Last seen:" + "\n"+"Date"+"Time");




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @NonNull
            @Override
            public chatsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
               View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_display_layout, viewGroup, false);
               return new chatsViewHolder(view);
            }
        };

        chatsList.setAdapter(adapter);
        adapter.startListening();
    }

    public  static class  chatsViewHolder extends RecyclerView.ViewHolder
    {
        TextView userName, userEmail, userID;
        ImageView profileImage;
        public chatsViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.agentName_TV);
            userEmail = itemView.findViewById(R.id.email_TV);
            userID = itemView.findViewById(R.id.userID_TV);

            profileImage = itemView.findViewById(R.id.CIV);
        }
    }
}
