package com.example.uddd_project.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.ChiTietSanPham;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.Fragment.GioHangTab;
import com.example.uddd_project.Fragment.TrangChuTab;
import com.example.uddd_project.SPGioHangDomain;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.viewH_Giohang>{
    public static List<SanPhamDomain> spGioHangDomains;
    Context context;

    public GioHangAdapter(Context context, List<SanPhamDomain> sanPhamDomain) {
        this.context = context;
        this.spGioHangDomains = sanPhamDomain;
    }

    @NonNull
    @Override
    public GioHangAdapter.viewH_Giohang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_gio_hang,parent,false);
        return new GioHangAdapter.viewH_Giohang(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.viewH_Giohang holder, int position) {
        SanPhamDomain spGioHang = spGioHangDomains.get(position);
        if(spGioHang == null) {
            return;
        }

        byte[] hinhSP = spGioHang.getHinhSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhSP,0,hinhSP.length);

        holder.imgV_HinhSP_Gh.setImageBitmap(bitmap);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");
        String gia = decimalFormat.format(spGioHang.getGiaSP());

        holder.txtV_TenSP_Gh.setText(spGioHang.getTenSP());
        holder.txtV_SLMSP_Gh.setText(String.valueOf(spGioHang.getSLMSP()));
        holder.txtV_GiaSP_Gh.setText(gia);
        holder.txtV_ThanhTien_gh.setText(decimalFormat.format(spGioHang.getGiaSP() * spGioHang.getSLMSP()));
        holder.cardV_GioHang_gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("IDSP_CTSP",spGioHang.getIDSP());
                context.startActivity(intent);
            }
        });

        holder.imgB_XoaSPGH_Gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc muốn xóa sản phẩm này khỏi giỏ hàng không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holder.dao.CapNhatSPGH(TrangChu.taikhoan.getIDTK(),
                                        spGioHang.getIDSP(), 0);

                                spGioHangDomains.remove(holder.getAdapterPosition());
                                GioHangTab.gioHangAdapter.notifyItemRemoved(holder.getAdapterPosition());

                                if(spGioHangDomains.size() == 0){
                                    Intent intent = new Intent(context,TrangChu.class);
                                    intent.putExtra("posTab",1);
                                    context.startActivity(intent);
                                }
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });

        //holder

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

        private CardView cardV_GioHang_gh;
        private ImageButton imgB_XoaSPGH_Gh;
        private ImageView imgV_HinhSP_Gh;
        private TextView txtV_TenSP_Gh, txtV_GiaSP_Gh, txtV_SLMSP_Gh, txtV_ThanhTien_gh;
        private DAO dao;
        private int SLMSP = 0;

        public viewH_Giohang(@NonNull View itemView) {
            super(itemView);

            dao = new DAO(itemView.getContext());

            txtV_TenSP_Gh = itemView.findViewById(R.id.txtV_TenSP_Gh);
            imgV_HinhSP_Gh = itemView.findViewById(R.id.imgV_HinhSP_Gh);
            imgB_XoaSPGH_Gh = itemView.findViewById(R.id.imgB_XoaSPGH_Gh);

            txtV_TenSP_Gh = itemView.findViewById(R.id.txtV_TenSP_Gh);
            txtV_GiaSP_Gh = itemView.findViewById(R.id.txtV_GiaSP_Gh);
            txtV_SLMSP_Gh = itemView.findViewById(R.id.txtV_SLMSP_Gh);
            cardV_GioHang_gh = itemView.findViewById(R.id.cardV_GioHang_gh);
            txtV_ThanhTien_gh = itemView.findViewById(R.id.txtV_ThanhTien_gh);
        }
    }
}
