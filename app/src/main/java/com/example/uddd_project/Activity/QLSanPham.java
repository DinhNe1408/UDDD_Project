package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.uddd_project.R;

public class QLSanPham extends AppCompatActivity {

    Toolbar tool3_QLSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsan_pham);

        AnhXa();
        tool3_QLSanPham.setTitle("Quản lý sản phẩm");
        tool3_QLSanPham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void AnhXa() {
        tool3_QLSanPham = findViewById(R.id.tool3_QLSanPham);
    }
}