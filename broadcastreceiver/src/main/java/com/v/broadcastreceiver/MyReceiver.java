package com.v.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static Handler sHandler;

    public static void setUIHandler(Handler h) {
        sHandler = h;
    }

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("msg");
        Log.d("MyReceiver", "message = " + str);
        if (sHandler != null) {
            Message msg = new Message();
            msg.what = 1221;
            msg.obj = str;
            sHandler.sendMessage(msg);
        }
    }
}
