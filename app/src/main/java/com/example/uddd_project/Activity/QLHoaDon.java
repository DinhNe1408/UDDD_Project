package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.uddd_project.R;

public class QLHoaDon extends AppCompatActivity {

    Toolbar tool3_QLHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlhoa_don);

        AnhXa();
        tool3_QLHoaDon.setTitle("Quản lý hóa đơn");
        tool3_QLHoaDon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void AnhXa() {
        tool3_QLHoaDon = findViewById(R.id.tool3_DonHang_dh);
    }
}