package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.card.MaterialCardView;

public class NetworkSMSActivity extends AppCompatActivity {

    MaterialCardView missedcall,incomingcall,outgoingcall;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_smsactivity);

        incomingcall=findViewById(R.id.incomingbtn);
        outgoingcall=findViewById(R.id.outgoingbtn);

        //Getting required permission
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.SEND_SMS, Manifest.permission.READ_CALL_LOG}, PackageManager.PERMISSION_GRANTED);

        incomingcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NetworkSMSActivity.this, IncomingCallActivity.class));
            }
        });
        outgoingcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NetworkSMSActivity.this, OutgoingCallActivity.class));
            }
        });
    }
}