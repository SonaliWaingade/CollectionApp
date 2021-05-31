package com.example.collectxnew;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class profilesettingadapter extends FirebaseRecyclerAdapter<customer, profilesettingadapter.myviewholder>
{
    public profilesettingadapter(@NonNull FirebaseRecyclerOptions<customer> options, Context context)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final customer customer)
    {

        holder.pro_email.setText(customer.getEmail());
        holder.pro_phone.setText(customer.getContactNumber());
        holder.pro_address.setText(customer.getAddress());
        holder.pro_userID.setText(customer.getUserID());


        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText address=myview.findViewById(R.id.ET_address);
                final EditText phone=myview.findViewById(R.id.ET_phone);
                final EditText userID=myview.findViewById(R.id.ET_userID);
                final EditText email=myview.findViewById(R.id.ET_email);
                Button submit=myview.findViewById(R.id.btn_update);



                email.setText(customer.getEmail());
                userID.setText(customer.getUserID());
                phone.setText(customer.getContactNumber());
                address.setText(customer.getAddress());



                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("email",email.getText().toString());
                        map.put("userID",userID.getText().toString());
                        map.put("contactNumber",phone.getText().toString());
                        map.put("address",address.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("agent")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        /*holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("clients")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });*/

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_new_profile_setting,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        Button btn_update;
        TextView name,course,email;
        private EditText agentName, pro_email, pro_phone, pro_address, pro_userID;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            pro_email=itemView.findViewById(R.id.ET_email);
            pro_phone=itemView.findViewById(R.id.ET_phone);
            pro_address=itemView.findViewById(R.id.ET_address);
            pro_userID = itemView.findViewById(R.id.ET_userID);
            btn_update =itemView.findViewById(R.id.btn_update);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}