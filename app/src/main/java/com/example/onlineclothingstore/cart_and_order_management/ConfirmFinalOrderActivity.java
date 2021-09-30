package com.example.onlineclothingstore.cart_and_order_management;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.bookmark.R;
//import com.example.bookmark.category_management.DisplayBooks;
//import com.example.bookmark.payment_management.activity_h2p;
import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.user_and_payment_management.Addedcards;
import com.example.onlineclothingstore.user_and_payment_management.User;
import com.example.onlineclothingstore.user_and_payment_management.UserProfile;
import com.example.onlineclothingstore.user_and_payment_management.cardform;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {
    private FirebaseUser user;
    private EditText nameEditText, phoneEditText, addressEditText, emailEditText;
    private Button confirmOrderBtn;
    private String totalAmount = "";
    private TextView total;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price = Rs."+totalAmount, Toast.LENGTH_SHORT).show();

        confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);
        nameEditText = (EditText)findViewById(R.id.shipment_name);
        phoneEditText =(EditText) findViewById(R.id.shipment_phone);
        addressEditText = (EditText)  findViewById(R.id.shipment_address);
        emailEditText = (EditText) findViewById(R.id.shipment_email);
        total= (TextView) findViewById(R.id.shipment_total);

        total.setText("Total: Rs:"+String.valueOf(totalAmount));

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

                    nameEditText.setText(name);
                    emailEditText.setText(email);
                    phoneEditText.setText(phone);
                    addressEditText.setText(address);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(ConfirmFinalOrderActivity.this,"Something wrong happened",Toast.LENGTH_LONG).show();

            }
        });

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();

            }
        });

    }

    private void Check() {
        if(TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your full name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your address", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(emailEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your Email", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ConfirmOrder();

        }
    }

    private void ConfirmOrder() {

        String SaveCurrentTime,SaveCurrentDate;

        Calendar calForDate =  Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        SaveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(FirebaseAuth.getInstance().getUid());                                //email


        HashMap<String,Object> orderMap = new HashMap<>();
        orderMap.put("totalAmount", totalAmount);
        orderMap.put("name", nameEditText.getText().toString());
        orderMap.put("phone", phoneEditText.getText().toString());
        orderMap.put("address", addressEditText.getText().toString());
        orderMap.put("city", emailEditText.getText().toString());
        orderMap.put("date", SaveCurrentDate);
        orderMap.put("time", SaveCurrentTime);

        ordersRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User View")
                            .child(FirebaseAuth.getInstance().getUid())                 //emal
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(ConfirmFinalOrderActivity.this, "Your final order has been placed successfully.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ConfirmFinalOrderActivity.this, Addedcards.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                }

            }
        });






    }
    //to  stop app get close when pressing back key
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent int1 = new Intent(ConfirmFinalOrderActivity.this, CartActivity.class);
        startActivity(int1);
    }
}