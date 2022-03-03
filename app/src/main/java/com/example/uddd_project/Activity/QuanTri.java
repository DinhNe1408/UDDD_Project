package com.example.uddd_project.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.uddd_project.Adapter.TaiKhoanTabAdapter;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.ArrayList;
import java.util.List;

public class QuanTri extends AppCompatActivity {

    List<Integer> listkey;

    RecyclerView recV_QuanTri_qt;
    Toolbar tool3_QuanTri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_tri);

        AnhXa();
        setSupportActionBar(tool3_QuanTri);
        ActionBar actionBar = getSupportActionBar();
        tool3_QuanTri.setTitle("Quản trị viên");
        tool3_QuanTri.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        listkey = new ArrayList<>();
        listkey.add(TrangChu.KEY_QLTAIKHOAN);
        listkey.add(TrangChu.KEY_QLSANPHAM);
        listkey.add(TrangChu.KEY_DOIMATKHAU);
        listkey.add(TrangChu.KEY_DANGXUAT);

        recV_QuanTri_qt.setAdapter(new TaiKhoanTabAdapter(this,listkey));
        recV_QuanTri_qt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(QuanTri.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc muốn đăng xuất?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TrangChu.taikhoan = new TaiKhoanDomain();
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void AnhXa() {
        tool3_QuanTri = findViewById(R.id.tool3_QuanTri);
        recV_QuanTri_qt = findViewById(R.id.recV_QuanTri_qt);
    }
}