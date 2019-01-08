package com.example.mapguide.mapguide.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.mapguide.mapguide.R;

public class WelcomeScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void  run(){
                Intent homeIntent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(homeIntent);
                finish();


            }
        },SPLASH_TIME_OUT);
    }
}
