package com.example.mapguide.mapguide.Activities.MainMenuActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.mapguide.mapguide.R;

public class About extends AppCompatActivity {


    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);


        textView = findViewById(R.id.textAbout);

        String para = "Map-Guide builded by\n" +
                "\tThomas Zegos\n" +
                "\tTheodoros Boutakoglou\n" +
                "\tTsiflikas Panagiotis\n" +
                "\tFoteini Papaharalabou\n" +
                "\tKaterina Stefanidou\n" +
                "\tTasos Petridis\n" +
                "\tEleni Dimitriadou,\n" +
                "\tDimitris Pallasidis\n" +
                "\tAnastasia Kontoura\n" +
                "\tKostas Theo\n" +
                "Version: v2.0\n" +
                "\n\n\n\nMap Guide Copyrights © 2019\n";

        textView.setText(para);


        textView.setMovementMethod(new ScrollingMovementMethod());

    }

}
