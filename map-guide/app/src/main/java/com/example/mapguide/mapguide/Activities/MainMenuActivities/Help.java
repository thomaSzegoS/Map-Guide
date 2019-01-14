package com.example.mapguide.mapguide.Activities.MainMenuActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

import com.example.mapguide.mapguide.R;


public class Help extends AppCompatActivity {


    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        textView =  findViewById(R.id.textHelp);

        String para = "Στην κεντρική φόρμα υπάρχει στην πάνω δεξιά μεριά ένα menu επιλογών(Settings και help),όπου πατώντας το Settings  μεταβαίνει στις ρυθμίσεις τhς εφαρμογής και στο help στην φόρμα βοήθειας του χρήστη";

        textView.setText(para);


        textView.setMovementMethod(new ScrollingMovementMethod());


    }
}
