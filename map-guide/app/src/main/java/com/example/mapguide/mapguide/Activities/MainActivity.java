package com.example.mapguide.mapguide.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrServices;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText searchtextview = findViewById(R.id.id_searchtext);
        Button searchbutton = findViewById(R.id.id_searchbutton);
        final FlickrServices r = new FlickrServices();
        r.GetTop20();
        if (r.PhotosData != null) {
            for (int i = 0; i < r.PhotosData.size(); i++) {
                String[] Photo = r.PhotosData.get(i);
                System.out.println("ID:" + Photo[0] + ", Title:" + Photo[1] + ", Link:" + Photo[2] + ", Latitude:" + Photo[3] + ", Longitude:" + Photo[4] + ", Description:" + Photo[5] + " ");
            }
        }

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.GetSearch20(searchtextview.getText().toString());
                if (r.PhotosData != null) {
                    for (int i = 0; i < r.PhotosData.size(); i++) {
                        String[] Photo = r.PhotosData.get(i);
                        System.out.println("ID:" + Photo[0] + ", Title:" + Photo[1] + ", Link:" + Photo[2] + ", Latitude:" + Photo[3] + ", Longitude:" + Photo[4] + ", Description:" + Photo[5] + " ");
                    }
                }
            }
        });



    }

}


