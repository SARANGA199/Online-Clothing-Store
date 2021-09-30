package com.example.onlineclothingstore.request_and_review_management;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.item_and_category_management.DisplayCategoryUser;
import com.example.onlineclothingstore.request_and_review_management.DBHelper.DBHelper;
import com.example.onlineclothingstore.user_and_payment_management.Addedcards;
import com.example.onlineclothingstore.user_and_payment_management.cardform;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class FirstActivity extends AppCompatActivity{

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static ArrayList<DBHelper> arrayList  = new ArrayList<>();
    private RecyclerView list;
    private Button btnCreate;
    public static Activity Fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        arrayList.clear();
        Fa =this;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("Requests");
        getUsers();
        btnCreate = findViewById(R.id.btn_CreateMain);
        list = findViewById(R.id.list);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(),
                new LinearLayoutManager(this).getOrientation());
        list.addItemDecoration(dividerItemDecoration);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ListAdapter(arrayList,this ));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,SecondActivity.class);
                String s;
                s="Create";
                i.putExtra("Button",s);
                startActivity(i);
                finish();
            }
        });

    }
    public void getUsers(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            String type, size, quantity, description;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    size = dataSnapshot.child("size").getValue().toString();
                    type =dataSnapshot.child("type").getValue().toString();
                    quantity = dataSnapshot.child("quantity").getValue().toString();
                    description = dataSnapshot.child("description").getValue().toString();
                    arrayList.add(new DBHelper(type,size,quantity, description));;
                }
                list.setAdapter(new ListAdapter(arrayList,FirstActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //to  stop app get close when pressing back key
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent int1 = new Intent(FirstActivity.this, DisplayCategoryUser.class);
        startActivity(int1);
    }

    public static void cleanList(){
        arrayList.clear();
    }
}