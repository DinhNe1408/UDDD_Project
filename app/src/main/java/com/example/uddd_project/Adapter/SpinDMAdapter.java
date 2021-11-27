package com.example.uddd_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.R;

import java.util.List;

public class SpinDMAdapter extends ArrayAdapter<DanhMucDomain> {

    public SpinDMAdapter(@NonNull Context context, int resource, @NonNull List<DanhMucDomain> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_spin_dm_b, parent,false);
        TextView txtV_Spin_DM_selected_dm = convertView.findViewById(R.id.txtV_Spin_DM_selected_dm);

        DanhMucDomain danhMuc = this.getItem(position);
        if(danhMuc != null){
            txtV_Spin_DM_selected_dm.setText(danhMuc.getTenDM());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_spin_dm_a, parent,false);
        TextView txtV_Spin_DM_dm = convertView.findViewById(R.id.txtV_Spin_DM_dm);

        DanhMucDomain danhMuc = this.getItem(position);
        if(danhMuc != null){
            txtV_Spin_DM_dm.setText(danhMuc.getTenDM());
        }
        return convertView;
    }
}
