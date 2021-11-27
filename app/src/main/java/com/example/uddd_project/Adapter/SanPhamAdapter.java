package com.example.uddd_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.ChiTietSanPham;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.viewHolder_Sanpham> {
    List<SanPhamDomain> sanPhamDomains;
    private Context context;

    public SanPhamAdapter(Context context, List<SanPhamDomain> sanPhamDomains) {
        this.sanPhamDomains = sanPhamDomains;
        this.context = context;
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

        byte[] hinhSP = sanPham.getHinhSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhSP,0,hinhSP.length);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");
        String gia = decimalFormat.format(sanPham.getGiaSP());

        holder.txtV_Tensp.setText(sanPham.getTenSP());
        holder.txtV_Giasp.setText(gia);
        holder.imgV_Hinhsp.setImageBitmap(bitmap);

        if (sanPham.getGiaGocSP() == 0){
            holder.txtV_GiaGoc.setVisibility(View.INVISIBLE);
        }else {
            holder.txtV_GiaGoc.setVisibility(View.VISIBLE);
            holder.txtV_GiaGoc.setText(decimalFormat.format(sanPham.getGiaGocSP()));
            holder.txtV_GiaGoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSP(sanPham);
            }
        });
    }

    private void onClickSP(SanPhamDomain sanPham) {
        Intent intent = new Intent(context, ChiTietSanPham.class);
        intent.putExtra("IDSP_CTSP",sanPham.getIDSP());
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        if (sanPhamDomains != null) {
            return sanPhamDomains.size();
        }
        return 0;
    }

    public class viewHolder_Sanpham extends RecyclerView.ViewHolder{

        View view;
        private CardView mainlayout;
        private TextView txtV_Tensp,txtV_Giasp,txtV_GiaGoc;
        private ImageView imgV_Hinhsp;

        public viewHolder_Sanpham(@NonNull View itemView) {
            super(itemView);

            mainlayout = itemView.findViewById(R.id.mainlayout);
            txtV_GiaGoc = itemView.findViewById(R.id.txtV_GiaGoc);
            txtV_Tensp = itemView.findViewById(R.id.txtV_Tensp);
            txtV_Giasp = itemView.findViewById(R.id.txtV_Giasp);
            imgV_Hinhsp = itemView.findViewById(R.id.imgV_Hinhsp);
        }
    }
}
