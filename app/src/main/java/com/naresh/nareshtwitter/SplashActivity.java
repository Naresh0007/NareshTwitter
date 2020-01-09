package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }



}

