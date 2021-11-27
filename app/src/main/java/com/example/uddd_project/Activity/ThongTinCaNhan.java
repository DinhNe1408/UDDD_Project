package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.google.android.material.textfield.TextInputEditText;

public class ThongTinCaNhan extends AppCompatActivity {

    Toolbar tool3_ThongTinCaNhan;
    TextInputEditText editT_DiaChi_ttcn,editT_HoTen_ttcn,editT_SDT_ttcn;
    Button btn_Sua_ttcn,btn_Huy_ttcn;
    DAO dao;
    boolean isEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

        AnhXa();
        SetValue();

        tool3_ThongTinCaNhan.setTitle("Thông tin cá nhân");
        tool3_ThongTinCaNhan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_Sua_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEnable){
                    if(editT_HoTen_ttcn.getText().length() !=0 && editT_DiaChi_ttcn.getText().length() !=0){
                        btn_Sua_ttcn.setText("Cập nhật");
                        dao.CapNhatTKNguoiDung(TrangChu.taikhoan.getIDTK(),editT_HoTen_ttcn.getText().toString(),editT_DiaChi_ttcn.getText().toString());
                        Toast.makeText(ThongTinCaNhan.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        TrangChu.taikhoan.setHoTen(editT_HoTen_ttcn.getText().toString());
                        TrangChu.taikhoan.setDiaChi(editT_DiaChi_ttcn.getText().toString());
                        isEnable = !isEnable;
                        En();
                    } else {
                        Toast.makeText(ThongTinCaNhan.this, "Vui lòng điền đủ các trường", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    isEnable = !isEnable;
                    En();
                    btn_Sua_ttcn.setText("Lưu");
                }
            }
        });

        btn_Huy_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetValue();
            }
        });

    }

    private void SetValue() {
        editT_SDT_ttcn.setText(TrangChu.taikhoan.getSDT());
        editT_DiaChi_ttcn.setText(TrangChu.taikhoan.getDiaChi());
        editT_HoTen_ttcn.setText(TrangChu.taikhoan.getHoTen());

        isEnable = false;
        btn_Sua_ttcn.setText("Cập nhật");
        En();
    }

    private void En(){
        editT_HoTen_ttcn.setEnabled(isEnable);
        editT_DiaChi_ttcn.setEnabled(isEnable);
    }

    private void AnhXa() {
        dao = new DAO(this);

        tool3_ThongTinCaNhan = findViewById(R.id.tool3_ThongTinCaNhan);
        editT_DiaChi_ttcn = findViewById(R.id.editT_DiaChi_ttcn);
        editT_HoTen_ttcn = findViewById(R.id.editT_HoTen_ttcn);
        editT_SDT_ttcn = findViewById(R.id.editT_SDT_ttcn);

        btn_Sua_ttcn = findViewById(R.id.btn_Sua_ttcn);
        btn_Huy_ttcn = findViewById(R.id.btn_Huy_ttcn);
    }

}