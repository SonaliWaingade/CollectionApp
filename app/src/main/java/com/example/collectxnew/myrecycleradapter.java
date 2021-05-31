package com.example.collectxnew;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class myrecycleradapter extends FirebaseRecyclerAdapter<recyclermodel, myrecycleradapter.myviewholder>
{

    ArrayList<recyclermodel> data;
    Context context;

    public myrecycleradapter(@NonNull FirebaseRecyclerOptions<recyclermodel> options, Context context) {
        super(options);
        this.data = data;
        this.context = context;
    }

    public myrecycleradapter(@NonNull FirebaseRecyclerOptions<recyclermodel> options)
    {
        super(options);
    }

    /*public myadapter(@NonNull FirebaseRecyclerOptions<model> options, ArrayList<String> mNames, ArrayList<String> mImages, Context mContext) {
        super(options);
        this.mNames = mNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    public ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;*/



    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final recyclermodel recyclermodel)
    {


        holder.name.setText(recyclermodel.getName());
        holder.account.setText(recyclermodel.getAccount());



        /*holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewDisplayCustomer.class);
                intent.putExtra("name",temp.getName());
                intent.putExtra("course",temp.getCourse() );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });*/

        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final recyclermodel temp=data.get(position);

                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(context, Four_Activity.class);
                        intent.putExtra("name",temp.getName());
                        intent.putExtra("account",temp.getAccount());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });


                        //Intent intent = new Intent(context, Four_Activity.class);
                        //context.startActivity(intent);





            }
        });



      /*  holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecyclerItems.class);
                intent.putExtra("name", mNames.get(position));
                mContext.startActivity(intent);
            }
        });*/






        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1300)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText purl=myview.findViewById(R.id.urcvimgurl);
                final EditText name=myview.findViewById(R.id.urcvname);
                final EditText account=myview.findViewById(R.id.urcvaccount);

                Button submit=myview.findViewById(R.id.urcvsubmit);



                name.setText(recyclermodel.getName());
               account.setText(recyclermodel.getAccount());
               purl.setText(recyclermodel.getPurl());




                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("purl",purl.getText().toString());
                        map.put("name",name.getText().toString());
                       map.put("account",account.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("rcvclients")
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


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("rcvclients")
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
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_rv,parent,false);
        return new myviewholder(view);
    }




    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        TextView name, account;
        CardView parentlayout;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.imgrcv);
            name=(TextView)itemView.findViewById(R.id.rv_nametext1);
            account=(TextView)itemView.findViewById(R.id.rv_acctext);


            parentlayout=(CardView)itemView.findViewById(R.id.CVparentLayout);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}