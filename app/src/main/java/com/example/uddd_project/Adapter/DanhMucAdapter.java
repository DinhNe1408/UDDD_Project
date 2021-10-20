package com.example.uddd_project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.R;


import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.viewH_Danhmuc> {
    List<DanhMucDomain> danhMucDomains;

    public DanhMucAdapter(List<DanhMucDomain> danhMucDomains) {
        this.danhMucDomains = danhMucDomains;
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

        holder.imgV_Hinhdm.setImageResource(danhMuc.getHinh());
        holder.txtV_Tendm.setText(danhMuc.getTendm());

        // Cần thêm sự kiện onClick nhằm mở ra chỉ tiết sản phẩm
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


        public viewH_Danhmuc(@NonNull View itemView) {
            super(itemView);

            txtV_Tendm = itemView.findViewById(R.id.txtV_Tendm);
            imgV_Hinhdm = itemView.findViewById(R.id.imgV_Hinhdm);
        }
    }
}
