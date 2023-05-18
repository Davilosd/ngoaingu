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

import java.util.ArrayList;

public class CustomAdapter_SV extends ArrayAdapter<SinhVien> {
    Context context;
    int resource;
    ArrayList<SinhVien> data;
    public CustomAdapter_SV(@NonNull Context context, int resource, @NonNull ArrayList<SinhVien> data) {
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
            convertView= LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }


        SinhVien sv = data.get(position);
        viewHolder.tvHoTen.setText(sv.getTenSV());
        if(sv.getGioiTinh()){
            viewHolder.ivGioiTinh.setImageResource(R.drawable.baseline_male_24);

        }
        else {
            viewHolder.ivGioiTinh.setImageResource(R.drawable.baseline_female_24);

        }
        viewHolder.cbChon.setChecked(sv.getChon());
        viewHolder.cbChon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sv.setChon(b);
            }
        });
        return convertView;
    }
    public void ChonHet(){
        for (SinhVien sv:data){
            sv.setChon(true);
        }
        notifyDataSetChanged();
    }
    public void BoChon(){
        for (SinhVien sv:data){
            sv.setChon(false);
        }
        notifyDataSetChanged();
    }
    public void XoaDS(){
        ArrayList<SinhVien> data_tempt=new ArrayList<>();

        for (SinhVien sv:data){
            if(!sv.getChon())
                data_tempt.add(sv);
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
