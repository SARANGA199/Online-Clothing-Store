package com.example.onlineclothingstore.request_and_review_management;



import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.request_and_review_management.DBHelper.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Type;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {
    private Intent i ;
    private String s;
    private EditText txtType,txtSize,txtQuantity, txtDescription;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String request="Requests";
    private String type,size,quantity,description;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button= findViewById(R.id.btnSubmit);
        txtType = (EditText)findViewById(R.id.editTxtType);
        txtSize =(EditText)findViewById(R.id.editTxtSize);
        txtQuantity =(EditText)findViewById(R.id.editTxtQuantity);
        txtDescription =(EditText)findViewById(R.id.editTxtDescription);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference(request);
        button.setText("Submit");
        i = getIntent();
        s=i.getStringExtra("Button");
        if (s.equals("Create")){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = txtType.getText().toString();
                    size =  txtSize.getText().toString();
                    quantity = txtQuantity.getText().toString();
                    description = txtDescription.getText().toString();

                    databaseReference.child(type).setValue(new DBHelper(type,size,quantity,description));
                    Toast.makeText(SecondActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                    i = new Intent(SecondActivity.this,FirstActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        }else if (s.equals("Edit")){
            String temp;
            HashMap hashMap =  new HashMap();
            type = i.getStringExtra("type");
            temp = type;
            size = i.getStringExtra("size");
            quantity = i.getStringExtra("quantity");
            description = i.getStringExtra("description");
            txtType.setText(type);
            txtSize.setText(size);
            txtQuantity.setText(quantity);
            txtDescription.setText(description);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = txtType.getText().toString();
                    size = txtSize.getText().toString();
                    quantity = txtQuantity.getText().toString();
                    description = txtDescription.getText().toString();
                    hashMap.put("type",type);
                    hashMap.put("size",size);
                    hashMap.put("quantity",quantity);
                    hashMap.put("description",description);
                    databaseReference.child(temp).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            i = new Intent(SecondActivity.this,FirstActivity.class);
                            if (task.isSuccessful()){
                                Toast.makeText(SecondActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SecondActivity.this,"Failed to update data",Toast.LENGTH_SHORT).show();
                            }
                            startActivity(i);
                            finish();
                        }
                    });
                }
            });
        }else if (s.equals("Read")){
            type = i.getStringExtra("type");
            size = i.getStringExtra("size");
            quantity = i.getStringExtra("quantity");
            description = i.getStringExtra("description");
            button.setText("OK");
            txtType.setText(type);
            txtSize.setText(size);
            txtQuantity.setText(quantity);
            txtDescription.setText(description);
            txtType.setEnabled(false);
            txtSize.setEnabled(false);
            txtQuantity.setEnabled(false);
            txtDescription.setEnabled(false);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(SecondActivity.this,FirstActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        Intent j = new Intent(SecondActivity.this,FirstActivity.class);
        startActivity(j);
        finish();
        super.onBackPressed();
    }
}