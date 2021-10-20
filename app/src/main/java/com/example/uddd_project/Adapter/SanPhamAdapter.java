package com.example.uddd_project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.viewHolder_Sanpham> {
    List<SanPhamDomain> sanPhamDomains;

    public SanPhamAdapter(List<SanPhamDomain> sanPhamDomains) {
        this.sanPhamDomains = sanPhamDomains;
    }

    @NonNull
    @Override
    public viewHolder_Sanpham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_san_pham,parent,false);
        return new viewHolder_Sanpham(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder_Sanpham holder, int position) {
        SanPhamDomain sanPham = sanPhamDomains.get(position);
        if(sanPham == null) {
            return;
        }

        holder.imgV_Hinhsp.setImageResource(sanPham.getHinh());
        holder.txtV_Tensp.setText(sanPham.getTen());
        holder.txtV_Giasp.setText(String.valueOf(sanPham.getGia()));

//        holder.txtV_Tieudesp.setText(sanPhamDomains.get(position).getTieude());
//        holder.txtV_Giasp.setText(String.valueOf(sanPhamDomains.get(position).getGia()));
//        int drawbleResourceid = holder.itemView.getContext().getResources().getIdentifier(sanPhamDomains.get(position).getHinh(),"drawble",holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawbleResourceid)
//                .into(holder.imgV_Hinhsp);

        // Cần thêm sự kiện onClick nhằm mở ra chỉ tiết sản phẩm

    }


    @Override
    public int getItemCount() {
        if (sanPhamDomains != null) {
            return sanPhamDomains.size();
        }
        return 0;
    }

    public class viewHolder_Sanpham extends RecyclerView.ViewHolder{

        private TextView txtV_Tensp,txtV_Giasp;
        private ImageView imgV_Hinhsp;

        public viewHolder_Sanpham(@NonNull View itemView) {
            super(itemView);

            txtV_Tensp = itemView.findViewById(R.id.txtV_Tensp);
            txtV_Giasp = itemView.findViewById(R.id.txtV_Giasp);
            imgV_Hinhsp = itemView.findViewById(R.id.imgV_Hinhsp);
        }
    }
}
