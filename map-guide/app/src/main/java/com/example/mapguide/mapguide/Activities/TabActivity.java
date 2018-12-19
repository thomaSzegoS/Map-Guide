package com.example.mapguide.mapguide.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.MapService;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    Button favBtn;
    ImageView img,fimg;
    TextView tx_title, tx_location, tx_description;
    String lat, lon;

    private static final String TAG = "TabActivity";
    MapActivity mvCam = new MapActivity();
    private static final float DEFAULT_ZOOM = 15f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Log.d(TAG, "onCreate: get incoming intents");


        //get intents
        getIncomingIntents();



        MapService services = new MapService();

        fimg = findViewById(R.id.fullscreenView);
        favBtn = findViewById(R.id.favButton);
        img = findViewById(R.id.image);
        tx_title = findViewById(R.id.textTitle);
        tx_location = findViewById(R.id.textLocation);
        tx_description = findViewById(R.id.textDesc);

        /*************se auto to shmeio me bgazei problhma******************/
        //fimg.setImageResource(getIntent().getIntExtra("img_id",00));
        //img.setImageResource(getIntent().getIntExtra("img_id",00));
        /*******************************************************************/

        /*******************************de xreiastikan gia auta pou ekana***/
        //tx_title.setText("Text : "+getIntent().getStringExtra("text"));
        //tx_location.setText("Location : "+getIntent().getStringExtra("location"));
        //tx_description.setText("Description : "+getIntent().getStringExtra("description"));


        /************problhma sthn if*****************************************/
        //if (services.isServicesOK()) {
            loadMapActivity();
        //}
        favBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v){
                                          favBtn.setBackgroundColor(Color.RED);
                                      }
                                  }
        );
        /*****************************me empodizei na to trexw****************/
        /*fimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfullscreen();
            }
        });*/
    }

    public void openfullscreen(){
        Intent intent = new Intent(this, Fullscreen.class);
        startActivity(intent);
    }

    private void loadMapActivity() {
        FloatingActionButton btnMap =  findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TabActivity.this, MapActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                startActivity(intent);
            }
        });
    }

    private void getIncomingIntents(){
        Log.d(TAG, "getIncomingIntents: checking for incoming intents");
        if(getIntent().hasExtra("lat") && getIntent().hasExtra("lon") /*&& getIntent().hasExtra("image")*/ && getIntent().hasExtra("title") && getIntent().hasExtra("description")) {
            Log.d(TAG, "getIncomingIntents: found intents");

             lat = getIntent().getStringExtra("lat");
            lon = getIntent().getStringExtra("lon");
            //String image = getIntent().getStringExtra("image");
            String title = getIntent().getStringExtra("title");
            String description = getIntent().getStringExtra("description");
            Log.d(TAG, "getIncomingIntents: image title " + title);


            //setImage(image);

            setTitle(title);

            setLocation(lat, lon);
            setDescription(description);
            Log.d(TAG, "getIncomingIntents: DONE");
        }
    }
/*******************tha asxolithw me to image argotera*********************/
   /* private void setImage(String image){
        //set image to imageView

    }*/
   private void setTitle(String title){
       TextView textViewTitle = findViewById(R.id.textTitle);
       textViewTitle.setText(title);
       Log.d(TAG, "setTitle: after setText");
   }


    private void setLocation(String lat, String lon){
        TextView location = findViewById(R.id.textLocation);
        String latlon = lat + "," + lon;

        location.setText(latlon);
    }

    private void setDescription(String description){
        TextView textViewDescription = findViewById(R.id.textDesc);
        if(description.isEmpty()) description="Description not found";
        textViewDescription.setText(description);
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
