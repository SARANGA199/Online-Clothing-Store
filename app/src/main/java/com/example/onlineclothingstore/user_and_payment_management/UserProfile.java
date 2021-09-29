package com.example.onlineclothingstore.user_and_payment_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineclothingstore.MainActivity;
import com.example.onlineclothingstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class UserProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView hnameTextView = (TextView) findViewById(R.id.nameh);
        final TextView nameTextView = (TextView) findViewById(R.id.nametxt);
        final TextView emailTextView = (TextView) findViewById(R.id.emailtxt);
        final TextView phoneTextView = (TextView) findViewById(R.id.phonetxt);
        final TextView addressTextView = (TextView) findViewById(R.id.addresstxt);
        final TextView typeTextView = (TextView) findViewById(R.id.typetxt);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String name = userProfile.fullname;
                    String email = userProfile.email;
                    String phone = userProfile.phone;
                    String address = userProfile.address;
                    int type = userProfile.isUser;

                    hnameTextView.setText(name);
                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    phoneTextView.setText(phone);
                    addressTextView.setText(address);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(UserProfile.this,"Something wrong happened",Toast.LENGTH_LONG).show();

            }
        });

        View EditProf = findViewById(R.id.edit_prof);
        EditProf.setOnClickListener((v) -> {
            Intent i = new Intent(v.getContext(),EditProfile.class);
            i.putExtra("fullname",nameTextView.getText().toString());
            i.putExtra("email",emailTextView.getText().toString());
            i.putExtra("phone",phoneTextView.getText().toString());
            i.putExtra("address",addressTextView.getText().toString());
            startActivity(i);
        });

        View DeleteProf = findViewById(R.id.delete_prof);
        DeleteProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder dialog = new AlertDialog.Builder(UserProfile.this);
                dialog.setTitle("Delete you account");
                dialog.setMessage("Do you want to delete ?");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(UserProfile.this, "Account deleted", Toast.LENGTH_SHORT).show();
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(UserProfile.this, MainActivity.class));
                                }else {
                                    Toast.makeText(UserProfile.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
}