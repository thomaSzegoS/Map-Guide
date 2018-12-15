package com.example.mapguide.mapguide.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.text.Editable;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.mapguide.mapguide.Adapters.MyAdapter;
import com.example.mapguide.mapguide.Model.Image;
import com.example.mapguide.mapguide.R;
import com.example.mapguide.mapguide.Services.FlickrService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    LinearLayout LLayout1;
    private static final String URL_DATA = "https://simplifiedcoding.net/demos/marvel/";
    private RecyclerView recyclerView;
    private android.support.v7.widget.RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    final FlickrService r = new FlickrService();
    private Button button;
    private FloatingActionButton searchBtn;
    private EditText textBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        searchBtn = findViewById(R.id.searchBtn);
        textBar = findViewById(R.id.textBar);
        LLayout1 = findViewById(R.id.LinearLayout2);

        r.GetTop20();
        loadRecyclerViewData();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNetworkStatus();
            }
        });


         searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(LLayout1.getVisibility() == View.GONE){
                LLayout1.setVisibility(LLayout1.VISIBLE);}
                else if(LLayout1.getVisibility() == View.VISIBLE) {
                    LLayout1.setVisibility(LLayout1.GONE);
                }
            }
        });


         /* Input watcher to react while text changes  */
        textBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                r.GetSearch20(textBar.getText().toString());
                loadRecyclerViewData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       /*  End of Input watcher to react while text changes */



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






        //loadRecyclerViewData();
        /*
        final EditText SearchBar = (EditText) findViewById(R.id.id_SearchBar);
        Button SearchButton = (Button) findViewById(R.id.id_SearchButton);
        r.GetTop20();
        loadRecyclerViewData();
        */




    public void openNetworkStatus(){
        Intent intent = new Intent(this, NetworkStatus.class);
        startActivity(intent);
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


