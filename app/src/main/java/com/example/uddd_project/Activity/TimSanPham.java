package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uddd_project.Adapter.SanPhamAdapter;
import com.example.uddd_project.Adapter.SpinDMAdapter;
import com.example.uddd_project.Adapter.TimKiemAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.util.ArrayList;

public class TimSanPham extends AppCompatActivity {

    RecyclerView recV_TimKiem_tk;
    Spinner spin_DanhMuc_tk;
    ArrayList<SanPhamDomain> listSP;
    ArrayList<DanhMucDomain> listDM;
    SpinDMAdapter spinDMAdapter;
    DAO dao;
    SearchView menu_timkiem_tc;
    TimKiemAdapter adapter;
    Toolbar tool3_TimSanPham_tsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_san_pham);

        AnhXa();
        SuKien();
        listDM = new ArrayList<>();
        listDM.add(new DanhMucDomain(-1,"Tất cả"));
        listDM.addAll(dao.LayDanhMuc());

        spinDMAdapter = new SpinDMAdapter(this,R.layout.viewholder_spin_dm_b, listDM);
        spin_DanhMuc_tk.setAdapter(spinDMAdapter);

        int iddm = getIntent().getIntExtra("idDM",-2);
        if(iddm != - 2){
            for(int i = 0; i< listDM.size();i++){
                if(iddm == listDM.get(i).getIDDM()){
                    spin_DanhMuc_tk.setSelection(i);
                    break;
                }
            }
            listSP = dao.ListSP(iddm);
        } else {
            spin_DanhMuc_tk.setSelection(0);
            listSP = dao.ListSP(-1);
        }


        adapter = new TimKiemAdapter(TimSanPham.this,listSP);
        recV_TimKiem_tk.setAdapter(adapter);
        recV_TimKiem_tk.setLayoutManager(new GridLayoutManager(TimSanPham.this,2));

        // spinDMAdapter.getItem()


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trang_chu,menu);

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        menu_timkiem_tc = (SearchView) menu.findItem(R.id.menu_timkiem_tc).getActionView();
//        menu_timkiem_tc.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        menu_timkiem_tc.setMaxWidth(Integer.MAX_VALUE);
        Toast.makeText(TimSanPham.this, "d", Toast.LENGTH_SHORT).show();
        menu_timkiem_tc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void XuLy(){
        DanhMucDomain x = listDM.get(spin_DanhMuc_tk.getSelectedItemPosition());
        listSP.clear();
        listSP.addAll(dao.ListSP(x.getIDDM()));
        adapter.notifyDataSetChanged();

    }

    private void SuKien(){

        spin_DanhMuc_tk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                XuLy();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tool3_TimSanPham_tsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void AnhXa() {
        dao = new DAO(TimSanPham.this);
        tool3_TimSanPham_tsp = findViewById(R.id.tool3_TimSanPham_tsp);
        recV_TimKiem_tk = findViewById(R.id.recV_TimKiem_tk);
        spin_DanhMuc_tk = findViewById(R.id.spin_DanhMuc_tk);
    }

}