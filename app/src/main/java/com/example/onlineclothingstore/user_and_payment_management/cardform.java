package com.example.onlineclothingstore.user_and_payment_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineclothingstore.MainActivity;
import com.example.onlineclothingstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class cardform extends AppCompatActivity {

    private static final Pattern NAME_PATTERN =
            Pattern.compile(".\\d.");

    private static final Pattern NUM_PATTERN =
            Pattern.compile("\\d+");

    private static final Pattern CVV_PATTERN =
            Pattern.compile("\\d+"+           //no white spaces
                    ".{3}");

    private static final Pattern EXP_PATTERN =
            Pattern.compile("[0-9][0-9]+/+[0-9][0-9]$");

    private TextView addcardbtn,mycardsbtn;
    private EditText editTextCardName, editCardNumber, editTextexpdate, editTextCvv;

    DatabaseReference paymentCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardform);

        editTextCardName = findViewById(R.id.cardname);
        editCardNumber = findViewById(R.id.cardnum);
        editTextexpdate = findViewById(R.id.expdate);
        editTextCvv = findViewById(R.id.cvv);
        addcardbtn = findViewById(R.id.addcardbtn);
        mycardsbtn = findViewById(R.id.allcardsbtn);


        paymentCards = FirebaseDatabase.getInstance().getReference().child("paymentCards");

        addcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPaymentCard();
            }
        });

        mycardsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cardform.this, Addedcards.class));
            }
        });

    }

    private void addPaymentCard() {
        String name = editTextCardName.getText().toString().trim();
        String number = editCardNumber.getText().toString().trim();
        String exp = editTextexpdate.getText().toString().trim();
        String cv  = editTextCvv.getText().toString().trim();

        if(name.isEmpty()){
            editTextCardName.setError("Card name is required");
            editTextCardName.requestFocus();
            return;
        } else if (NAME_PATTERN.matcher(name).matches()) {
            editTextCardName.setError("Incorrect Name");
            return;
        }

        if(number.isEmpty()) {
            editCardNumber.setError("Card number required");
            editCardNumber.requestFocus();
            return;
        }else if (!NUM_PATTERN.matcher(number).matches()) {
            editCardNumber.setError("Invalid Card Number");
            return;
            }

        int len = editTextexpdate.length();

        if(exp.isEmpty()){
            editTextexpdate.setError("Field can't be empty");
            editTextexpdate.requestFocus();
            return;
        } else if (!EXP_PATTERN.matcher(exp).matches() || len!=5) {
            editTextexpdate.setError("Invalid Date! use correct format");
            return;
        }

        int lencv = editTextCvv.length();
        if(cv.isEmpty()){
            editTextCvv.setError("Address is required");
            editTextCvv.requestFocus();
            return;
        }else if (CVV_PATTERN.matcher(cv).matches() || lencv!=3) {
            editTextCvv.setError("Invalid CVV Number");
            return;
        }

        String encryptedNumber = "";
        String sourceStr = number;
        try {
            encryptedNumber = AESUtils.encrypt(sourceStr);
            System.out.println("TEST" + "encrypted:" + encryptedNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cards cards = new Cards(name,encryptedNumber,exp,cv);

        paymentCards.push().setValue(cards);
        Toast.makeText(cardform.this, "Card added",Toast.LENGTH_LONG).show();
    }

//    ///////
//    private boolean isValid(){
//
//        boolean valid = false;
//
//        String NumInput = editCardNumber.getText().toString().trim();
//        String CvvInput = editTextCvv.getText().toString().trim();
//        String ExpInput = editTextexpdate.getText().toString().trim();
//
//        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("Cards");
//        Query checkCard = dataRef.orderByChild("number").equalTo(NumInput);
//
//
//
//        checkCard.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()){
//
//                    String cvvDB = snapshot.child(NumInput).child("cv").getValue(String.class);
//                    String expDB = snapshot.child(NumInput).child("expdate").getValue(String.class);
//
//                    if(cvvDB.equals(CvvInput) && expDB.equals(ExpInput) ){
//                        boolean valid = true;
//                        Toast.makeText(cardform.this, "Data Matched", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//
//        return valid;
//    }
//    ///////

}