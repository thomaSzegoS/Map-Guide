package com.example.mapguide.mapguide.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.MapServices;

public class GalleryActivity extends AppCompatActivity {
    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreateGalleryActivity: started!!!");

        getIncomingIntent();

        FloatingActionButton mapBtn = findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Map button pressed");
                MapServices services = new MapServices();
                Log.d(TAG, "onClick: new Map services...DONE");
                //if (services.isServicesOK()==true)
                loadMapActivity();
                Log.d(TAG, "onClick: Google Maps loaded successfully");

            }
        });
    }



    private void getIncomingIntent(){

        Log.d(TAG, "getIncomingIntent: checking for incoming intent");

        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){

            Log.d(TAG, "getIncomingIntent: found intent extras");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imageUrl, imageName);
        }
    }


    private void setImage(String imageUrl, String imageName){
        Log.d(TAG, "setImage: setting the image and name to the widgets");

        TextView name = findViewById(R.id.imageTitle);
        name.setText(imageName);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);


    }


    private void loadMapActivity() {
        Intent intent = new Intent(GalleryActivity.this, MapActivity.class);
        Toast.makeText(GalleryActivity.this, "Loading Google Maps", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
