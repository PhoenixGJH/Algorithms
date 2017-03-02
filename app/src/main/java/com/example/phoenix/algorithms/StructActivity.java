package com.example.phoenix.algorithms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StructActivity extends AppCompatActivity {
    private int j;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struct);

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inc inc = new Inc();
                Dec dec = new Dec();
                for (int i = 0; i < 2; i++) {
                    Thread thread = new Thread(inc);
                    thread.start();
                    thread = new Thread(dec);
                    thread.start();
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNotification();
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test2.testVersionCompare();
            }
        });
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class Inc implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }
        }
    }

    /**
     * 设置通知
     */
    private void makeNotification() {
        String text = "特别长的文字啊数据库了的房间的数据库福建省的两节课了了是看得见发了可接受的否连接扣水电费吉林省的弗兰克聚少离多会计法是老大会计法是豆腐看见了塑料袋看风景";
//        String text = "特别长的文字啊";
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText(text);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 9, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap btm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setTicker("了快捷键了")//第一次提示消息的时候显示在通知栏上
                .setLargeIcon(btm)
                .setStyle(style)
                .setAutoCancel(false)//自己维护通知的消失
                .setContentIntent(contentIntent);
        //获取通知管理器对象
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(9, mBuilder.build());
    }
}
