package com.example.mapguide.mapguide.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mapguide.mapguide.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
