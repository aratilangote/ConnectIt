package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class IncomingCallActivity extends AppCompatActivity {

    SwitchMaterial aSwitch;
    EditText message;
    Button setIN;

    private SharedPreferences preferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);

        aSwitch= findViewById(R.id.my_switchin);
        message=findViewById(R.id.entermsgET_IN);
        setIN=findViewById(R.id.setbtn_IN);

        preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String incomingMessage = preferences.getString("incomingMessage", "");
        message.setText(incomingMessage);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
        }


        aSwitch.setChecked(preferences.getBoolean("isMessageEnabled", true));
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isMessageEnabled", isChecked);
                editor.apply();
                if (isChecked) {
                    Intent intent = new Intent(IncomingCallActivity.this, MyBackgroundService.class);
                    startService(intent);
                } else {
                    stopService(new Intent(IncomingCallActivity.this, MyBackgroundService.class));
                }
            }
        });

        setIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = message.getText().toString();

                Toast.makeText(IncomingCallActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("incomingMessage", msg);
                editor.apply();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(IncomingCallActivity.this, MyBackgroundService.class);
        startService(intent);
    }
}