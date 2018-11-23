package com.example.mapguide.mapguide.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(com.example.mapguide.mapguide.R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      ListItem listItem = listItems.get(position);

      holder.textViewHead.setText(listItem.getHead());
      holder.textViewDesc.setText(listItem.getDesc());

        Picasso.get()
                .load(listItem.getImageUrl())
                .into((holder.imageView));

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(com.example.mapguide.mapguide.R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(com.example.mapguide.mapguide.R.id.textViewDesc);
            imageView = (ImageView) itemView.findViewById(com.example.mapguide.mapguide.R.id.imageView);
        }
    }
}
