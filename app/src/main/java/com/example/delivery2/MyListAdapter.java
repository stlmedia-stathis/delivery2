package com.example.delivery2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private
    ArrayList listdata;
    Context context;
    // RecyclerView recyclerView;
    public MyListAdapter(Context context, ArrayList listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String perigrafi;
        Integer pos;
        perigrafi = (listdata.get(position).toString());
        pos = perigrafi.indexOf("+@");
        holder.textView.setText(perigrafi.substring(0, pos));
        holder.imageView.setImageResource(getInt(perigrafi.substring(pos, perigrafi.length())));
       // holder.textView.setText(listdata.get(position).toString());
    //   holder.imageView.setImageResource(listdata.get(position).get(0));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(context,"click on item: "+holder.textView.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
          //  this.imageView.setImageResource(R.drawable.alagi);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
    public int getInt(String s) {
        return Integer.parseInt(s.replaceAll("[\\D]", ""));
    }

}