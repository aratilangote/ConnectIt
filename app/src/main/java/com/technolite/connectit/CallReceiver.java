package com.technolite.connectit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Code to send an SMS when a call arrives
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String message = "Thank you for contacting us.\n If you have any query you can contact us.";
        if (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(incomingNumber, null, message, null, null);
        }
       /* switch (tm.getCallState()) {
            case TelephonyManager.CALL_STATE_RINGING:
                // Handle incoming call
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                String message = "Thank you for contacting us.\n If you have any query you can contact us.";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(incomingNumber, null, message, null, null);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                // Handle outgoing call
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                // Handle call ending
                break;
        }*/
    }
}