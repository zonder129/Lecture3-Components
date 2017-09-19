package ru.mail.park.lecture3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BatteryReceiver extends BroadcastReceiver {
    private final static String TAG = BatteryReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = (intent != null) ? intent.getAction() : "NO ACTION";
        Log.d(TAG, String.format("onReceive. Action is '%s'", action));
    }
}