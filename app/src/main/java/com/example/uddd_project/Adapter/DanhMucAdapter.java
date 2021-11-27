package com.example.uddd_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.TimSanPham;
import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.R;


import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.viewH_Danhmuc> {
    List<DanhMucDomain> danhMucDomains;
    Context context;
    public DanhMucAdapter(Context context, List<DanhMucDomain> danhMucDomains) {
        this.danhMucDomains = danhMucDomains;
        this.context = context;
    }

    @NonNull
    @Override
    public viewH_Danhmuc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_danh_muc,parent,false);
        return new DanhMucAdapter.viewH_Danhmuc(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewH_Danhmuc holder, int position) {
        DanhMucDomain danhMuc = danhMucDomains.get(position);
        if(danhMuc == null) {
            return;
        }
        byte[] hinhSP = danhMuc.getHinhDM();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhSP,0,hinhSP.length);

        holder.imgV_Hinhdm.setImageBitmap(bitmap);
        holder.txtV_Tendm.setText(danhMuc.getTenDM());
        holder.cardV_DanhMuc_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TimSanPham.class);
                intent.putExtra("idDM",danhMuc.getIDDM());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (danhMucDomains != null) {
            return danhMucDomains.size();
        }
        return 0;
    }

    public class viewH_Danhmuc extends RecyclerView.ViewHolder {
        private TextView txtV_Tendm;
        private ImageView imgV_Hinhdm;
        CardView cardV_DanhMuc_dm;

        public viewH_Danhmuc(@NonNull View itemView) {
            super(itemView);

            txtV_Tendm = itemView.findViewById(R.id.txtV_Tendm);
            imgV_Hinhdm = itemView.findViewById(R.id.imgV_Hinhdm);
            cardV_DanhMuc_dm = itemView.findViewById(R.id.cardV_DanhMuc_dm);
        }
    }
}
