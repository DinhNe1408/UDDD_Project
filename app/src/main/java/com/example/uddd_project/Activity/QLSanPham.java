package com.example.uddd_project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.uddd_project.Activity.QL.UpSanPham;
import com.example.uddd_project.Adapter.GioHangAdapter;
import com.example.uddd_project.Adapter.QLSPAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.util.ArrayList;

public class QLSanPham extends AppCompatActivity {

    Toolbar tool3_QLSanPham;
    SearchView iTimkiem;
    QLSPAdapter adapter;
    DAO dao;
    ArrayList<SanPhamDomain> listSP;
    RecyclerView recV_DSsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsan_pham);

        AnhXa();
        setSupportActionBar(tool3_QLSanPham);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tool3_QLSanPham.setTitle("Quản lý sản phẩm");
        tool3_QLSanPham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registerForContextMenu(recV_DSsanpham);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_ql_tai_khoan, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        iTimkiem = (SearchView) menu.findItem(R.id.iTimkiem).getActionView();
        iTimkiem.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        iTimkiem.setMaxWidth(Integer.MAX_VALUE);

        iTimkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        return true;
    }

    @Override
    protected void onStart() {
        listSP = dao.ListSP(-1);
        adapter = new QLSPAdapter(this,listSP);
        recV_DSsanpham.setLayoutManager(new LinearLayoutManager(QLSanPham.this,LinearLayoutManager.VERTICAL,false));
        recV_DSsanpham.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QLSPAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent intent = new Intent(QLSanPham.this, UpSanPham.class);
                intent.putExtra("Type",2);
                intent.putExtra("IDSP", adapter.listSP.get(position).getIDSP());
                startActivity(intent);
                return true;
            case R.id.iXoa:
                int pos = position;
                new AlertDialog.Builder(QLSanPham.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc muốn xóa sản phẩm này không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.XoaSP(listSP.get(pos).getIDSP());

                                listSP.remove(pos);
                                adapter.notifyItemRemoved(pos);
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iAdd:
                Intent intent = new Intent(QLSanPham.this, UpSanPham.class);
                intent.putExtra("Type",1);
                startActivity(intent);
                return true;
            case R.id.iTimkiem:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void AnhXa() {
        dao = new DAO(this);

        recV_DSsanpham = findViewById(R.id.recV_DSsanpham);
        tool3_QLSanPham = findViewById(R.id.tool3_QLSanPham);
    }
}