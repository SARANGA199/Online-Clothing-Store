package com.example.onlineclothingstore.item_and_category_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.databinding.ActivityAddCategoryBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddCategory extends AppCompatActivity {

    //view binding
    private ActivityAddCategoryBinding binding;

    //firebase auth


    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.CAT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCategory.this,Home.class));
            }
        });

        //init firebase

        //configure progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Pls Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        //handle click
        binding.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }



    private String category=" ";
    private String description = " ";


    private void validateData() {
        category = binding.categoryET.getText().toString().trim();
        description = binding.categoryET1.getText().toString().trim();
        //validate

        if(TextUtils.isEmpty(category)){
            Toast.makeText(this, "Please enter category", Toast.LENGTH_SHORT).show();
        }else{
            addCategoryFirebase();
        }

    }

    private void addCategoryFirebase() {
        //show
        progressDialog.setMessage("Adding category");
        progressDialog.show();

        //get timestamp
        long timestamp = System.currentTimeMillis();

        //add to firebase
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",""+timestamp);
        hashMap.put("category",""+category);
         hashMap.put("Description",""+description);
        hashMap.put("timestamp",""+timestamp);

        //upload
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.child(""+timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //add successfull
                        progressDialog.dismiss();
                        Toast.makeText(AddCategory.this, "Category added successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(AddCategory.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}