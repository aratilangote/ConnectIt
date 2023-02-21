package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    Button btnAllbuttons;

    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //All buttons activity
        btnAllbuttons = findViewById(R.id.goto_allbuttons);
        btnAllbuttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AllButtonsActivity.class));
            }
        });

        //tootbar settings
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ConnectIt");
        setSupportActionBar(toolbar);

        //drawerlayout setiings
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //navigationview settings
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_premium:
                Toast.makeText(this, "Premium", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_business_info:
                Toast.makeText(this, "Business Info", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_wp_msg:
                Toast.makeText(this, "Whatsapp msgs", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_sms_schedule:
                Toast.makeText(this, "Sms scheduler", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_bulk_sms:
                Toast.makeText(this, "Bulk msgs", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            case R.id.nav_permission:
                Toast.makeText(this, "Permission", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_aboutUs:
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_referFriend:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}