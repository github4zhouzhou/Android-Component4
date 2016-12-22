package com.zhouzhou.componentsfour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityA extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button btnA = getButtonA();
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.zhouzhou.componentsfour.action.ab");
                startActivity(intent);
            }
        });

        Button btnB = getButtonB();
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityA.this, ActivityB.class);
                startActivity(intent);
            }
        });

        Button btnC = getButtonC();
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityA.this, ActivityC.class);
                startActivity(intent);
            }
        });

        Button btnD = getButtonD();
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityA.this, ActivityD.class);
                startActivity(intent);
            }
        });

        Button btnMain = getButtonMain();
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityA.this, ActivityMain.class);
                startActivity(intent);
            }
        });

    }
}
