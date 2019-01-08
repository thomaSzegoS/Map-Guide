package com.example.mapguide.mapguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapguide.mapguide.Activities.ListItem;
import com.example.mapguide.mapguide.Activities.MapActivity;
import com.example.mapguide.mapguide.Activities.TabActivity;
import com.example.mapguide.mapguide.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

//My adapter class
//This class is responsible for the creation of ViewHolder and onBindViewHolder
//This class is also responsible for creating a List , a context and getting the Context from tabActivity

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    private List<ListItem> listItems;
    private Context context;


    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent myIntent = new Intent(v.getContext(), TabActivity.class);
            myIntent.putExtra("lat",getLat(v));
            myIntent.putExtra("lon",getLon(v));
            myIntent.putExtra("image",getImage(v));//TODO: na perasw to url oxi tin eikona se string
            myIntent.putExtra("title",getTitle(v));
            myIntent.putExtra("description",getDescription(v));
            v.getContext().startActivity(myIntent);

        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(com.example.mapguide.mapguide.R.layout.list_item, parent, false);
        v.setOnClickListener(mOnClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
        holder.Lat.setText(listItem.getLat().toString());
        holder.Lon.setText(listItem.getLon().toString());
        Picasso.get()
                .load(listItem.getImageUrl())
                .into((holder.imageView));

    }

    //Use of getters
    //Lat,ItemCount,Image,Title,Description

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public String getLat(View itemView) {
        TextView Lat = (TextView) itemView.findViewById(R.id.id_lat);
        return Lat.getText().toString();
    }

    public String getLon(View itemView) {
        TextView Lon = (TextView) itemView.findViewById(R.id.id_lon);
        return Lon.getText().toString();
    }

    public byte[] getImage(View itemView) {
        ImageView image = (ImageView) itemView.findViewById(R.id.imageView);
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        return arr;
    }

    public String getTitle(View itemView) {
        TextView title = (TextView) itemView.findViewById(R.id.textViewHead);
        return title.getText().toString();
    }
    public String getDescription(View itemView) {
        TextView desc = (TextView) itemView.findViewById(R.id.textViewDesc);
        return desc.getText().toString();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;
        public TextView Lat;
        public TextView Lon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(com.example.mapguide.mapguide.R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(com.example.mapguide.mapguide.R.id.textViewDesc);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Lat = (TextView) itemView.findViewById(R.id.id_lat);
            Lon = (TextView) itemView.findViewById(R.id.id_lon);
        }
    }
}
