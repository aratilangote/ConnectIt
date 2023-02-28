package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class OTPVerificationActivity extends AppCompatActivity {

    MaterialButton verify;
   // PinView otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        verify = findViewById(R.id.verify);
        //otp = findViewById(R.id.otp);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String x;
//                x = otp.getText().toString();
//                OTPVerificationActivity.super.onBackPressed();
//                Toast.makeText(OTPVerificationActivity.this, x, Toast.LENGTH_SHORT).show();
            }
        });
    }
}