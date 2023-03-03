package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

//    Button BtnPremium, BtnNetworkSMS, BtnBithday, BtnSettings, BtnPermission, BtnBulkWhatsapp, BtnWhatsapp, BtnEazypost, BtnDownloads;


    //Gridview
    GridView gridview;


    @SuppressLint({"ResourceAsColor", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //tootbar settings
        toolbar = findViewById(R.id.toolbar);
       // toolbar.setTitle("ConnectIt");
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


        //Gridview
        gridview = findViewById(R.id.grid);
        ArrayList<demo> courseModelArrayList = new ArrayList<demo>();

        courseModelArrayList.add(new demo("Upgrade to premium", R.drawable.home));
        courseModelArrayList.add(new demo("Network SMS", R.drawable.sms_networking));
        //  courseModelArrayList.add(new demo("Whatsapp", R.drawable.whatsapp));
        courseModelArrayList.add(new demo("Bulk whatsapp", R.drawable.whatsapp_bulk));
        courseModelArrayList.add(new demo("Set Bithday", R.drawable.set_birthday));
        courseModelArrayList.add(new demo("Settings", R.drawable.settings));
        courseModelArrayList.add(new demo("Set permissions", R.drawable.permission));
        courseModelArrayList.add(new demo("Eazy Post", R.drawable.eazypost));
        courseModelArrayList.add(new demo("Downloads", R.drawable.download));
        courseModelArrayList.add(new demo("Business", R.drawable.business));

        CardAdapter adapter = new CardAdapter(this, courseModelArrayList);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    startActivity(new Intent(HomeActivity.this, UpgradePremiumActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(HomeActivity.this, NetworkSMSActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(HomeActivity.this,Bulk_whatsapp_msg_Activity.class));
                } else if (position == 3) {
                    startActivity(new Intent(HomeActivity.this, Birthday_Reminder_Activity.class));
                } else if (position == 4) {
                    startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                } else if (position == 5) {
                    startActivity(new Intent(HomeActivity.this,apppermissionActivity.class));
                } else if (position == 6) {
                    startActivity(new Intent(HomeActivity.this, CustomWebView.class)
                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/login"));
                } else if (position == 7) {
                    startActivity(new Intent(HomeActivity.this, salman.class));
                } else if (position == 8) {
                    startActivity(new Intent(HomeActivity.this, CustomWebView.class)
                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/customer/cards"));
                }
            }
        });

        //All buttons
//        BtnPremium = findViewById(R.id.btn_premium);
//        BtnNetworkSMS = findViewById(R.id.btn_network_sms);
//        BtnBithday = findViewById(R.id.btn_birthday_remainder);
//        BtnSettings = findViewById(R.id.btn_settings);
//        BtnPermission = findViewById(R.id.btn_permissions);
//        BtnWhatsapp = findViewById(R.id.btn_whatsapp);
//        BtnBulkWhatsapp = findViewById(R.id.btn_bulkwhatsapp);
//        BtnEazypost = findViewById(R.id.btn_eazypost);
//        BtnDownloads = findViewById(R.id.btn_downloads);
//        Button btnbusiness =findViewById(R.id.btn_Business);
//
//        BtnPremium.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, UpgradePremiumActivity.class));
//            }
//        });
//        BtnNetworkSMS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, NetworkSMSActivity.class));
//            }
//        });
//
//        BtnBithday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, Birthday_Reminder_Activity.class));
//            }
//        });
//
//        BtnSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
//            }
//        });
//
//        BtnPermission.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this,apppermissionActivity.class));
//            }
//        });
//
//
//        BtnWhatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, WhatsappActivity.class));
//            }
//        });
//
//        BtnBulkWhatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this,Bulk_whatsapp_msg_Activity.class));
//            }
//        });
//
//        BtnEazypost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, CustomWebView.class)
//                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/login"));
//            }
//        });
//
//        BtnDownloads.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, salman.class));
//            }
//        });
//
//        btnbusiness.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, CustomWebView.class)
//                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/customer/cards"));
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        return true;
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}