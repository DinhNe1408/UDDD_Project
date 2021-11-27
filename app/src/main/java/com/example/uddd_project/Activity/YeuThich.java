package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.uddd_project.Adapter.SanPhamAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.util.ArrayList;

public class YeuThich extends AppCompatActivity {

    TextView txt_ThongBao1;
    Toolbar tool3_YeuThich_yt;
    RecyclerView recV_YeuThich_yt;
    DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich);
        AnhXa();

        tool3_YeuThich_yt.setTitle("Yêu thích");
        tool3_YeuThich_yt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<SanPhamDomain> list = dao.LayYeuThich(TrangChu.taikhoan.getIDTK());
        recV_YeuThich_yt.setAdapter(new SanPhamAdapter(this,list));
        recV_YeuThich_yt.setLayoutManager(new GridLayoutManager(YeuThich.this, 2));
        if (list.size() == 0){
            txt_ThongBao1.setVisibility(View.VISIBLE);
            txt_ThongBao1.setText("Bạn không có sản phẩm yêu thích!");
        } else {
            txt_ThongBao1.setVisibility(View.INVISIBLE);
        }
    }

    private void AnhXa() {
        dao = new DAO(this);
        txt_ThongBao1 = findViewById(R.id.txt_ThongBao1);
        recV_YeuThich_yt = findViewById(R.id.recV_YeuThich_yt);
        tool3_YeuThich_yt = findViewById(R.id.tool3_YeuThich_yt);
    }
}