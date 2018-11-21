package com.example.mapguide.mapguide.Model;


import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mapguide.mapguide.R;


public class Place extends AppCompatActivity {

     private ImageView img;
     private TextView head, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img = findViewById(R.id.image);
        head = findViewById(R.id.textViewHead);
        desc = findViewById(R.id.textViewDesc);

        img.setImageResource(getIntent().getIntExtra("img_id", 00));
        head.setText("Text : " + getIntent().getStringExtra("text"));
        desc.setText("Description : " + getIntent().getStringExtra("description"));
    }


    private String lat, lon, areaName;

    public Place() {

    }

    public Place(String lat, String lon, String areaName) {
        this.lat = lat;
        this.lon = lon;
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


}
