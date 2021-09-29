package com.example.onlineclothingstore.user_and_payment_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineclothingstore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText profilename,profileemail,profilephone,profileaddress;

    FirebaseUser user;
    DatabaseReference reference;
    Button Updateprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_edit_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        Intent data = getIntent();
        String fullname = data.getStringExtra("fullname");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("phone");
        String address = data.getStringExtra("address");

        profilename = findViewById(R.id.editname);
        profileemail = findViewById(R.id.editemail);
        profilephone = findViewById(R.id.editphone);
        profileaddress = findViewById(R.id.editaddress);

        Updateprofile = findViewById(R.id.saveupdate);

        Updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profilename.getText().toString().isEmpty() || profileemail.getText().toString().isEmpty() || profilephone.getText().toString().isEmpty() || profileaddress.getText().toString().isEmpty()) {
                    Toast.makeText(EditProfile.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = profileemail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DatabaseReference dbref = reference.child(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("fullname",profilename.getText().toString());
                        edited.put("phone",profilephone.getText().toString());
                        edited.put("address",profileaddress.getText().toString());
                        dbref.updateChildren(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditProfile.this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),UserProfile.class));
                                finish();
                            }
                        });

                        Toast.makeText(EditProfile.this, "Email is changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(EditProfile.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        profilename.setText(fullname);
        profileemail.setText(email);
        profilephone.setText(phone);
        profileaddress.setText(address);

        Log.d(TAG, "onCreate: "+fullname + "" + email + "" + phone + "" + address);
    }
}