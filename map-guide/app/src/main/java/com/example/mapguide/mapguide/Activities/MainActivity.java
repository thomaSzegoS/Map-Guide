package com.example.mapguide.mapguide.Activities;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mapguide.mapguide.Model.ImageModel;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private android.support.v7.widget.RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    final FlickrServices r = new FlickrServices();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void loadRecyclerViewData(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        for (int i = 0; i < r.PhotosData.size(); i++) {
            ImageModel photo = r.PhotosData.get(i);
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


