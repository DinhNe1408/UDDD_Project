package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;

public class DonHang extends AppCompatActivity {

    EditText editT_HoTen_dh, editT_SDT_dh, editT_DiaChi_dh;
    Button btn_Huy_dh,btn_XacNhan_dh;
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
                int idhd = dao.LayIDHD();
                if (idhd == -1){

                }
            }
        });
    }

    private void AnhXa() {
        dao = new DAO(this);
        tool3_DonHang_dh = findViewById(R.id.tool3_DonHang_dh);
        btn_Huy_dh = findViewById(R.id.btn_Huy_dh);
        btn_XacNhan_dh = findViewById(R.id.btn_XacNhan_dh);

        editT_HoTen_dh = findViewById(R.id.editT_HoTen_dh);
        editT_SDT_dh = findViewById(R.id.editT_SDT_dh);
        editT_DiaChi_dh = findViewById(R.id.editT_DiaChi_dh);
    }
}