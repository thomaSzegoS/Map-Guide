package com.example.mapguide.mapguide.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class TabActivity extends AppCompatActivity {

    private static final String TAG = "TabActivity";

    ImageView img;
    TextView tx_title, tx_location, tx_description;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Log.d(TAG, "onCreateTabActivity: started!!!");
        MapServices services = new MapServices();

        img = findViewById(R.id.image);
        tx_title = findViewById(R.id.textTitle);
        tx_location = findViewById(R.id.textLocation);
        tx_description = findViewById(R.id.textDesc);

        //img.setImageResource(getIntent().getIntExtra("img_id",00));
        //tx_title.setText("Text : "+getIntent().getStringExtra("text"));
        //tx_location.setText("Location : "+getIntent().getStringExtra("location"));
        //tx_description.setText("Description : "+getIntent().getStringExtra("description"));


        getIncomingIntent();
        if (services.isServicesOK()) {
           // loadMapActivity();
            Log.d(TAG, "onCreate: map loading maps");
        }


    }

    private void getIncomingIntent(){

        Log.d(TAG, "getIncomingIntent: checking for incoming intent");

        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){

            Log.d(TAG, "getIncomingIntent: found intent extras");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imageUrl, imageName);
        }
        else {
            Toast.makeText(this, "getIncomingIntent(): Extras not found!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setImage(String imageUrl, String imageName){
        Log.d(TAG, "setImage: setting the image and name to the widgets");


        tx_title.setText(imageName);

        ImageView image = img;
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);


    }


    private void loadMapActivity() {
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }















}
