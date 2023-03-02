package com.technolite.connectit;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyBackgroundServiceOutgoing extends Service {
    private static final String TAG = "MyBackgroundSerOutgoing";
    private static final String CHANNEL_ID = "MyBackgroundOutgoingChannel";
    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;
    private Handler mHandler;
    private Runnable mRunnable;
    private SharedPreferences preferences;

    private boolean smsSent = false;

    @Override
    public void onCreate() {
        super.onCreate();
       /* preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                switch (state) {

                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        if(!smsSent) {
                            // Outgoing call started
                            String outgoingMessage = getSharedPreferences("myPrefs", MODE_PRIVATE).getString("outgoingMessage", "");
                            if (!outgoingMessage.isEmpty()) {
                                sendSMS(incomingNumber, outgoingMessage);
                                smsSent = true;
                            }
                        }
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                    case TelephonyManager.CALL_STATE_IDLE:
                        smsSent = false;
                        break;

                    default:
                        break;
                }
            }
        };
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "My Background Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification for the foreground service
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("My Background Service")
                .setContentText("Running in the background")
                .setSmallIcon(R.drawable.notification)
                .build();

        // Start the service as a foreground service
        startForeground(1, notification);

        mHandler = new Handler();
        preferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Background service is running...");

                // Check for incoming calls
                telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                phoneStateListener = new PhoneStateListener() {
                    @Override
                    public void onCallStateChanged(int state, String incomingNumber) {
                        super.onCallStateChanged(state, incomingNumber);
                       // Log.d("MyBackgroundSerOutgoing", "onCallStateChanged: state = " + state + ", incomingNumber = " + incomingNumber);

                        switch (state) {
                            case TelephonyManager.CALL_STATE_OFFHOOK:
                                if(!smsSent) {
                                    // Outgoing call started
                                   // String number =Intent.EXTRA_PHONE_NUMBER;
                                    String outgoingMessage = getSharedPreferences("myPrefs", MODE_PRIVATE).getString("outgoingMessage", "");
                                    if (!outgoingMessage.isEmpty()) {
                                    //    Log.d("OutgoingService","Outgoing Call :"+number+"Message"+outgoingMessage);
                                        sendSMS(incomingNumber, outgoingMessage);
                                    //    sendSMS(number, outgoingMessage);
                                        smsSent = true;
                                    }
                                }
                                break;
                            case TelephonyManager.CALL_STATE_RINGING:
                            case TelephonyManager.CALL_STATE_IDLE:
                                smsSent = false;
                                break;
                        }
                    }

                };
                telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
                Log.d("MyBackgroundService", "Background service is running...");


                mHandler.postDelayed(this, 10000); // 10 seconds
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.post(mRunnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
        Log.d(TAG, "Background service is stopped.");
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            // Code to send SMS
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        }catch (Exception e){
            Toast.makeText(this, "Oops service is not available.", Toast.LENGTH_SHORT).show();
        }
    }
}