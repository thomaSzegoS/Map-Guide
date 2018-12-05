package com.example.mapguide.mapguide.Services;

import android.app.Dialog;
import android.util.Log;

import com.example.mapguide.mapguide.Activities.TabActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MapService {
    /*************************Checking Api Services Availability ***********************************************/

    private static final String TAG = "TabActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private TabActivity tabActivity;

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(tabActivity);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error has occured
            Log.d(TAG, "isServicesOK: an error has occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(tabActivity, available, ERROR_DIALOG_REQUEST);
            dialog.show();
            return false;
        }
        else {
            Log.d(TAG, "We can't make map requests!");
            return false;
        }
    }
}
