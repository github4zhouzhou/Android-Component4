package com.zhouzhou.componentsfour;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityD extends ActivityBase implements View.OnClickListener {

    private Button btnQuery, btnDelete, btnInsert, btnUpdate, btnThirdPart, btnUseFirstURI, btnUseSecondURI;
    private TextView mtv;
    private Button btnSendBroadcast;

    public static final String AUTHORITY = "com.v.provider.PeopleContentProvider";
    public static final Uri CONTENT_URI_FIRST = Uri.parse("content://" + AUTHORITY + "/first");
    public static final Uri CONTENT_URI_SECOND = Uri.parse("content://" + AUTHORITY + "/second");
    public static Uri mCurrentURI = CONTENT_URI_FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        btnDelete = (Button) findViewById(R.id.delete);
        btnInsert = (Button) findViewById(R.id.insert);
        btnQuery = (Button) findViewById(R.id.query);
        btnUpdate = (Button) findViewById(R.id.update);
        btnThirdPart = (Button) findViewById(R.id.thirdPart);
        btnUseFirstURI = (Button) findViewById(R.id.first_uri);
        btnUseSecondURI = (Button) findViewById(R.id.second_uri);
        mtv = (TextView) findViewById(R.id.tv);
        mtv.setText("current URI:" + mCurrentURI.toString());

        btnDelete.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnThirdPart.setOnClickListener(this);
        btnUseFirstURI.setOnClickListener(this);
        btnUseSecondURI.setOnClickListener(this);

        // 广播测试
        btnSendBroadcast = (Button) findViewById(R.id.btn_send_broadcast);
        btnSendBroadcast.setOnClickListener(this);
    }

    private void thirdPart() {
        Intent intent = new Intent();
        intent.setAction("com.v.second");
        //intent.setType("vnd.android.cursor.dir/v.first");
        //intent.setData(mCurrentURI);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void insert() {
        ContentValues values = new ContentValues();
        values.put("name", "hello");
        values.put("detail", "my name is v");
        Uri uri = this.getContentResolver().insert(mCurrentURI, values);
        Log.e("test ", uri.toString());
    }

    private void query() {
        Cursor cursor = this.getContentResolver().query(mCurrentURI, null, null, null, null);
        Log.e("test ", "count=" + cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String table = cursor.getString(cursor.getColumnIndex("table_name"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String detail = cursor.getString(cursor.getColumnIndex("detail"));
            Log.e("test", "table_name:" + table);
            Log.e("test ", "name: " + name);
            Log.e("test ", "detail: " + detail);
            cursor.moveToNext();
        }
        cursor.close();
    }

    private void update() {
        ContentValues values = new ContentValues();
        values.put("detail", "my name is v !!!!!!!!!!!!!!!!!!!!!!!!!!");
        int count = this.getContentResolver().update(mCurrentURI, values, "_id = 1", null);
        Log.e("test ", "count=" + count);
        query();
    }

    private void delete() {
        int count = this.getContentResolver().delete(mCurrentURI, "_id = 1", null);
        Log.e("test ", "count=" + count);
        query();
    }

    private void switchURI(Uri uri) {
        mCurrentURI = uri;
        mtv.setText("current URI:" + mCurrentURI.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_uri: {
                switchURI(CONTENT_URI_FIRST);
            }
            break;
            case R.id.second_uri: {
                switchURI(CONTENT_URI_SECOND);
            }
            break;
            case R.id.delete: {
                delete();
            }
            break;
            case R.id.insert: {
                insert();
            }
            break;
            case R.id.query: {
                query();
            }
            break;
            case R.id.update: {
                update();
            }
            break;
            case R.id.thirdPart: {
                thirdPart();
            }
            break;
            case R.id.btn_send_broadcast: {
                Intent intent = new Intent();
                //intent.setAction("android.intent.action.MY_BROADCAST");
                intent.setAction("android.intent.action.FIRST_BROADCAST");
                intent.putExtra("msg", "message from other application.");
                sendBroadcast(intent);
                //sendOrderedBroadcast(intent, "com.v.broadcast.permission");
            }
            break;
            default:
                break;
        }

    } // end of onClick

} // end of class Activity
