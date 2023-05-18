package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter_Course extends ArrayAdapter <Course>{
    Context context;
    int resource;

    ArrayList<Course> data;

    public CustomAdapter_Course(@NonNull Context context, int resource, @NonNull ArrayList<Course> data) {
        super(context, resource, data);
        this.context=context;
        this.resource=resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder=(ViewHolder) convertView.getTag();
        return convertView;
    }

    private class ViewHolder{
        TextView tv1;
        //ImageView

        public ViewHolder(View view){
            tv1 = view.findViewById(R.id.tv1);
        }
    }
}
