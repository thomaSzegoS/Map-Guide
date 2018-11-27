package com.example.mapguide.mapguide.Activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mapguide.mapguide.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Γεμίζει το μενού · αυτό προσθέτει στοιχεία στη γραμμή μπάρας, αν υπάρχει
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            //όταν κάνεις κλικ σε μια μπάρα η μπάρα χειρισμού "action bar"
            //αυτόματα η μπάρα θα παραμήνει στη θέση της
            //εφόσον ορίσαμε συγκεκριμένα σαν parent activity "AndroidManifest.xml"
            case R.id.menu_red:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(false);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F44336")));
                return true;

            case R.id.menu_blue:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(false);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03A9F4")));
                return true;
            case R.id.menu_green:
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(false);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4CAF50")));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
