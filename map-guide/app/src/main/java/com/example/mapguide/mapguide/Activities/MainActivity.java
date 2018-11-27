package com.example.mapguide.mapguide.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mapguide.mapguide.Model.ImageModel;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrServices;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageURLs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: App started!!!");

        initImageBitmaps();
        initRecyclerView();
}


    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing Bitmaps...");

        FlickrServices r = new FlickrServices();
        Log.d(TAG, "onCreate: Flickr services ok");

        //loading the images in RecyclerView
        for (int i = 0; i < r.PhotosData.size(); i++) {
            ImageModel photo = r.PhotosData.get (i);
            Log.d (TAG, "ID:" + photo.getId () + ", Title:" + photo.getTitle () + ", Link:" + photo.getLinkImg () + ", Latitude:" + photo.getPlace ().getLat () + ", Longitude:" + photo.getPlace ().getLon () + ", Description:" + photo.getDesc () + " ");
            mImageURLs.add(photo.getLinkImg ());
            mNames.add(photo.getTitle ());
        }

        /*mImageURLs.add("https://cdn.shopifycloud.com/hatchful-web/assets/313d73fa42f04a46213abc6267b4d074.png");
        mNames.add("Safyi Real Estate");

        mImageURLs.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageURLs.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageURLs.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageURLs.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageURLs.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageURLs.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageURLs.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageURLs.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Australia");

        mImageURLs.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");*/

        Log.d(TAG, "initImageBitmaps: DONE.");
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView.");

        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageURLs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d(TAG, "initRecyclerView: DONE.");
    }

}


