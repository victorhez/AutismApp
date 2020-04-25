package com.example.autismapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private TextInputLayout password, name, phone, email, repassword;
    private TextView haveAcc;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_register);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseStorage= FirebaseStorage.getInstance();
        firestore= FirebaseFirestore.getInstance();
        register= findViewById(R.id.registerBtn);
        haveAcc=findViewById(R.id.haveAccount);
        password=findViewById(R.id.passwordReg);
        repassword=findViewById(R.id.confirmPassReg);
        name=findViewById(R.id.nameRegister);
        phone=findViewById(R.id.phoneNumber);
        email=findViewById(R.id.emailReg);
        if(firebaseAuth.getCurrentUser()!=null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        else {


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
                    } else {
                        String emaila = email.getEditText().getText().toString().trim();
                        String passr = password.getEditText().getText().toString().trim();
                        firebaseAuth.createUserWithEmailAndPassword(emaila,passr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "SUCCESSFUL AUTHENTICATION", Toast.LENGTH_LONG).show();
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    fireStoreme();
                                    Toast.makeText(RegisterActivity.this," Please Wait: Loading " ,Toast.LENGTH_LONG).show();
                                }
                            }
                        });
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

    private void fireStoreme() {
        Map<String, Object> client= new HashMap<>();
        client.put("Name", name.getEditText().getText().toString());
        client.put("Email", email.getEditText().getText().toString());
        client.put("PhoneNumber", phone.getEditText().getText().toString());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressDialog progressDialog= new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Checking all credentials...");
                progressDialog.setTitle("Check Bot");
                progressDialog.setProgress(50);
                progressDialog.show();
            }
        },4000);
        Toast.makeText(RegisterActivity.this," Please Wait: Loading " ,Toast.LENGTH_LONG).show();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name.getEditText().getText().toString()).build();
        user.updateProfile(profileUpdates);
        DocumentReference documentReference=firestore.collection("Clients").document(user.getUid());
        documentReference.set(client).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(RegisterActivity.this,"SUCCESSFUL FireStore", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this,"Error Occurred: " + e, Toast.LENGTH_LONG).show();

            }
        });
    }
    }
