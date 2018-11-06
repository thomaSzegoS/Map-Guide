package com.example.mapguide.mapguide.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrServices;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlickrServices r = new FlickrServices();
        for (int i = 0; i < r.PhotosData.size(); i++) {
            String[] Photo = r.PhotosData.get(i);
            System.out.println("ID:"+Photo[0]+ ", Title:" +Photo[1]+ ", Link:" +Photo[2]+ ", Latitude:" +Photo[3]+ ", Longitude:" +Photo[4]+ ", Description:" +Photo[5]+ " ");
        }

    }

}


