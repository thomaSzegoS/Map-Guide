package com.example.mapguide.mapguide.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mapguide.mapguide.R;

public class NetworkStatus extends AppCompatActivity {

    ImageView mConStatusIv;
    TextView mConStatusTv;
    Button mConStatusBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_status);

        mConStatusIv = findViewById(R.id.conStatusIv);
        mConStatusTv = findViewById(R.id.conStatusTv);
        mConStatusBtn = findViewById(R.id.conStatusBtn);

        mConStatusBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkNetworkConnectionStatus();
        }
    });

}
        private void checkNetworkConnectionStatus(){
                    boolean wifiConnected;
                    boolean mobileConnected;
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
            if(activeInfo != null && activeInfo.isConnected()){
                wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                if(wifiConnected){
                    mConStatusIv.setImageResource(R.drawable.ic_action_wifi);
                    mConStatusTv.setText("Connected with Wifi");
            }
                else if (mobileConnected){
                    mConStatusIv.setImageResource(R.drawable.ic_action_mobile);
                    mConStatusTv.setText("Connected with Mobile Data Connection");
                }
            }
            else {
                mConStatusIv.setImageResource(R.drawable.ic_action_no);
                mConStatusTv.setText("No internet connection");
            }
                }
}
