package com.v.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FirstReceiver extends BroadcastReceiver {
    private IMessageReceiver mMessageReceiver;

    public FirstReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("msg");
        Log.d("FirstReceiver", "message = " + str);
        if (mMessageReceiver != null) {
            mMessageReceiver.onReceive(str);
        }
    }

    public void registerReceiver(IMessageReceiver imr) {
        mMessageReceiver = imr;
    }

    public void unregisterReceiver() {
        mMessageReceiver = null;
    }
}
