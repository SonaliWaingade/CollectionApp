package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Pro_Setting_Activity extends AppCompatActivity {


   private ImageView pro_img;
   private Button btn_save;
   private TextView  emailET, phoneET, addressET, userIDET;
   private TextView agentNameET;

private FirebaseUser user;

    private DatabaseReference settingUserRef;
    private FirebaseAuth fAuth;
    private StorageReference userProfileImgRef;


    private String currentUserID;
    private static final int GalleryPick = 1;
    private Uri uri;

    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro__setting_);


        fAuth = FirebaseAuth.getInstance();




        currentUserID = fAuth.getCurrentUser().getUid();
        settingUserRef = FirebaseDatabase.getInstance().getReference().child("agent");
        userProfileImgRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        agentNameET = findViewById(R.id.agentName);
        emailET = findViewById(R.id.pro_email);
        phoneET = findViewById(R.id.pro_phone);
        addressET = findViewById(R.id.pro_address);
        userIDET = findViewById(R.id.pro_userID);



        loadingBar = new ProgressDialog(this);


        btn_save = findViewById(R.id.btn_save);

        pro_img = findViewById(R.id.pro_img);




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
          RetrieveUserInfo();

        pro_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(Pro_Setting_Activity.this);
              /*Intent gallerIntent = new Intent();
                gallerIntent.setAction(Intent.ACTION_GET_CONTENT);
                gallerIntent.setType("image/*");
                startActivityForResult(gallerIntent, GalleryPick);*/
                /*CropImage.startPickImageActivity(Pro_Setting_Activity.this );*/

            }
        });


    }

    private void RetrieveUserInfo() {
        settingUserRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("fullName"))){
                    String userImage = dataSnapshot.child("image").getValue().toString();
                    String userName = dataSnapshot.child("fullName").getValue().toString();
                    String userEmail = dataSnapshot.child("email").getValue().toString();

                    Picasso.get().load(userImage).placeholder(R.drawable.profile).into(pro_img);
                    agentNameET.setText(userName);
                    emailET.setText(userEmail);
                }
                else {
                   Toast.makeText(Pro_Setting_Activity.this, "Hey", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GalleryPick && resultCode == RESULT_OK && data!=null) {

            Uri ImageUri = data.getData();

           return;

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK ){
            final CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK){



                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                final StorageReference filePath = userProfileImgRef.child(currentUserID + ".jpg");
                Uri resultUri = result.getUri();
                Uri ImageUri = data.getData();


                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){

                            final String downloadUrl = task.getResult().getStorage().getDownloadUrl().toString();

                            settingUserRef.child("agent").child(currentUserID).child("image")
                                    .setValue(downloadUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Pro_Setting_Activity.this, "Image Saved", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                    else
                                    {
                                        String message = task.getException().toString();
                                        Toast.makeText(Pro_Setting_Activity.this, "Error:"+ message, Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }

                                }
                            });



                        }
                        else
                        {
                            String message = task.getException().toString();
                            Toast.makeText(Pro_Setting_Activity.this, "Error!", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }

                    }
                });
            }
        }
    }

    /*private void RetrieveUserInfo() {
        settingUserRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild(("fullName")) )){


                    String name = String.valueOf(dataSnapshot.getValue(customer.class));


                    String retrieveName = dataSnapshot.child("fullName").getValue().toString();
                    String retrieveEmail = dataSnapshot.child("email").getValue().toString();
                    String retrievePhone = dataSnapshot.child("contactNumber").getValue().toString();
                    String retrieveAddress = dataSnapshot.child("address").getValue().toString();
                    String retrieveUser = dataSnapshot.child("userID").getValue().toString();

                    String retrieveProImg = dataSnapshot.child("image").getValue().toString();


                    agentNameET.setText(retrieveName);
                    emailET.setText(retrieveEmail);
                    phoneET.setText(retrievePhone);
                    addressET.setText(retrieveAddress);
                    userIDET.setText(retrieveUser);




                    Picasso.get().load(retrieveProImg).into(pro_img);
                    Picasso.get().load(retrieveProImg).placeholder(R.drawable.profile).into(pro_img);

                }
                else if((dataSnapshot.exists()) && (dataSnapshot.hasChild(("email"))))
                {

                    String retrieveName = dataSnapshot.child("fullName").getValue().toString();
                    String retrieveEmail = dataSnapshot.child("email").getValue().toString();
                    String retrievePhone = dataSnapshot.child("contactNumber").getValue().toString();
                    String retrieveAddress = dataSnapshot.child("address").getValue().toString();
                    String retrieveUser = dataSnapshot.child("userID").getValue().toString();


                    agentNameET.setText(retrieveName);
                    emailET.setText(retrieveEmail);
                    phoneET.setText(retrievePhone);
                    addressET.setText(retrieveAddress);
                    userIDET.setText(retrieveUser);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }*/


 /*  private void startCrop(Uri imageuri) {
        CropImage.activity(imageuri).setGuidelines(CropImageView.Guidelines.ON).setMultiTouchEnabled(true).start(this);
    }*/


}
