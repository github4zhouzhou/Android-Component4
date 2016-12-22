package com.v.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 1. 广播的动态注册和静态注册
 * 2. 有序广播
 * 3. 为广播添加权限
 */

public class MainActivity extends Activity implements IMessageReceiver {

    private FirstReceiver mFirstReceiver;
    private BroadcastReceiver mLocalReceiver;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1221: {
                    String str = (String)msg.obj;
                    TextView tv = (TextView) findViewById(R.id.tv_text);
                    tv.setText(str);
                }
                break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.COMMON_BROADCAST");
                intent.putExtra("msg", "hello receiver.");
                //sendBroadcast(intent);
                // 使用有序广播，使用权限
                sendOrderedBroadcast(intent, null);
            }
        });

        Button btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstReceiver = new FirstReceiver();
                mFirstReceiver.registerReceiver(MainActivity.this);

                IntentFilter filter = new IntentFilter();
                filter.setPriority(14); // 为广播添加优先级
                filter.addAction("android.intent.action.FIRST_BROADCAST");
                filter.addAction("android.intent.action.COMMON_BROADCAST");

                registerReceiver(mFirstReceiver, filter, "com.v.broadcast.permission", null);
            }
        });

        Button btnLocalReceiver = (Button) findViewById(R.id.btn_local_receiver);
        btnLocalReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MainActivity.this);

                Intent intent = new Intent();
                intent.setAction("android.intent.action.LOCAL_BROADCAST");
                intent.putExtra("msg", "hello world");
                lbm.sendBroadcast(intent);
            }
        });


        // 注册本地广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCAL_BROADCAST");
        intentFilter.addAction("android.intent.action.COMMON_BROADCAST");
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MainActivity.this);
        mLocalReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String str = intent.getStringExtra("msg");
                Log.d("LocalReceiver", "message = " + str);
            }
        };
        lbm.registerReceiver(mLocalReceiver, intentFilter);


        MyReceiver.setUIHandler(mHandler);

        // 这段代码可以将 FirstReceiver 的 enabled 值为 true
        /*
        getPackageManager().setComponentEnabledSetting(
                new ComponentName(this, FirstReceiver.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        */

    }

    @Override
    public void onReceive(String str) {
        TextView tv = (TextView) findViewById(R.id.tv_text);
        tv.setText(str);
    }

    @Override
    protected void onDestroy() {
        if (mFirstReceiver != null) {
            mFirstReceiver.unregisterReceiver();
            // 广播解注册
            unregisterReceiver(mFirstReceiver);
        }

        if (mLocalReceiver != null) {
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(MainActivity.this);
            lbm.unregisterReceiver(mLocalReceiver);
        }

        MyReceiver.setUIHandler(null);

        super.onDestroy();
    }
}
