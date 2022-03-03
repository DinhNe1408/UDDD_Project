package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.util.Calendar;
import java.util.List;

public class DonHang extends AppCompatActivity {

    EditText editT_HoTen_dh, editT_SDT_dh, editT_DiaChi_dh, editT_GhiChu_dh;
    Button btn_XacNhan_dh;
    Toolbar tool3_DonHang_dh;
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);


        AnhXa();

        tool3_DonHang_dh.setTitle("Đơn hàng");
        tool3_DonHang_dh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_XacNhan_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editT_HoTen_dh.getText().length() != 0 && editT_SDT_dh.getText().length() != 0 &&
                        editT_DiaChi_dh.getText().length() != 0){
                    int idhd = dao.LayIDHD();

                    List<SanPhamDomain> listGH = dao.DuLieuGH(TrangChu.taikhoan.getIDTK());
                    Log.e("Tag",String.valueOf(listGH.size()));
                    if (idhd == -1){
                        dao.ThemHD(0,TrangChu.taikhoan.getIDTK(),editT_HoTen_dh.getText().toString(),
                                editT_SDT_dh.getText().toString(), editT_DiaChi_dh.getText().toString(),
                                Calendar.getInstance().getTime(), TinhTongTien(listGH),"CTT","CGH",
                                editT_GhiChu_dh.getText().toString());
                        ThemCTHD(0,listGH);
                    } else {
                        dao.ThemHD(idhd + 1,TrangChu.taikhoan.getIDTK(),editT_HoTen_dh.getText().toString(),
                                editT_SDT_dh.getText().toString(), editT_DiaChi_dh.getText().toString(),
                                Calendar.getInstance().getTime(),TinhTongTien(listGH) ,"CTT","CGH",
                                editT_GhiChu_dh.getText().toString());
                        ThemCTHD(idhd + 1,listGH);
                    }

                } else if ( editT_HoTen_dh.getText().length() == 0){
                    editT_HoTen_dh.setFocusable(true);
                    Toast.makeText(DonHang.this, "Vui lòng điền Họ và tên người nhận", Toast.LENGTH_SHORT).show();
                } else if ( editT_SDT_dh.getText().length() == 0){
                    editT_SDT_dh.setFocusable(true);
                    Toast.makeText(DonHang.this, "Vui lòng điền số điện thoại người nhận", Toast.LENGTH_SHORT).show();
                } else if ( editT_DiaChi_dh.getText().length() == 0 ) {
                    editT_DiaChi_dh.setFocusable(true);
                    Toast.makeText(DonHang.this, "Vui lòng điền số điện thoại người nhận", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private int TinhTongTien(List<SanPhamDomain> listGH){
        int TongTien = 0;
        for(int i = 0; i < listGH.size(); i++){
            TongTien += (listGH.get(i).getGiaSP() * listGH.get(i).getSLMSP());
        }
        return TongTien;
    }

    private void ThemCTHD(int idhd, List<SanPhamDomain> listGH){
        for(int i = 0; i < listGH.size(); i++){
            dao.ThemCTHD(idhd,listGH.get(i).getIDSP(),listGH.get(i).getTenSP()
                    ,listGH.get(i).getGiaSP(),listGH.get(i).getSLMSP());
        }

        dao.CapNhatSPGH(TrangChu.taikhoan.getIDTK(),0,-1);

        if(dao.LayIDHD() == idhd){
            Toast.makeText(DonHang.this, "Đặt hàng thành công! Bạn có thể kiểm tra tại Lịch sử đơn hàng", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DonHang.this,TrangChu.class));
        } else {
            Toast.makeText(DonHang.this, "Đặt hàng thất bại! Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        }
    }

    private void AnhXa() {
        dao = new DAO(this);
        tool3_DonHang_dh = findViewById(R.id.tool3_DonHang_dh);
        btn_XacNhan_dh = findViewById(R.id.btn_XacNhan_dh);

        editT_GhiChu_dh = findViewById(R.id.editT_GhiChu_dh);
        editT_HoTen_dh = findViewById(R.id.editT_HoTen_dh);
        editT_SDT_dh = findViewById(R.id.editT_SDT_dh);
        editT_DiaChi_dh = findViewById(R.id.editT_DiaChi_dh);
    }
}