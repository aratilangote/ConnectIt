package com.technolite.connectit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int subscriptionId = intent.getIntExtra("subscriptionId", -1);

        String number = intent.getStringExtra("number");
        String message = intent.getStringExtra("message");


        SmsManager smsManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            smsManager = SmsManager.getSmsManagerForSubscriptionId(subscriptionId);
            smsManager.sendTextMessage(number, null, message, null, null);
        }



        Toast.makeText(context, "SMS Sent", Toast.LENGTH_SHORT).show();
    }
}
