package com.v.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private Button mBtnBindService;
    private Button mBtnUnbindService;
    private Button mBtnStartService;

    private IServiceManager ism;

    private ServiceConnection sc = new MyServiceConnection();

    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnBindService = (Button) findViewById(R.id.btn_bind);
        mBtnUnbindService = (Button) findViewById(R.id.btn_unbind);
        mBtnStartService = (Button) findViewById(R.id.btn_start_service);

        mBtnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, sc, Context.BIND_AUTO_CREATE);
            }
        });

        mBtnUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeUnbindService();
            }
        });

        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
         public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.d(TAG, "in MyServiceConnection onServiceConnected");
            if (binder != null && binder instanceof IServiceManager) {
                ism = (IServiceManager) binder;
                ism.printService();
            }
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "in MyServiceConnection onServiceDisconnected");
            mBound = false;
        }

    }

     private void executeUnbindService() {
         if (mBound) {
             unbindService(sc);
             mBound = false;
         }
     }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "in onDestroy");
        executeUnbindService();
        super.onDestroy();
    }

}
