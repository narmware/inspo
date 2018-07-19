package com.narmware.inspo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.narmware.inspo.R;

public class SplashActivity extends AppCompatActivity {

    public static int TIMEOUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(SplashActivity.this,HelpActivity.class);
                startActivity(intent);
                finish();
            }
        },TIMEOUT);

    }
}
