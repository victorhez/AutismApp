package com.example.autismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
private Button register;
private TextInputLayout password, name,phone, email, repassword;
private TextView haveAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register= findViewById(R.id.registerBtn);
        haveAcc=findViewById(R.id.haveAccount);
        password=findViewById(R.id.passwordReg);
        repassword=findViewById(R.id.confirmPassReg);
        name=findViewById(R.id.nameRegister);
        phone=findViewById(R.id.phoneNumber);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getEditText().getText())) {
                    name.setError("Enter Full Name");
                } else if (TextUtils.isEmpty(phone.getEditText().getText())) {
                    phone.setError("Enter Phone Number");
                } else if (TextUtils.isEmpty(email.getEditText().getText())) {
                    email.setError("Enter Email");
                } else if (!email.getEditText().getText().toString().contains("@")) {
                    email.setError("Your Email must be valid");
                } else if (TextUtils.isEmpty(password.getEditText().getText())) {
                    password.setError("Enter Password ");
                } else if (TextUtils.isEmpty(repassword.getEditText().getText())) {
                    repassword.setError("Confirm Password ");
                } else if (password.getEditText().getText().toString().trim().length() < 6) {
                    password.setError("Password is Must be up to Six Characters");
                    repassword.setError("Password is Must be up to Six Characters");
                } else if (!TextUtils.equals(password.getEditText().getText().toString().trim(), repassword.getEditText().getText().toString().trim())) {
                    password.setError("Password is not the Same");
                    repassword.setError("Password is not the Same");
                }
                else {

                }
            }
        });
        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginMain.class));
            }
        });
    }
}
