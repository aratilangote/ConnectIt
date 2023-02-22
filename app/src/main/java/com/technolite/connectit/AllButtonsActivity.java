package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllButtonsActivity extends AppCompatActivity {

    Button BtnBithday, BtnSettings, BtnPermission, BtnView, BtnWhatsapp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_buttons);

        BtnBithday = findViewById(R.id.btn_birthday);
        BtnSettings = findViewById(R.id.btn_settings);
        BtnPermission = findViewById(R.id.btn_permissions);
        BtnView = findViewById(R.id.btn_view);
        BtnWhatsapp = findViewById(R.id.btn_whatsapp);


        BtnBithday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        BtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, SettingsActivity.class));
            }
        });

        BtnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        BtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        BtnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, WhatsappActivity.class));
            }
        });
    }
}