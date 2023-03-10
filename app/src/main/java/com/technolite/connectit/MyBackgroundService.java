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

import java.util.HashMap;
import java.util.HashSet;

public class MyBackgroundService extends Service {
    private static final String TAG = "MyBackgroundService";
    private static final String CHANNEL_ID = "MyBackgroundServiceChannel";
    private Handler mHandler;
    private Runnable mRunnable;
    private SharedPreferences preferences;
    private TelephonyManager telephonyManager;
    private PhoneStateListener phoneStateListener;
    private boolean smsSent = false;



    @Override
    public void onCreate() {
        super.onCreate();

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
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        mRunnable = new Runnable() {
            private HashSet<String> sentNumbers = new HashSet<>();
            private HashMap<String, Long> lastSentTimes = new HashMap<>();
            @Override
            public void run() {
                Log.d(TAG, "Background service is running...");

                // Check for incoming calls
                telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                phoneStateListener = new PhoneStateListener() {
                    @Override
                    public void onCallStateChanged(int state, String incomingNumber) {
                        super.onCallStateChanged(state, incomingNumber);
                        Log.d("MyBackgroundService", "onCallStateChanged: state = " + state + ", incomingNumber = " + incomingNumber);

                        switch (state) {
                            case TelephonyManager.CALL_STATE_RINGING:
                                if (!sentNumbers.contains(incomingNumber)) {
                                    String message = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("incomingMessage", "");
                                    if (!message.isEmpty()) {
                                        sendSMS(incomingNumber, message);
                                        sentNumbers.add(incomingNumber);
                                        lastSentTimes.put(incomingNumber, System.currentTimeMillis());
                                    }
                                } else {
                                    Long lastSentTime = lastSentTimes.get(incomingNumber);
                                    if (lastSentTime != null && System.currentTimeMillis() - lastSentTime > 24 * 60 * 60 * 1000) {
                                        String message = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("incomingMessage", "");
                                        if (!message.isEmpty()) {
                                            sendSMS(incomingNumber, message);
                                            lastSentTimes.put(incomingNumber, System.currentTimeMillis());
                                        }
                                    }
                                }
                                break;
                            case TelephonyManager.CALL_STATE_OFFHOOK:
                            case TelephonyManager.CALL_STATE_IDLE:
                                sentNumbers.remove(incomingNumber);
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
        Log.d("MyBackgroundService", "Background service is stopped.");
        Log.d(TAG, "Background service is stopped.");

        // Stop the foreground service and remove the notification
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message+"\n"+"https://technolitesolutions.com/", null, null);
        }catch (Exception e){
            Toast.makeText(this, "Oops service is not available right now.", Toast.LENGTH_SHORT).show();
        }

    }

}
