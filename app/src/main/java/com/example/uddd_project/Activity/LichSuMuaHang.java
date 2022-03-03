package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.uddd_project.Adapter.ExpandableAdapter;
import com.example.uddd_project.CTHoaDon;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.HoaDon;
import com.example.uddd_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LichSuMuaHang extends AppCompatActivity {

    Toolbar tool3_LSMH;
    RecyclerView recV_LSMH;
    DAO dao;
    ArrayList<HoaDon> listHD;
    Map <HoaDon,ArrayList<CTHoaDon>> listCTHD;
    ExpandableAdapter adapter;

    ExpandableListView expLV_lsmh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);

        AnhXa();
        tool3_LSMH.setTitle("Lịch sử mua hàng");
        tool3_LSMH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        listHD = dao.LayHoaDon(TrangChu.taikhoan.getIDTK());
        listCTHD = getListCTHD();

        adapter = new ExpandableAdapter(listHD,listCTHD);
        expLV_lsmh.setAdapter(adapter);
    }

    private Map<HoaDon,ArrayList<CTHoaDon>> getListCTHD(){
        Map<HoaDon,ArrayList<CTHoaDon>> listMap = new HashMap<>();

        for(int i = 0;i < listHD.size();i++){
            ArrayList<CTHoaDon> CTHoaDon = dao.LayCTHoaDon(listHD.get(i).getIDHD());
            listMap.put(listHD.get(i), CTHoaDon);
        }
        return listMap;
    }

    private void AnhXa() {
        dao = new DAO(this);
        tool3_LSMH = findViewById(R.id.tool3_LSMH);
        expLV_lsmh = findViewById(R.id.expLV_lsmh);
    }


}