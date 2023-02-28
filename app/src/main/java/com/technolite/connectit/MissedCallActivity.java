package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MissedCallActivity extends AppCompatActivity {

    SwitchMaterial on_off;

    EditText enter_msg;

    Button set_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_call);

        //Initialize BroadcastReceiver
        //  missedCallReceiver = new MissedCallReceiver(getApplicationContext());


        on_off=findViewById(R.id.my_switch);
        enter_msg=findViewById(R.id.entermsgET);
        set_btn=findViewById(R.id.setbtn);

     /*   on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String message = enter_msg.getText().toString();
                    missedCallReceiver.updateMessage(message);
                } else {
                    missedCallReceiver.clearMessage();
                }
            }
        });

        set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = enter_msg.getText().toString();
                missedCallReceiver.updateMessage(message);
            }
        });*/
    }
}