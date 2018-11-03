package com.example.mapguide.mapguide.Model;

import com.google.android.gms.maps.model.LatLng;

public class Place {


    private String title, description; // onoma perioxhs ,perigrafh perioxhs
    private LatLng coordinates; // syntetagmenes latitude ,longtude

    public Place() {

    }

    public Place(String title, String description, LatLng coordinates) {
        this.title = title;
        this.description = description;
        this.coordinates = coordinates;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;

    }

    public String getDescription() {
        return description;
    }


}
