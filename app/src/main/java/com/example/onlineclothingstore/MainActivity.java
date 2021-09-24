package com.example.onlineclothingstore;

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

import com.example.onlineclothingstore.user_and_payment_management.Addedcards;
import com.example.onlineclothingstore.user_and_payment_management.Forgotpassword;
import com.example.onlineclothingstore.user_and_payment_management.Profile;
import com.example.onlineclothingstore.user_and_payment_management.Register;
import com.example.onlineclothingstore.user_and_payment_management.UserProfile;
import com.example.onlineclothingstore.user_and_payment_management.cardform;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //redirect to profile
                    startActivity(new Intent(MainActivity.this, Profile.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to login!.Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}