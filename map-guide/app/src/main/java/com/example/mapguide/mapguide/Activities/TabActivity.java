package com.example.mapguide.mapguide.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mapguide.mapguide.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class TabActivity extends AppCompatActivity {

    private static final String TAG = "TabActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServicesOK()) {
            loadMapActivity();
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



    /*************************Checking Api Services Availability ***********************************************/

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(TabActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error has occured
            Log.d(TAG, "isServicesOK: an error has occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(TabActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
            return false;
        }
        else {
            Toast.makeText(this, "We can't make map requests!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
