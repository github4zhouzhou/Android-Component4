package com.v.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SecondReceiver extends BroadcastReceiver {
    public SecondReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 通过打印可以知道，onReceive 是在主线程执行的，所有这里不能有耗时操作
        Log.d("SecondReceiver", "current thread = " + Thread.currentThread());
        String str = intent.getStringExtra("msg");
        Log.d("SecondReceiver", "message = " + str);
    }
}
