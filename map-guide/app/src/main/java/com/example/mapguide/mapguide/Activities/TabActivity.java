package com.example.mapguide.mapguide.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.MapServices;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    ImageView img;
    TextView tx_title, tx_location, tx_description;

    private static final String TAG = "TabActivity";
    MapActivity mvCam = new MapActivity();
    private static final float DEFAULT_ZOOM = 15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapServices services = new MapServices();

        img = findViewById(R.id.image);
        tx_title = findViewById(R.id.textTitle);
        tx_location = findViewById(R.id.textLocation);
        tx_description = findViewById(R.id.textDesc);

        img.setImageResource(getIntent().getIntExtra("img_id",00));
        tx_title.setText("Text : "+getIntent().getStringExtra("text"));
        tx_location.setText("Location : "+getIntent().getStringExtra("location"));
        tx_description.setText("Description : "+getIntent().getStringExtra("description"));


        if (services.isServicesOK()) {
            loadMapActivity();

            geoLocate();
        }
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

    private void geoLocate () {
        Log.d(TAG, "geoLocate: geolocation");
        String searchString = tx_location.getText().toString();
        Geocoder geocoder = new Geocoder(TabActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate:IOException: " + e.getMessage() );
        }
        if(list.size() > 0){
            Address address = list.get(0);
            Log.d(TAG, "geoLocate: found a location: " +address.toString());
            mvCam.moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        }
    }















}
