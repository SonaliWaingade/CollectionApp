package com.example.collectxnew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HistoryFragment extends Fragment {

    private View ContactView;
    private RecyclerView myContactsList;
    private DatabaseReference ContactsRef, UserRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public HistoryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       ContactView = inflater.inflate(R.layout.fragment_history, container, false);

       myContactsList = (RecyclerView)ContactView.findViewById(R.id.contacts_RL);
       myContactsList.setLayoutManager(new LinearLayoutManager(getContext()));
       ContactsRef = FirebaseDatabase.getInstance().getReference().child("Contacts").child(currentUserID);
       UserRef = FirebaseDatabase.getInstance().getReference().child("agent");
       mAuth = FirebaseAuth.getInstance();
       currentUserID = mAuth.getCurrentUser().getUid();

       return ContactView;
}

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Contacts>().setQuery(ContactsRef, Contacts.class).build();

        FirebaseRecyclerAdapter<Contacts, ContactsViewHolder> adapter
                = new FirebaseRecyclerAdapter<Contacts, ContactsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ContactsViewHolder holder, int position, @NonNull Contacts model) {
                String userIDs = getRef(position).getKey();

                UserRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("image"))
                        {
                            String userImage = dataSnapshot.child("image").getValue().toString();
                            String profileName = dataSnapshot.child("fullName").getValue().toString();
                            String profileEmail = dataSnapshot.child("email").getValue().toString();
                            String profileUserID = dataSnapshot.child("userID").getValue().toString();

                            holder.userName.setText(profileName);
                            holder.userID.setText(profileUserID);
                            holder.userEmail.setText(profileEmail);
                            Picasso.get().load(userImage).placeholder(R.drawable.profile).into(holder.profileImage);

                        }
                        else {
                            String profileName = dataSnapshot.child("fullName").getValue().toString();
                            String profileEmail = dataSnapshot.child("email").getValue().toString();
                            String profileUserID = dataSnapshot.child("userID").getValue().toString();

                            holder.userName.setText(profileName);
                            holder.userID.setText(profileUserID);
                            holder.userEmail.setText(profileEmail);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_display_layout, viewGroup, false);
                ContactsViewHolder viewHolder = new ContactsViewHolder(view);
                return viewHolder;
            }
        };

        myContactsList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder{
        TextView userName, userEmail, userID;
        ImageView profileImage;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.agentName_TV);
            userEmail = itemView.findViewById(R.id.email_TV);
            userID = itemView.findViewById(R.id.userID_TV);

            profileImage = itemView.findViewById(R.id.CIV);
        }
    }
}

