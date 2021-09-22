package com.example.onlineclothingstore.user_and_payment_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineclothingstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cardform extends AppCompatActivity {

    private TextView addcardbtn;
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


        paymentCards = FirebaseDatabase.getInstance().getReference().child("paymentCards");

        addcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPaymentCard();
            }
        });

    }

    private void addPaymentCard() {
        String name = editTextCardName.getText().toString().trim();
        String number = editCardNumber.getText().toString().trim();
        String exp = editTextexpdate.getText().toString().trim();
        String cv  = editTextCvv.getText().toString().trim();

        if(name.isEmpty()){
            editTextCardName.setError("Full name is required");
            editTextCardName.requestFocus();
            return;
        }

        if(number.isEmpty()){
            editCardNumber.setError("Email is required");
            editCardNumber.requestFocus();
            return;
        }

        if(exp.isEmpty()){
            editTextexpdate.setError("Phone number is required");
            editTextexpdate.requestFocus();
            return;
        }

        if(cv.isEmpty()){
            editTextCvv.setError("Address is required");
            editTextCvv.requestFocus();
            return;
        }

        Cards cards = new Cards(name,number,exp,cv);

        paymentCards.push().setValue(cards);
        Toast.makeText(cardform.this, "Card added",Toast.LENGTH_LONG).show();
    }
}