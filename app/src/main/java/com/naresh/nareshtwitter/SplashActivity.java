package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    private Handler handler = new Handler();
    int initialPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);


        RunProBar();
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

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    public void RunProBar(){
        initialPoint=40;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (initialPoint<100){
                    initialPoint+=30;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(initialPoint);
                        }});
                    try{
                        Thread.sleep(2000);

                    } catch (InterruptedException i){
                        i.printStackTrace();
                    }if(initialPoint>=100){


                    }

                }

            }
        }).start();
    }
}

