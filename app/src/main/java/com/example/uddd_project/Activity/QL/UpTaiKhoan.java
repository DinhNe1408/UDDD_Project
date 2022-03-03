package com.example.uddd_project.Activity.QL;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

public class UpTaiKhoan extends AppCompatActivity {

    EditText editT_SDT_uptk, editT_MatKhau_uptk, editT_HoTen_uptk, editT_DiaChi_uptk;
    Toolbar tool3_QLUp;
    Button btn_Huy_uptk,btn_XacNhan_uptk ;
    boolean isEnable;
    DAO dao;
    TaiKhoanDomain taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_tai_khoan);

        AnhXa();

        setSupportActionBar(tool3_QLUp);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        tool3_QLUp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        int type = getIntent().getIntExtra("Type",0);

        if(type == 1){
            tool3_QLUp.setTitle("Thêm tài khoản");
            btn_Huy_uptk.setText("Làm mới");
        } else if(type == 2){

            taikhoan = dao.DuLieuTK(getIntent().getStringExtra("SDT"),
                    getIntent().getStringExtra("MK"));
            tool3_QLUp.setTitle("Sửa tài khoản");
            btn_XacNhan_uptk.setText("Cập nhật");
            LoadText(taikhoan.getSDT(),taikhoan.getMatKhau(),taikhoan.getHoTen(),taikhoan.getDiaChi());
            isEnable = false;
            HienThi();
        } else {
            return;
        }

        btn_XacNhan_uptk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1){
                    TaoTaiKhoan();
                } else if(type == 2){
                    if (isEnable){
                        isEnable = !isEnable;
                        HienThi();

                        taikhoan.setHoTen(editT_HoTen_uptk.getText().toString());
                        taikhoan.setMatKhau(editT_MatKhau_uptk.getText().toString());
                        taikhoan.setDiaChi(editT_DiaChi_uptk.getText().toString());

                        CapNhatTaiKhoan();
                        Toast.makeText(UpTaiKhoan.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        btn_XacNhan_uptk.setText("Cập nhật");
                    } else
                    {
                        isEnable = !isEnable;
                        HienThi();
                        editT_SDT_uptk.setEnabled(false);
                        btn_XacNhan_uptk.setText("Lưu");
                    }

                }
            }
        });

        btn_Huy_uptk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1){
                    LoadText("","","","");
                } else {
                    isEnable = false;
                    HienThi();
                    Toast.makeText(UpTaiKhoan.this, taikhoan.getSDT(), Toast.LENGTH_SHORT).show();
                    LoadText(taikhoan.getSDT(),taikhoan.getMatKhau(),taikhoan.getHoTen(),taikhoan.getDiaChi());
                }
            }
        });
    }

    private void CapNhatTaiKhoan() {
        if(editT_HoTen_uptk.getText().length() > 0 && editT_DiaChi_uptk.getText().length() > 0
                && editT_SDT_uptk.getText().length() > 0 && editT_MatKhau_uptk.getText().length() > 0){
            dao.CapNhatTKNguoiDung(taikhoan.getIDTK(),taikhoan.getHoTen(),taikhoan.getDiaChi());
            dao.DoiMatKhau(taikhoan.getIDTK(),taikhoan.getMatKhau());
            //dao.CapNhatQuyen(taikhoan.getIDTK(),quyen);
        } else {
            Toast.makeText(UpTaiKhoan.this, "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }
    }

    private void TaoTaiKhoan(){
        if(editT_HoTen_uptk.getText().length() > 0 && editT_DiaChi_uptk.getText().length() > 0
                && editT_SDT_uptk.getText().length() > 0 && editT_MatKhau_uptk.getText().length() > 0){
            if (dao.isTK(editT_SDT_uptk.getText().toString())){
                dao.TaoTK(editT_SDT_uptk.getText().toString(),editT_MatKhau_uptk.getText().toString(),
                        editT_HoTen_uptk.getText().toString(), editT_DiaChi_uptk.getText().toString(),
                        "");

                Toast.makeText(UpTaiKhoan.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpTaiKhoan.this, "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(UpTaiKhoan.this, "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }
    }

    private void HienThi(){
        editT_SDT_uptk.setEnabled(isEnable);
        editT_MatKhau_uptk.setEnabled(isEnable);
        editT_HoTen_uptk.setEnabled(isEnable);
        editT_DiaChi_uptk.setEnabled(isEnable);
    }

    private void LoadText(String sdt, String matkhau, String hoten, String diachi){
        editT_SDT_uptk.setText(sdt);
        editT_MatKhau_uptk.setText(matkhau);
        editT_HoTen_uptk.setText(hoten);
        editT_DiaChi_uptk.setText(diachi);
    }

    private void AnhXa() {
        dao = new DAO(UpTaiKhoan.this);
        tool3_QLUp = findViewById(R.id.tool3_QLUp);
        editT_SDT_uptk = findViewById(R.id.editT_IDSP_upsp);
        editT_MatKhau_uptk = findViewById(R.id.editT_TenSP_upsp);
        editT_HoTen_uptk = findViewById(R.id.editT_SLSP_upsp);
        editT_DiaChi_uptk = findViewById(R.id.editT_SLMTD_upsp);
        btn_Huy_uptk = findViewById(R.id.btn_Huy_upsp);
        btn_XacNhan_uptk = findViewById(R.id.btn_Camera_upsp);

    }
}