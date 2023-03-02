package com.technolite.connectit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AllButtonsActivity extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 1;
    Button BtnPremium, BtnHome, BtnNetworkSMS, BtnBithday, BtnSettings, BtnPermission, BtnBulkWhatsapp, BtnWhatsapp, BtnEazypost, BtnDownloads, BtnBusiness;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_buttons);

        BtnPremium = findViewById(R.id.btn_premium);
        BtnHome = findViewById(R.id.btn_home);
        BtnNetworkSMS = findViewById(R.id.btn_network_sms);
        BtnBithday = findViewById(R.id.btn_birthday_remainder);
        BtnSettings = findViewById(R.id.btn_settings);
        BtnPermission = findViewById(R.id.btn_permissions);
        BtnWhatsapp = findViewById(R.id.btn_whatsapp);
        BtnBulkWhatsapp = findViewById(R.id.btn_bulkwhatsapp);
        BtnEazypost = findViewById(R.id.btn_eazypost);
        BtnDownloads = findViewById(R.id.btn_downloads);
        BtnBusiness = findViewById(R.id.btn_Business);

        if (ContextCompat.checkSelfPermission(AllButtonsActivity.this,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(AllButtonsActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestStoragePermission();
        }


        BtnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, UpgradePremiumActivity.class));
            }
        });

        BtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, HomeActivity.class));
            }
        });
        BtnNetworkSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, NetworkSMSActivity.class));
            }
        });

        BtnBithday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, Birthday_Reminder_Activity.class));
            }
        });

        BtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, SettingsActivity.class));
            }
        });

        BtnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this,apppermissionActivity.class));
            }
        });


        BtnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, WhatsappActivity.class));
            }
        });

        BtnBulkWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this,Bulk_whatsapp_msg_Activity.class));
            }
        });

        BtnEazypost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, CustomWebView.class)
                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/customer/cards"));
            }
        });

        BtnDownloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllButtonsActivity.this, salman.class));
            }
        });

       /* BtnBusiness.setOnClickListener(v ->
                startActivity(new Intent(AllButtonsActivity.this, EazypostWebviewActivity.class)
                        .putExtra("LinkToOpen", "https://linkconnect.in/panel/customer/cards"))
        );*/
    }
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to download and Share Daily posts from EAZY POSTS")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(AllButtonsActivity.this,
                                    new String[] {Manifest.permission.MANAGE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            }
        }
    }
}