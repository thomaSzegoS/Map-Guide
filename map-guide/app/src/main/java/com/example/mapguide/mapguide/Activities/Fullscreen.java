package com.example.mapguide.mapguide.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mapguide.mapguide.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Fullscreen extends AppCompatActivity {

    PhotoView fimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        fimg = findViewById(R.id.fullscreenView);
        fimg.setImageResource(R.drawable.ic_android_black_24dp);
    }

    //private void loadimg(){

   // }
}
