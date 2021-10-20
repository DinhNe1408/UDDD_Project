package com.example.uddd_project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.SPGioHangDomain;
import com.example.uddd_project.R;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.viewH_Giohang>{
    List<SPGioHangDomain> spGioHangDomains;

    public GioHangAdapter(List<SPGioHangDomain> danhMucDomains) {
        this.spGioHangDomains = danhMucDomains;
    }

    @NonNull
    @Override
    public GioHangAdapter.viewH_Giohang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_gio_hang,parent,false);
        return new GioHangAdapter.viewH_Giohang(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.viewH_Giohang holder, int position) {
        SPGioHangDomain spGioHang = spGioHangDomains.get(position);
        if(spGioHang == null) {
            return;
        }

        holder.imgV_Hinhsp.setImageResource(spGioHang.getHinhsp());
        holder.txtV_Tensp.setText(spGioHang.getTensp());

        // Cần thêm sự kiện onClick nhằm mở ra chỉ tiết sản phẩm
    }

    @Override
    public int getItemCount() {
        if (spGioHangDomains != null) {
            return spGioHangDomains.size();
        }
        return 0;
    }

    public class viewH_Giohang extends RecyclerView.ViewHolder {
        private TextView txtV_Tensp;
        private ImageView imgV_Hinhsp;


        public viewH_Giohang(@NonNull View itemView) {
            super(itemView);

            txtV_Tensp = itemView.findViewById(R.id.txtV_Tensanpham_Gh);
            imgV_Hinhsp = itemView.findViewById(R.id.imgV_Hinhsanpham_Gh);
        }
    }
}
