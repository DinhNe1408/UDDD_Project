package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.uddd_project.Adapter.TaiKhoanTabAdapter;
import com.example.uddd_project.R;

import java.util.ArrayList;
import java.util.List;

public class QuanTri extends AppCompatActivity {

    List<Integer> listkey;

    RecyclerView recV_QuanTri_qt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_tri);

        AnhXa();
        listkey = new ArrayList<>();
        listkey.add(TrangChu.KEY_QLTAIKHOAN);
        listkey.add(TrangChu.KEY_QLSANPHAM);
        listkey.add(TrangChu.KEY_QLHOADON);
        listkey.add(TrangChu.KEY_DANGXUAT);

        recV_QuanTri_qt.setAdapter(new TaiKhoanTabAdapter(this,listkey));
        recV_QuanTri_qt.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    private void AnhXa() {
        recV_QuanTri_qt = findViewById(R.id.recV_QuanTri_qt);
    }
}