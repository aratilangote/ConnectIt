package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Bulk_whatsapp_msg_Activity extends AppCompatActivity {

    TextInputLayout mobileNumbers;
    Button setwp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_whatsapp_msg);

        mobileNumbers = findViewById(R.id.mobileNumbersLayout);
        setwp = findViewById(R.id.schedule_btn);
        mobileNumbers.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Bulk_whatsapp_msg_Activity.this, "To do when end icon is clicked upon", Toast.LENGTH_SHORT).show();
            }
        });

        setwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Bulk_whatsapp_msg_Activity.this, "Comming Soon", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }
}