package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.technolite.connectit.module.SlideItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    CardView BtnPremium, BtnNetworkSMS, BtnBithday, BtnSettings, BtnPermission, BtnBulkWhatsapp, BtnWhatsapp, BtnEazypost, BtnDownloads, btnbusiness;


    ViewPager2 viewPager2;

    private Handler slideHandler = new Handler();

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



       // All buttons
        BtnPremium = findViewById(R.id.btn_premium);
        BtnNetworkSMS = findViewById(R.id.btn_network_sms);
        BtnBithday = findViewById(R.id.btn_birthday_remainder);
        BtnSettings = findViewById(R.id.btn_settings);
        BtnPermission = findViewById(R.id.btn_permissions);
     //   BtnWhatsapp = findViewById(R.id.btn_whatsapp);
        BtnBulkWhatsapp = findViewById(R.id.btn_bulkwhatsapp);
        BtnEazypost = findViewById(R.id.btn_eazypost);
        BtnDownloads = findViewById(R.id.btn_downloads);
        btnbusiness =findViewById(R.id.btn_Business);

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

        BtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });

        BtnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,apppermissionActivity.class));
            }
        });


//        BtnWhatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, WhatsappActivity.class));
//            }
//        });

        BtnBulkWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,Bulk_whatsapp_msg_Activity.class));
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


        //Viewpager
        viewPager2 = findViewById(R.id.view_pager);

        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.color.teal_200));
        slideItems.add(new SlideItem(R.color.teal_700));
        slideItems.add(new SlideItem(R.color.teal_200));
        //slideItems.add(new SlideItem(R.drawable.image1));
        //slideItems.add(new SlideItem(R.drawable.image2));
        //slideItems.add(new SlideItem(R.drawable.image3));

        viewPager2.setAdapter(new SlideAdapter(slideItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r = 1-Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable, 2000);
            }
        });




    }
    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

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
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "YOUR_LINK");
                sendIntent.setType("Download Samrudhi solar App!");
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