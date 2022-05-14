package com.example.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private ImageButton btnReturnSignIn, btnSignInSignIn;
    private EditText edtUsernameSignUp, edtPasswordSignUp;
    private TextView txtSignUpSignIn, txtForgotPassSignin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();

        firebaseAuth = FirebaseAuth.getInstance();

        btnReturnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, WrapperActivity.class);
                startActivity(intent);
            }
        });
        btnSignInSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        txtSignUpSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }

    private void login() {
        String user = edtUsernameSignUp.getText().toString().trim();
        String pass = edtPasswordSignUp.getText().toString().trim();
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Please fill out this form", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Succeed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, ListActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Login.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        btnReturnSignIn = findViewById(R.id.btnReturnSignIn);
        btnSignInSignIn = findViewById(R.id.btnSigninTrangLogin);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        txtSignUpSignIn = findViewById(R.id.txtSignUpSignIn);
        txtForgotPassSignin = findViewById(R.id.txtForgotPassSignin);
    }
}