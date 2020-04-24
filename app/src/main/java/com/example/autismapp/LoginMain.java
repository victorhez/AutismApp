package com.example.autismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginMain extends AppCompatActivity {
private CircularProgressButton login;
private TextView register,forgetPass;
TextInputLayout editPass, editEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        login= findViewById(R.id.cirLoginButton);
        register= findViewById(R.id.registerNow);
        editEmail=findViewById(R.id.editTextEmail);
        editPass=findViewById(R.id.editTextPassword);
        forgetPass=findViewById(R.id.forgetPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editEmail.getEditText().getText())) {
                    editEmail.setError("Enter Full Name");
                } else if (TextUtils.isEmpty(editPass.getEditText().getText())) {
                    editPass.setError("Enter Phone Number");
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginMain.this, RegisterActivity.class));
            }
        });
    }
}
