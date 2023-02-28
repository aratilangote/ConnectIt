package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class OutgoingCallActivity extends AppCompatActivity {

    SwitchMaterial Oswitch;

    EditText Omsg;

    Button Osetbtn;

    private SharedPreferences preferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing_call);

        Oswitch=findViewById(R.id.my_switch_on);
        Omsg=findViewById(R.id.entermsgET_ON);
        Osetbtn=findViewById(R.id.setbtn_ON);

        preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String incomingMessage = preferences.getString("outgoingMessage", "");
        Omsg.setText(incomingMessage);

        Oswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isMessageEnabled", isChecked);
                    editor.apply();
                }
            }
        });

        Osetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = Omsg.getText().toString();
                Toast.makeText(OutgoingCallActivity.this, ""+Omsg, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("outgoingMessage", msg);
                editor.apply();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(OutgoingCallActivity.this, MyBackgroundServiceOutgoing.class);
        startService(intent);
    }
}