package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    Spinner selectSim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        selectSim = findViewById(R.id.select_sim);

    }
}