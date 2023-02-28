package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class SignupActivity extends AppCompatActivity {

    MaterialTextView signin_activity;
    TextInputLayout verify_phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        verify_phoneno = findViewById(R.id.phone_no);
        signin_activity = findViewById(R.id.signin_activity);

        verify_phoneno.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SignupActivity.this, OTPVerificationActivity.class);
//                startActivity(intent);
            }
        });

        signin_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}