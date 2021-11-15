package com.example.bankingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankingapp.R;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=2000;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent i = new Intent(SplashActivity.this,
                            MainActivity.class);

                    startActivity(i);

                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
