package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testapp.data.model.Question;

import java.util.ArrayList;

public class CustomAdapter_Question extends ArrayAdapter<Question> {
    Context context;
    int resource;
    ArrayList<Question> data;
    public CustomAdapter_Question(@NonNull Context context, int resource, @NonNull ArrayList<Question> data) {
        super(context, resource, data);
        this.context=context;
        this.resource=resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CustomAdapter_Question.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new CustomAdapter_Question.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomAdapter_Question.ViewHolder) convertView.getTag();
        }


        Question q = data.get(position);
        viewHolder.tvHoTen.setText(q.getQuestion());
//        if(sv.getGioiTinh()){
//            viewHolder.ivGioiTinh.setImageResource(R.drawable.baseline_male_24);
//
//        }
//        else {
//            viewHolder.ivGioiTinh.setImageResource(R.drawable.baseline_female_24);
//
//        }
        return convertView;
    }
    public void ChonHet(){
        for (Question q:data){
            q.setChon(true);
        }
        notifyDataSetChanged();
    }
    public void BoChon(){
        for (Question q:data){
            q.setChon(false);
        }
        notifyDataSetChanged();
    }
    public void XoaDS(){
        ArrayList<Question> data_tempt=new ArrayList<>();


        for (Question q:data){
            if(!q.getChon())
                data_tempt.add(q);
        }
        data.clear();
        data.addAll(data_tempt);
        notifyDataSetChanged();
    }
    private class ViewHolder{
        TextView tvHoTen;
        ImageView ivGioiTinh;
        CheckBox cbChon;
        public ViewHolder(View view){
            tvHoTen=view.findViewById(R.id.tvHoTen);
            ivGioiTinh=view.findViewById(R.id.ivGioiTinh);
            cbChon = view.findViewById(R.id.cbChon);
        }


    }
}
