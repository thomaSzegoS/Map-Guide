package com.example.mapguide.mapguide.Services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkAvailability {
    private boolean networkWorks;

    public NetworkAvailability(Context context) {
        networkWorks = isNetworkAvailable(context);
    }

    public boolean isNetworkWorks() {
        return networkWorks;
    }

    private void setNetworkWorks(boolean networkWorks) {
        this.networkWorks = networkWorks;
    }

    private boolean isNetworkAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        if (!haveConnectedWifi && !haveConnectedMobile) {
            //Toast.makeText(context.getApplicationContext(), R.string.checkForNetworkMessage, Toast.LENGTH_LONG).show();
            setNetworkWorks(false);
        } else {
            setNetworkWorks(true);
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
