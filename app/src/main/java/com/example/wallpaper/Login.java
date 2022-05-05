package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private ImageButton btnReturnSignIn, btnSignInSignIn;
    private EditText edtUsernameSignUp, edtPasswordSignUp;
    private TextView txtSignUpSignIn, txtForgotPassSignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnReturnSignIn = findViewById(R.id.btnReturnSignIn);
        btnSignInSignIn = findViewById(R.id.btnSigninTrangLogin);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        txtSignUpSignIn = findViewById(R.id.txtSignUpSignIn);
        txtForgotPassSignin = findViewById(R.id.txtForgotPassSignin);
    }
}