package com.trycatch_vishal.fooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText login_email_edittext, login_password_edittext;
    Button login_button;
    ImageView gsigimg, fsigimg;
    TextView forgot_password, needtosign;
    CheckBox remember_checkbox;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.com";
    String passwordPattern = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{6,}$";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email_edittext = findViewById(R.id.login_email_edittext);
        login_password_edittext = findViewById(R.id.login_password_edittext);
        login_button = findViewById(R.id.login_button);
        gsigimg = findViewById(R.id.gsigimg);
        fsigimg = findViewById(R.id.fsigimg);
        forgot_password = findViewById(R.id.forgot_password);
        remember_checkbox = findViewById(R.id.remember_checkbox);
        needtosign = findViewById(R.id.needtosign);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Check if remember me is checked and retrieve saved email and password
        if (sharedPreferences.getBoolean("isChecked", false)) {
            remember_checkbox.setChecked(true);
            login_email_edittext.setText(sharedPreferences.getString("email", ""));
            login_password_edittext.setText(sharedPreferences.getString("password", ""));
        }

        needtosign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email_edittext.getText().toString();
                progressDialog.setTitle("Sending Mail");
                progressDialog.show();

                mAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.cancel();
                                Toast.makeText(LoginActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performlogin();

            }
        });
        gsigimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GoogleActivity.class);
                startActivity(intent);

            }
        });
    }

    private void performlogin() {
        String email = login_email_edittext.getText().toString();
        String password = login_password_edittext.getText().toString();
        // Save email and password if remember me is checked
        if (remember_checkbox.isChecked()) {
            editor.putBoolean("isChecked", true);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();
        } else {
            editor.clear().apply();
        }

        if (TextUtils.isEmpty(email)) {
            login_email_edittext.setError("Enter your email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            login_password_edittext.setError("Enter your password");
            return;
        }

//        //here we will check either the input feild are empty or not from user side to validate the data
//        if (!email.matches(emailPattern)) {
//            login_email_edittext.setError("Enter correct E-mail, Your E-mail should end with .com");
//            login_email_edittext.requestFocus();
//        }// Password validation
//        if (!password.matches(passwordPattern)) {
//            login_password_edittext.setError("Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 6 characters long.");
//            login_password_edittext.requestFocus();
//
//        } else {
        progressDialog.setMessage("Please wait while Login");
        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //}

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}