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
import com.example.uddd_project.Activity.QL.UpTaiKhoan;
import com.example.uddd_project.Adapter.QLSPAdapter;
import com.example.uddd_project.Adapter.QLTKAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.ArrayList;

public class QLTaiKhoan extends AppCompatActivity {

    Toolbar tool3_QLTaiKhoan;
    RecyclerView recV_DStaikhoan;
    DAO dao;
    ArrayList<TaiKhoanDomain> listTK;
    QLTKAdapter adapter;
    SearchView iTimkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltai_khoan);

        AnhXa();

        setSupportActionBar(tool3_QLTaiKhoan);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        tool3_QLTaiKhoan.setTitle("Quản lý tài khoản");
        tool3_QLTaiKhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        registerForContextMenu(recV_DStaikhoan);
    }

    @Override
    protected void onStart() {
        listTK = dao.LayTatCaTK();
        adapter = new QLTKAdapter(this, listTK);
        recV_DStaikhoan.setLayoutManager( new LinearLayoutManager(QLTaiKhoan.this,LinearLayoutManager.VERTICAL,false));
        recV_DStaikhoan.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = QLTKAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.iSua:
                Intent intent = new Intent(QLTaiKhoan.this, UpTaiKhoan.class);
                intent.putExtra("Type",2);
                intent.putExtra("SDT", adapter.listTK.get(position).getSDT());
                intent.putExtra("MK", adapter.listTK.get(position).getMatKhau());
                startActivity(intent);
                return true;
            case R.id.iXoa:
                int pos = position;
                new AlertDialog.Builder(QLTaiKhoan.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc muốn xóa tài khoản này không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.XoaTK(listTK.get(pos).getIDTK());

                                listTK.remove(pos);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iAdd:
                Intent intent = new Intent(QLTaiKhoan.this, UpTaiKhoan.class);
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
        recV_DStaikhoan = findViewById(R.id.recV_DStaikhoan);
        tool3_QLTaiKhoan = findViewById(R.id.tool3_QLTaiKhoan);
    }
}