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


public class mydisplayadapter extends FirebaseRecyclerAdapter<model, mydisplayadapter.myviewholder>
{

    ArrayList<model> data;
    Context context;

    public mydisplayadapter(@NonNull FirebaseRecyclerOptions<model> options, Context context) {
        super(options);
        this.data = data;
        this.context = context;
    }

    public mydisplayadapter(@NonNull FirebaseRecyclerOptions<model> options)
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
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model)
    {


        holder.name.setText(model.getName());
        holder.course.setText("Collection Amount:"+model.getCourse());
        holder.balance.setText("Available Balance:"+model.getBalance());
        holder.email.setText("Contact No:"+model.getEmail());
        holder.total.setText("Total Amount:"+model.getTotal());
        holder.Date.setText("Date:"+model.getDate());



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
                final EditText purl=myview.findViewById(R.id.uimgurl);
                final EditText name=myview.findViewById(R.id.uname);
                final EditText course=myview.findViewById(R.id.ucourse);
                final EditText email=myview.findViewById(R.id.uemail);
                final EditText balance = myview.findViewById(R.id.ubalance);
                final EditText total = myview.findViewById(R.id.utotal);
                Button submit=myview.findViewById(R.id.usubmit);


                purl.setText(model.getPurl());
                name.setText(model.getName());
                course.setText(model.getCourse());
                email.setText(model.getEmail());
                balance.setText(model.getBalance());
                total.setText(model.getTotal());



                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("purl",purl.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("course",course.getText().toString());
                        map.put("balance",balance.getText().toString());
                        map.put("total",total.getText().toString());



                        FirebaseDatabase.getInstance().getReference().child("clients")
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
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }




    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        TextView name,course,email,balance,total,Date;
        CardView parentlayout;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.nametext);
            course=(TextView)itemView.findViewById(R.id.coursetext);
            email=(TextView)itemView.findViewById(R.id.emailtext);
            balance=(TextView)itemView.findViewById(R.id.availabletext);
            total=(TextView)itemView.findViewById(R.id.totaltext);
            Date=(TextView)itemView.findViewById(R.id.datetext);

            parentlayout=(CardView)itemView.findViewById(R.id.CVparentLayout);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}