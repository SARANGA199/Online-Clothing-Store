package com.example.onlineclothingstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineclothingstore.item_and_category_management.AddPhone;
import com.example.onlineclothingstore.item_and_category_management.DisplayCategory;
import com.example.onlineclothingstore.item_and_category_management.DisplayCategoryUser;
import com.example.onlineclothingstore.request_and_review_management.FirstActivity;
import com.example.onlineclothingstore.request_and_review_management.SecondActivity;
import com.example.onlineclothingstore.user_and_payment_management.Forgotpassword;
import com.example.onlineclothingstore.user_and_payment_management.Register;
import com.example.onlineclothingstore.user_and_payment_management.cardform;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.loginbtn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.lgemail);
        editTextPassword = (EditText) findViewById(R.id.lgpassword);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forget_pw);
        forgotPassword.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.loginbtn:
                userLogin();
                break;
            case R.id.forget_pw:
                startActivity(new Intent( this,Forgotpassword.class));
                break;
        }
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        //show
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    String uid = task.getResult().getUser().getUid();
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("Users").child(uid).child("isUser").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            int usertype = snapshot.getValue(Integer.class);
                            if(usertype == 1){
                                progressDialog.dismiss();
                                Intent int1 =new Intent(MainActivity.this, DisplayCategoryUser.class);
                                startActivity(int1);
                            }
                            else{
                                progressDialog.dismiss();
                                Intent int2 =new Intent(MainActivity.this, DisplayCategory.class);
                                startActivity(int2);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

//                    redirect to profile
//                    startActivity(new Intent(MainActivity.this, Profile.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to login!.Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}