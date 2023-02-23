package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class apppermissionActivity extends AppCompatActivity {

    Button whatsappAccess, autobgWork, otherapps,notifyautopilot,app_per_window,battery_permission,other_per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apppermission);

        whatsappAccess = findViewById(R.id.acc_whatsapp);
        autobgWork = findViewById(R.id.autostartbackwork);
        otherapps = findViewById(R.id.otherapps);
        notifyautopilot = findViewById(R.id.notifyautopilot);
        app_per_window = findViewById(R.id.app_per_window);
        battery_permission = findViewById(R.id.battery_permission);
        other_per = findViewById(R.id.other_per);

        whatsappAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            }
        });
        autobgWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AutoStartHelper.getInstance().getAutoStartPermission(apppermissionActivity.this);
               //startActivity(new Intent(Settings.));

                if(Build.BRAND.equalsIgnoreCase("xiaomi") ){
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                    startActivity(intent);
                } else if (Build.BRAND.equalsIgnoreCase("Asus")) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.powersaver.PowerSaverSettings"));
                    startActivity(intent);
                } else if (Build.BRAND.equalsIgnoreCase("Nokia")) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.evenwell.powersaving.g3", "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity"));
                    startActivity(intent);
                } else if(Build.BRAND.equalsIgnoreCase("Letv")){
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
                    startActivity(intent);
                }
                else if(Build.BRAND.equalsIgnoreCase("Honor")){
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
                    startActivity(intent);
                } else if (Build.BRAND.equalsIgnoreCase("Vivo")) {
                    try {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"));
                        startActivity(intent);
                    } catch (Exception e) {
                        try {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
                            startActivity(intent);
                        } catch (Exception ex) {
                            try {
                                Intent intent = new Intent();
                                intent.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager");
                                startActivity(intent);
                            } catch (Exception exx) {
                                ex.printStackTrace();
                            }
                        }
                    }
                } else if (Build.BRAND.equalsIgnoreCase("Oppo")) {
                    try {
                        Intent intent = new Intent();
                        intent.setClassName("com.coloros.safecenter",
                                "com.coloros.safecenter.permission.startup.StartupAppListActivity");
                        startActivity(intent);
                    } catch (Exception e) {
                        try {
                            Intent intent = new Intent();
                            intent.setClassName("com.oppo.safe",
                                    "com.oppo.safe.permission.startup.StartupAppListActivity");
                            startActivity(intent);

                        } catch (Exception ex) {
                            try {
                                Intent intent = new Intent();
                                intent.setClassName("com.coloros.safecenter",
                                        "com.coloros.safecenter.startupapp.StartupAppListActivity");
                                startActivity(intent);
                            } catch (Exception exx) {

                            }
                        }
                    }
                }
            }
        });
        other_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));

//                if (!Settings.canDrawOverlays(apppermissionActivity.this)) {
//                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//                    startActivityForResult(intent, 0);
//                }
            }
        });
        notifyautopilot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
            }
        });

        app_per_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", getPackageName(), null)));
            }
        });

        battery_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS));
            }
        });
        other_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS));
            }
        });
    }
}