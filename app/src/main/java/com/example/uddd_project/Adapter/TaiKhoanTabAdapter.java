package com.example.uddd_project.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.DangNhap;
import com.example.uddd_project.Activity.DoiMatKhau;
import com.example.uddd_project.Activity.LichSuMuaHang;
import com.example.uddd_project.Activity.QLSanPham;
import com.example.uddd_project.Activity.QLTaiKhoan;
import com.example.uddd_project.Activity.ThongTinCaNhan;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Activity.YeuThich;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.List;

public class TaiKhoanTabAdapter extends RecyclerView.Adapter<TaiKhoanTabAdapter.TaiKhoanTabAdapterHolder> {
    
    private Context context;
    private List<Integer> listkey;

    public TaiKhoanTabAdapter(Context context, List<Integer> listkey) {
        this.context = context;
        this.listkey = listkey;
    }

    @NonNull
    @Override
    public TaiKhoanTabAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tai_khoan_tab,parent,false);
        return new TaiKhoanTabAdapter.TaiKhoanTabAdapterHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiKhoanTabAdapterHolder holder, int position) {
        int key = listkey.get(position);
        switch (key){
            case TrangChu.KEY_LSMH :
                holder.txtV_taikhoantab.setText("Lịch sử mua hàng");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_receipt_long_black_48dp);
                break;
            case TrangChu.KEY_YEUTHICH:
                holder.txtV_taikhoantab.setText("Yêu thích");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_favorite_black_48dp);
                break;
            case TrangChu.KEY_TROGIUP:
                holder.txtV_taikhoantab.setText("Trợ giúp");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_help_black_48dp);
                break;
            case TrangChu.KEY_DANGNHAP:
                holder.txtV_taikhoantab.setText("Đăng nhập");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_login_black_48dp);
                break;
            case TrangChu.KEY_DANGXUAT:
                holder.txtV_taikhoantab.setText("Đăng xuất");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_logout_black_48dp);
                break;
            case TrangChu.KEY_DOIMATKHAU:
                holder.txtV_taikhoantab.setText("Đổi mật khẩu");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_vpn_key_black_48dp);
                break;
            case TrangChu.KEY_QLTAIKHOAN:
                holder.txtV_taikhoantab.setText("Quản lý tài khoản");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_people_black_48dp);
                break;
            case TrangChu.KEY_QLSANPHAM:
                holder.txtV_taikhoantab.setText("Quản lý sản phẩm");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_description_black_48dp);
                break;
            case TrangChu.KEY_THONGTINCANHAN:
                holder.txtV_taikhoantab.setText("Thông tin cá nhân");
                holder.imgV_taikhoantab.setImageResource(R.drawable.ic_person_black_48dp);
                break;
        }
        
        holder.consL_taikhoantab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                
                switch (key){
                    case TrangChu.KEY_LSMH :
                        if(TrangChu.taikhoan.getIDTK() != -1){ context.startActivity(new Intent(context, LichSuMuaHang.class)); }
                        else { ThongBao(); }
                        break;
                    case TrangChu.KEY_YEUTHICH:
                        if(TrangChu.taikhoan.getIDTK() != - 1){ context.startActivity(new Intent(context, YeuThich.class)); }
                        else { ThongBao(); }
                        break;
                    case TrangChu.KEY_TROGIUP:
                        Toast.makeText(context, "Trợ giúp", Toast.LENGTH_SHORT).show();
                        break;
                    case TrangChu.KEY_DANGNHAP:
                        intent = new Intent(context, DangNhap.class);
                        intent.putExtra("isRoot",1);
                        context.startActivity(intent);
                        break;
                    case TrangChu.KEY_DANGXUAT:
                        TrangChu.taikhoan = new TaiKhoanDomain();
                        intent = new Intent(context, DangNhap.class);
                        intent.putExtra("isRoot",1);
                        context.startActivity(intent);
                        break;
                    case TrangChu.KEY_DOIMATKHAU:
                        if(TrangChu.taikhoan.getIDTK() != -1){ context.startActivity(new Intent(context, DoiMatKhau.class)); }
                        else { ThongBao(); }
                        break;
                    case TrangChu.KEY_QLTAIKHOAN:
                        context.startActivity(new Intent(context, QLTaiKhoan.class));
                        break;
                    case TrangChu.KEY_QLSANPHAM:
                        context.startActivity(new Intent(context, QLSanPham.class));
                        break;
                    case TrangChu.KEY_THONGTINCANHAN:
                        if(TrangChu.taikhoan.getIDTK() != -1){ context.startActivity(new Intent(context, ThongTinCaNhan.class)); }
                        else { ThongBao(); }
                        break;

                }
            }
        });
        
    }

    private void ThongBao(){
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Thông báo")
                .setMessage("Bạn phải đăng nhập để sử dụng chức năng này")
                .setPositiveButton("Đồng ý",null)
                .show();
    }

    @Override
    public int getItemCount() {
        if(listkey != null){
            return listkey.size();
        }
        return 0;
    }

    public class TaiKhoanTabAdapterHolder extends RecyclerView.ViewHolder{
        ImageView imgV_taikhoantab;
        TextView txtV_taikhoantab;
        CardView consL_taikhoantab;
        public TaiKhoanTabAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imgV_taikhoantab = itemView.findViewById(R.id.imgV_taikhoantab);
            txtV_taikhoantab = itemView.findViewById(R.id.txtV_taikhoantab);
            consL_taikhoantab = itemView.findViewById(R.id.consL_taikhoantab);
        }
    }
}
