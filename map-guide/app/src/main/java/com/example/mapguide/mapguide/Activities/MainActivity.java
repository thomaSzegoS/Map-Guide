package com.example.mapguide.mapguide.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mapguide.mapguide.Model.ImageModel;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrServices;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlickrServices r = new FlickrServices();
        for (int i = 0; i < r.PhotosData.size(); i++) {
            ImageModel photo = r.PhotosData.get (i);
            System.out.println ("ID:" + photo.getId () + ", Title:" + photo.getTitle () + ", Link:" + photo.getLinkImg () + ", Latitude:" + photo.getPlace ().getLat () + ", Longitude:" + photo.getPlace ().getLon () + ", Description:" + photo.getDesc () + " ");
        }

    }

}


