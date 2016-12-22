package com.zhouzhou.componentsfour;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

/**
 * Created by zhouzhou on 2016/11/14.
 */

public abstract class ActivityBase extends Activity {
    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "------  " + TAG + "  ------" + "OnCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "------  " + TAG + "  ------" + "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "------  " + TAG + "  ------" + "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "------  " + TAG + "  ------" + "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "------  " + TAG + "  ------" + "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "------  " + TAG + "  ------" + "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "------  " + TAG + "  ------" + "onDestroy");
        super.onDestroy();
    }


    public Button getButtonA() {
        return (Button) findViewById(R.id.id_bt_show_1);
    }

    public Button getButtonB() {
        return (Button) findViewById(R.id.id_bt_show_2);
    }

    public Button getButtonC() {
        return (Button) findViewById(R.id.id_bt_show_3);
    }

    public Button getButtonD() {
        return (Button) findViewById(R.id.id_bt_show_4);
    }

    public Button getButtonMain() {
        return (Button) findViewById(R.id.id_bt_show_main);
    }
}
