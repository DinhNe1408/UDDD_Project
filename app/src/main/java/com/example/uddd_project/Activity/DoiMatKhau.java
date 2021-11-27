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

public class DoiMatKhau extends AppCompatActivity {

    TextInputEditText editT_MatKhauCu_dmk,editT_MatKhauMoi_dmk,editT_NLMatKhauMoi_dmk;
    Button btn_DoiMatKhau_dmk;
    Toolbar tool3_DoiMatKhau_dmk;
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        AnhXa();



        tool3_DoiMatKhau_dmk.setTitle("Đổi mật khẩu");
        tool3_DoiMatKhau_dmk.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_DoiMatKhau_dmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editT_MatKhauCu_dmk.getText().length() != 0 && editT_MatKhauMoi_dmk.getText().length() != 0 && editT_NLMatKhauMoi_dmk.getText().length() != 0){
                    if(editT_MatKhauCu_dmk.getText().toString().equals(TrangChu.taikhoan.getMatKhau())){
                        if(editT_MatKhauMoi_dmk.getText().toString().equals(editT_NLMatKhauMoi_dmk.getText().toString())){
                            dao.DoiMatKhau(TrangChu.taikhoan.getIDTK(),editT_MatKhauMoi_dmk.getText().toString());
                            TrangChu.taikhoan.setMatKhau(editT_MatKhauMoi_dmk.getText().toString());
                            Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DoiMatKhau.this, "Nhập lại mật khẩu phải trùng với mật khẩu mới", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoiMatKhau.this, "Mật khẩu cũ sai!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoiMatKhau.this, "Vui lòng điền đầy đủ các trường!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void AnhXa(){
        dao = new DAO(DoiMatKhau.this);
        btn_DoiMatKhau_dmk = findViewById(R.id.btn_DoiMatKhau_dmk);
        editT_NLMatKhauMoi_dmk = findViewById(R.id.editT_NLMatKhauMoi_dmk);
        editT_MatKhauMoi_dmk = findViewById(R.id.editT_MatKhauMoi_dmk);
        editT_MatKhauCu_dmk = findViewById(R.id.editT_MatKhauCu_dmk);
        tool3_DoiMatKhau_dmk = findViewById(R.id.tool3_DoiMatKhau_dmk);
    }
}