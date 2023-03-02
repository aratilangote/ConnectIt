package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpgradePremiumActivity_2 extends AppCompatActivity {

    TextView generatekey;
    Button activateplan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_premium2);

        generatekey = findViewById(R.id.generatekey);
        generatekey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wpurl="https://wa.me/+917028931588?text=Hello, I am Kundan send me activation licence key as soon as possible my registration details given below.\n"
                        +"Email:1910032@ritindia.edu.\n"
                        +"Mobile:7028931583.\n"
                        +"Serial Number:11133.";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });


        activateplan = findViewById(R.id.activateplan);
        activateplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}