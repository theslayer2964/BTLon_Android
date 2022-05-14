package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private ImageButton btnReturnSignUp, btnSignUpTrangSignUp;
    private EditText edtUsernameSignUp, edtPasswordSignUp, edtPassword2SignUp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mapping();
        firebaseAuth = FirebaseAuth.getInstance();

        btnReturnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, WrapperActivity.class);
                startActivity(intent);
            }
        });
        btnSignUpTrangSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String user = edtUsernameSignUp.getText().toString().trim();
        String pass1 = edtPasswordSignUp.getText().toString().trim();
        String pass2 = edtPassword2SignUp.getText().toString().trim();
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass1) || TextUtils.isEmpty(pass2)){
            Toast.makeText(this, "Please fill out this form", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pass1.equals(pass2)){
            Toast.makeText(this, "password incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(user, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Register Succeed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this, Login.class));
                }
                else
                    Toast.makeText(SignUp.this, "Try again!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        btnReturnSignUp = findViewById(R.id.btnReturnSignUp);
        btnSignUpTrangSignUp = findViewById(R.id.btnSignUpTrangSignUp);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPassword2SignUp = findViewById(R.id.edtPassword2SignUp);
    }
}