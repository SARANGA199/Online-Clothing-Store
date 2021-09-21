package com.example.onlineclothingstore.user_and_payment_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineclothingstore.R;
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
                    String type = userProfile.isUser;

                    hnameTextView.setText(name);
                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    phoneTextView.setText(phone);
                    addressTextView.setText(address);
                    typeTextView.setText(type);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(UserProfile.this,"Something wrong happened",Toast.LENGTH_LONG).show();

            }
        });
    }
}