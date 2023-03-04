package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public DrawerLayout drawerLayout;
    ImageSlider imageslider;
    ImageView tool;

    MaterialCardView BtnPremium, BtnNetworkSMS, BtnBithday, BtnWhatsapp, BtnEazypost, btnbusiness, BtnDownloads;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout=findViewById(R.id.drawer_layout);
        tool=findViewById(R.id.tool);
        imageslider=findViewById(R.id.imageslider);
        setImageSlider(imageslider);

        tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });


        //navigationview settings
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);



        // All buttons
        BtnPremium = findViewById(R.id.btn_premium);
        BtnNetworkSMS = findViewById(R.id.btn_network_sms);
        BtnBithday = findViewById(R.id.btn_birthday_remainder);
        BtnWhatsapp = findViewById(R.id.btn_whatsapp);
        BtnEazypost = findViewById(R.id.btn_eazypost);
        btnbusiness =findViewById(R.id.btn_Business);
        BtnDownloads = findViewById(R.id.btn_downloads);

        BtnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, UpgradePremiumActivity.class));
            }
        });
        BtnNetworkSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NetworkSMSActivity.class));
            }
        });

        BtnBithday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Birthday_Reminder_Activity.class));
            }
        });


        BtnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, WhatsappActivity.class));
            }
        });

        BtnEazypost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CustomWebView.class)
                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/login"));
            }
        });

        BtnDownloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, salman.class));
            }
        });

        btnbusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CustomWebView.class)
                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/customer/cards"));
            }
        });

    }

    private void setImageSlider(ImageSlider imageSlider)
    {
        ArrayList<SlideModel> slidemodels=new ArrayList<>();
        slidemodels.add(new SlideModel(R.drawable.banner01, ScaleTypes.CENTER_CROP));
        slidemodels.add(new SlideModel(R.drawable.banner01, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slidemodels,ScaleTypes.CENTER_CROP);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent intent1 = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent1);
                break;

            case R.id.permission:
                Intent intent2 = new Intent(HomeActivity.this, apppermissionActivity.class);
                startActivity(intent2);
                break;

            case R.id.about_us:
//                Intent intent3 = new Intent(HomeActivity.this, Aboutus.class);
//                startActivity(intent3);
                break;

            case R.id.contact:
                String url = "https://technolitesolutions.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.privacy:
                break;

            case R.id.share:
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://technolitesolutions.com");
//                sendIntent.setType("Download ConnectIt App!");
//                startActivity(sendIntent);



                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://technolitesolutions.com" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("Download ConnectIt App!");
                startActivity(sendIntent);
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;
        }
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