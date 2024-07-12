package com.trycatch_vishal.fooddelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText signup_username_edittext, signup_email_edittext, signup_password_edittext, signup_confpassword_edittext;
    Button signup_button;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    // email validator
    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.com";
    String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth

        signup_username_edittext = findViewById(R.id.signup_username_edittext);
        signup_email_edittext = findViewById(R.id.signup_email_edittext);
        signup_password_edittext = findViewById(R.id.signup_password_edittext);
        signup_confpassword_edittext = findViewById(R.id.signup_confpassword_edittext);
        signup_button = findViewById(R.id.signup_button); // Initialize signup button

        progressDialog = new ProgressDialog(this);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performsignup();
            }
        });
    }

    private void performsignup() {
        String email = signup_email_edittext.getText().toString().trim();
        String password = signup_password_edittext.getText().toString().trim();
        String conf = signup_confpassword_edittext.getText().toString().trim();

        // id

        // Check if email and password fields are empty
        if (email.isEmpty()) {
            signup_email_edittext.setError("Email is required");
            signup_email_edittext.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            signup_password_edittext.setError("Password is required");
            signup_password_edittext.requestFocus();
            return;
        }
        if (conf.isEmpty()) {
            signup_confpassword_edittext.setError("Confirm password is required");
            signup_confpassword_edittext.requestFocus();
            return;
        }

        // Check if email is in correct format
        if (!email.matches(emailPattern)) {
            signup_email_edittext.setError("Enter correct E-mail, Your E-mail should end with .com");
            signup_email_edittext.requestFocus();
            return;
        }
        // Password validation
        if (!password.matches(passwordPattern)) {
            signup_password_edittext.setError("Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 6 characters long.");
            signup_password_edittext.requestFocus();
            return;
        }
        // Check if passwords match
        if (!password.equals(conf)) {
            signup_confpassword_edittext.setError("Passwords do not match");
            signup_confpassword_edittext.requestFocus();
            return;
        }

        progressDialog.setMessage("Please wait while Sign up");
        progressDialog.setTitle("Sign up");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    sendUserToNextActivity();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
