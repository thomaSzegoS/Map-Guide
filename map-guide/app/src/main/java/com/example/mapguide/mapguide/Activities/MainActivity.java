package com.example.mapguide.mapguide.Activities;


import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.example.mapguide.mapguide.Adapters.MyAdapter;
import com.example.mapguide.mapguide.Model.Image;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private android.support.v7.widget.RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    final FlickrService r = new FlickrService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(haveNetwork())
        {
            ////////////////////
            //proceed with my app functionality
        }
        else if(!haveNetwork())
        {
            ////////////////////
            Toast.makeText(MainActivity.this, "Network connection is not available!", Toast.LENGTH_SHORT).show();
        }



        final EditText SearchBar = (EditText) findViewById(R.id.id_SearchBar);
        Button SearchButton = (Button) findViewById(R.id.id_SearchButton);
        r.GetTop20();
        loadRecyclerViewData();

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.GetSearch20(SearchBar.getText().toString());
                loadRecyclerViewData();
            }
        });


    }

    private boolean haveNetwork()
    {
        boolean have_WIFI=false;
        boolean have_MobileData=false;


        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info:networkInfos)
        {
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    have_WIFI=true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    have_MobileData=true;
        }
        return have_MobileData || have_WIFI;

    }

    private void loadRecyclerViewData(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        for (int i = 0; i < r.PhotosData.size(); i++) {
            Image photo = r.PhotosData.get(i);
            Log.v("lat",photo.getPlace().getLat());
            Log.v("lon",photo.getPlace().getLon());
            ListItem item = new ListItem(photo.getTitle(), photo.getDesc(), photo.getLinkImg(),photo.getPlace().getLat(),photo.getPlace().getLon());
            listItems.add(item);
        }
        adapter = new MyAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();
    }

}


