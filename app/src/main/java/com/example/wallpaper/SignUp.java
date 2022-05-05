package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUp extends AppCompatActivity {
    private ImageButton btnReturnSignUp, btnSignUpTrangSignUp;
    private EditText edtUsernameSignUp, edtPasswordSignUp, edtPassword2SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnReturnSignUp = findViewById(R.id.btnReturnSignUp);
        btnSignUpTrangSignUp = findViewById(R.id.btnSignUpTrangSignUp);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPassword2SignUp = findViewById(R.id.edtPassword2SignUp);

        btnReturnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}