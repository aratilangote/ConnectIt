package com.technolite.connectit;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;

public class SMSBackgroundService extends Service {
    private CallReceiver callReceiver;
    public static final String ACTION_PHONE_STATE_CHANGED = TelephonyManager.ACTION_PHONE_STATE_CHANGED;
   // public static final String ACTION_PHONE_STATE_CHANGED = TelephonyManager.ACTION_PHONE_STATE_CHANGED;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Register broadcast receiver for incoming call events
        callReceiver = new CallReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION_PHONE_STATE_CHANGED);
        registerReceiver(callReceiver, intentFilter);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Unregister broadcast receiver
        unregisterReceiver(callReceiver);
    }
}