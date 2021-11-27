package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.uddd_project.R;

public class QLTaiKhoan extends AppCompatActivity {

    Toolbar tool3_QLTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltaikhoan);

        AnhXa();
        tool3_QLTaiKhoan.setTitle("Quản lý tài khoản");
        tool3_QLTaiKhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void AnhXa() {
        tool3_QLTaiKhoan = findViewById(R.id.tool3_QLTaiKhoan);
    }
}