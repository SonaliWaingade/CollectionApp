package com.example.collectxnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class ProfileActivity extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;
    private TextView agentName, pro_email, pro_phone, pro_address, pro_userID;
    private ImageView pro_img;
    private Button btn_save,btn_show;
    Uri uriProfileImage;

     DatabaseReference profileUserRef;
    FirebaseAuth fAuth;
  String currentUserID;

    private ImageButton btn_setting;

    ProgressBar progressBar;
    String profileImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fAuth = FirebaseAuth.getInstance();
        currentUserID = fAuth.getCurrentUser().getUid();
        profileUserRef = FirebaseDatabase.getInstance().getReference().child("agent").child(currentUserID);


        /*currentUserId = fAuth.getCurrentUser().getUid();*/
        /*profileUserRef = FirebaseDatabase.getInstance().getReference().child(fAuth.getUid()).child(currentUserId);*/

        agentName = findViewById(R.id.agentName);
        pro_email = findViewById(R.id.pro_email);
        pro_phone = findViewById(R.id.pro_phone);
        pro_address = findViewById(R.id.pro_address);
        pro_userID = findViewById(R.id.pro_userID);

        btn_show = findViewById(R.id.btn_show);


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileUserRef = FirebaseDatabase.getInstance().getReference().child("agent").child("id");
                profileUserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String email=dataSnapshot.child("email").getValue().toString();
                        String phone=dataSnapshot.child("contactNumber").getValue().toString();
                        String address=dataSnapshot.child("address").getValue().toString();
                        String userID=dataSnapshot.child("userID").getValue().toString();

                        pro_email.setText(email);
                        pro_phone.setText(phone);
                        pro_address.setText(address);
                        pro_userID.setText(userID);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        /*btn_save = findViewById(R.id.btn_save);*/

      /*  pro_img = findViewById(R.id.pro_img);

        progressBar = findViewById(R.id.progress_bar);

        btn_setting = findViewById(R.id.img_button_setting);


       profileUserRef.child("agent").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("fullName") && (dataSnapshot.hasChild("userID")) &&
                        (dataSnapshot.hasChild("address")) && (dataSnapshot.hasChild("contactNumber")) && (dataSnapshot.hasChild("email"))))
                {
                    String Name = profileUserRef.child("agent").child("FullName").push().getKey();
                    profileUserRef.child(Name);
                    /*String Name = dataSnapshot.child("fullName").getValue().toString();*/
                  /*  String txtemail = dataSnapshot.child("email").getValue().toString();
                    String contact = dataSnapshot.child("contactNumber").getValue().toString();
                    String addr = dataSnapshot.child("address").getValue().toString();
                    String ID = dataSnapshot.child("userID").getValue().toString();



                    agentName.setText(Name);
                    pro_email.setText(txtemail);
                    pro_phone.setText(contact);
                    pro_address.setText(addr);
                    pro_userID.setText(ID);
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Update profile information", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    /*   loadUserInformation();*/
        pro_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();

            }
        });

        /*btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveUserInformation();
               {

                }
            }
        });*/
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerIntent = new Intent(ProfileActivity.this, Pro_Setting_Activity.class);
                startActivity(scannerIntent);

            }
        });


    }



    private void saveUserInformation() {
        String fullName = agentName.getText().toString().trim();
        String userID = pro_userID.getText().toString().trim();
        String address = pro_address.getText().toString().trim();
        String contactNumber = pro_phone.getText().toString().trim();
        String email = pro_email.getText().toString().trim();



        if (fullName.isEmpty()){
            agentName.setError("Name required");
            agentName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            pro_email.setError("Name required");
            pro_email.requestFocus();
            return;
        }
        if (contactNumber.isEmpty()){
            pro_phone.setError("Name required");
            pro_phone.requestFocus();
            return;
        }

        if (address.isEmpty()){
            pro_address.setError("Name required");
            pro_address.requestFocus();
            return;
        }

        if (userID.isEmpty()){
            pro_userID.setError("Name required");
            pro_userID.requestFocus();
            return;
        }




        FirebaseUser user= fAuth.getCurrentUser();

        if (user != null && profileImageUrl != null){
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(fullName)
                    .setPhotoUri(Uri.parse(profileImageUrl))
                    .build();

            user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                     startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }else {
                                       Toast.makeText(ProfileActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                }
            });
        }
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        if (fAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }*/


    private void loadUserInformation() {
        FirebaseUser user = fAuth.getCurrentUser();

        String fullName = user.getDisplayName().toString().trim();
        String userID = user.getDisplayName().toString().trim();
        String address = user.getDisplayName().toString().trim();
        String contactNumber = user.getPhoneNumber().toString().trim();
        String email = user.getEmail().toString().trim();


        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString())
                        .into(pro_img);
            }

            if (user.getDisplayName() != null) {
                agentName.setText(fullName);


              /*  pro_address.setText(address);
                pro_userID.setText(userID);*/
            }

            if (user.getEmail() != null){

                pro_email.setText(email);
            }

            if (user.getPhoneNumber() != null){
                pro_phone.setText(contactNumber);
            }


    }

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData()!=null){
           uriProfileImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                pro_img.setImageBitmap(bitmap);

                uploadImageToFirebasestorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebasestorage() {
        final StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis() + ".jpg");
        if (uriProfileImage != null){
            progressBar.setVisibility(View.VISIBLE);
            profileImageRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.GONE);

                    profileImageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String profileImageUrl = task.getResult().toString();
                            Log.i("URL",profileImageUrl);
                        }
                    });

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SelectProfile Image"), CHOOSE_IMAGE);
    }



}
