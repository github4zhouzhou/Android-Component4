package com.zhouzhou.componentsfour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnA = getButtonA();
        //http://img3.imgtn.bdimg.com/it/u=3734411932,2762705517&fm=21&gp=0.jpg
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.zhouzhou.componentsfour.action.aa");
                //intent.setDataAndType(Uri.parse("http://www.baidu.com/android.jpg"), "image/*");
                startActivity(intent);
            }
        });

        Button btnB = getButtonB();
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.zhouzhou.componentsfour.action.ba");
                intent.addCategory("com.zhouzhou.componentsfour.category.ba");
                startActivity(intent);
            }
        });

        Button btnC = getButtonC();
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMain.this, ActivityC.class);
                startActivity(intent);
            }
        });

        Button btnD = getButtonD();
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMain.this, ActivityD.class);
                startActivity(intent);
            }
        });

        Button btnMain = getButtonMain();
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMain.this, ActivityMain.class);
                startActivity(intent);
            }
        });

    }

}
