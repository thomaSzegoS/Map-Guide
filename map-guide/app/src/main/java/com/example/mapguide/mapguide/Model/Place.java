package com.example.mapguide.mapguide.Model;



import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mapguide.mapguide.R;

public class Place {

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
        if (this.lat==null){
            this.lat = "1";
        }
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        if (this.lon==null){
            this.lon = "1";
        }
        return this.lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}


