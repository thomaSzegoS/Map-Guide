package com.example.mapguide.mapguide.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.MapService;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class TabActivity extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    PrettyDialog dialog;
    boolean flag;
    ImageButton favBtn, shareBtn;
    ImageView img, fimg;

    TextView tx_title, tx_location, tx_description;
    String lat, lon;

    private static final String TAG = "TabActivity";
    MapActivity mvCam = new MapActivity ();
    private static final float DEFAULT_ZOOM = 15f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_tab);
        Log.d (TAG, "onCreate: get incoming intents");


        //get intents
        getIncomingIntents ();


        MapService services = new MapService ();


        favBtn = findViewById (R.id.favButton);
        img = findViewById (R.id.imageVIew);
        tx_title = findViewById (R.id.textTitle);
        tx_location = findViewById (R.id.textLocation);
        tx_description = findViewById (R.id.textDesc);

        shareBtn = findViewById (R.id.share_btn);

        shareBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions ()) {
                    Log.d ("mpika", String.valueOf (saveImage (convertImageViewToBitmap (img))));
                    Intent shareIntent = new Intent ();
                    shareIntent.setAction (Intent.ACTION_SEND);
                    //shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                    shareIntent.putExtra (Intent.EXTRA_STREAM, saveImage (convertImageViewToBitmap (img)));  //optional//use this when you want to send an image
                    shareIntent.setType ("image/jpeg");
                    shareIntent.addFlags (Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity (Intent.createChooser (shareIntent, "Share images to.."));
                }
            }
        });

        /*************se auto to shmeio me bgazei problhma******************/
        //fimg.setImageResource(getIntent().getIntExtra("img_id",00));
        //img.setImageResource(getIntent().getIntExtra("img_id",00));
        img.setImageDrawable (getResources ().getDrawable (R.mipmap.ic_launcher)); // gia logus debugging
        /*******************************************************************/

        /*******************************de xreiastikan gia auta pou ekana***/
        //tx_title.setText("Text : "+getIntent().getStringExtra("text"));
        //tx_location.setText("Location : "+getIntent().getStringExtra("location"));
        //tx_description.setText("Description : "+getIntent().getStringExtra("description"));


        /************problhma sthn if*****************************************/
        //if (services.isServicesOK()) {
        loadMapActivity ();
        //}
        favBtn.setOnClickListener (new View.OnClickListener () {
                                       @Override
                                       public void onClick(View v) {
                                           favBtn.setBackgroundColor (Color.RED);
                                       }
                                   }
        );
        img.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openfullscreen ();
            }
        });
    }

    private boolean checkAndRequestPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission (TabActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            flag = false;

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale (TabActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                dialog = new PrettyDialog (this);
                dialog.setTitle (getString (R.string.app_name))
                        .setMessage (getString (R.string.permissions_message))
                        .setIcon (android.R.drawable.ic_menu_info_details)
                        .addButton (getString (android.R.string.ok), android.R.color.white, android.R.color.holo_red_light, new PrettyDialogCallback () {
                            @Override
                            public void onClick() {
                                ActivityCompat.requestPermissions (TabActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        REQUEST_ID_MULTIPLE_PERMISSIONS);
                                dialog.dismiss ();
                            }
                        })
                        .addButton (getString (android.R.string.cancel), android.R.color.white, android.R.color.holo_blue_light, new PrettyDialogCallback () {
                            @Override
                            public void onClick() {
                                flag = false;
                                dialog.dismiss ();
                            }
                        })
                        .setAnimationEnabled (true)
                        .show ();

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            flag = true;
        }
        return flag;
    }

    private Bitmap convertImageViewToBitmap(ImageView v) {
        return ((BitmapDrawable) v.getDrawable ()).getBitmap ();
    }

    private Uri saveImage(Bitmap finalBitmap) {
        //ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File rootSdDirectory = Environment.getExternalStorageDirectory ();
        // path to /data/data/yourapp/app_data/imageDir
        //File directory = cw.getDir("bitmap", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File (rootSdDirectory, "MapGuide_image" + ".jpeg");

        //FileOutputStream fos = null;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream (mypath);
            //fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            finalBitmap.compress (Bitmap.CompressFormat.JPEG, 90, stream);
            stream.flush ();
            stream.close ();

        } catch (Exception e) {
            e.printStackTrace ();
        }
        return Uri.fromFile (mypath);
    }

    public void openfullscreen() {
        Intent intent = new Intent (this, Fullscreen.class);
        startActivity (intent);
    }

    private void loadMapActivity() {
        ImageButton btnMap = findViewById (R.id.btnMap);
        btnMap.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (TabActivity.this, MapActivity.class);
                intent.putExtra ("lat", lat);
                intent.putExtra ("lon", lon);
                startActivity (intent);
            }
        });
    }

    private void getIncomingIntents() {
        Log.d (TAG, "getIncomingIntents: checking for incoming intents");
        if (getIntent ().hasExtra ("lat") && getIntent ().hasExtra ("lon") /*&& getIntent().hasExtra("image")*/ && getIntent ().hasExtra ("title") && getIntent ().hasExtra ("description")) {
            Log.d (TAG, "getIncomingIntents: found intents");

            lat = getIntent ().getStringExtra ("lat");
            lon = getIntent ().getStringExtra ("lon");
            byte[] image = getIntent ().getByteArrayExtra ("image");
            String title = getIntent ().getStringExtra ("title");
            String description = getIntent ().getStringExtra ("description");
            Log.d (TAG, "getIncomingIntents: image title " + title);
            Log.d (TAG, "getIncomingIntents: image url " + image);

            setImage (image);

            setTitle (title);

            setLocation (lat, lon);
            setDescription (description);
            Log.d (TAG, "getIncomingIntents: DONE");
        }
    }

    /*******************tha asxolithw me to image argotera*********************/
   /* private void setImage(String image){
        //set image to imageView

    }*/
    private void setImage(byte[] b) {
        ImageView ivBasicImage = (ImageView) findViewById (R.id.imageVIew);
        Bitmap bmp = BitmapFactory.decodeByteArray (b, 0, b.length);
        ivBasicImage.setImageBitmap (bmp);

    }

    private void setTitle(String title) {
        TextView textViewTitle = findViewById (R.id.textTitle);
        textViewTitle.setText (title);
        Log.d (TAG, "setTitle: after setText");
    }


    private void setLocation(String lat, String lon) {
        TextView location = findViewById (R.id.textLocation);
        String latlon = lat + "," + lon;

        location.setText (latlon);
    }

    private void setDescription(String description) {
        TextView textViewDescription = findViewById (R.id.textDesc);
        if (description.isEmpty ()) description = "Description not found";
        textViewDescription.setText (description);
    }

    private void geoLocate() {
        Log.d (TAG, "geoLocate: geolocation");

        String searchString = tx_location.getText ().toString ();

        Geocoder geocoder = new Geocoder (TabActivity.this);
        List<Address> list = new ArrayList<> ();

        try {
            list = geocoder.getFromLocationName (searchString, 1);
        } catch (IOException e) {
            Log.e (TAG, "geoLocate:IOException: " + e.getMessage ());
        }

        if (list.size () > 0) {
            Address address = list.get (0);

            Log.d (TAG, "geoLocate: found a location: " + address.toString ());

            mvCam.moveCamera (new LatLng (address.getLatitude (), address.getLongitude ()), DEFAULT_ZOOM, address.getAddressLine (0));
        }
    }


}
