package com.example.uddd_project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.uddd_project.Adapter.TrangChuAdapter;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;
import com.example.uddd_project.ThongBao;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Date;

public class TrangChu extends AppCompatActivity {

    public static final int KEY_LSMH = 0;
    public static final int KEY_YEUTHICH = 1;
    public static final int KEY_TROGIUP = 2;
    public static final int KEY_DANGNHAP = 3;
    public static final int KEY_DANGXUAT = 4;
    public static final int KEY_DOIMATKHAU = 5;
    public static final int KEY_QLTAIKHOAN = 6;
    public static final int KEY_QLSANPHAM = 7;
    public static final int KEY_QLHOADON = 8;
    public static final int KEY_THONGTINCANHAN = 9;

    private ViewPager2 viewP_Tc;
    BottomNavigationView navigationView;
    public static TaiKhoanDomain taikhoan = new TaiKhoanDomain();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        AnhXa();

        TrangChuAdapter viewP_TC_Adap = new TrangChuAdapter(this);
        viewP_Tc.setAdapter(viewP_TC_Adap);
        setViewP_Tc();
        setNavigationView();

        int posTab = getIntent().getIntExtra("posTab",0);
        viewP_Tc.setCurrentItem(posTab);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Thông báo")
                .setMessage("Bạn có chắc muốn thoát không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    void AnhXa(){
        navigationView = findViewById(R.id.navigation_view);
        viewP_Tc = findViewById(R.id.viewP_Tc);
    }

    private void setNavigationView() {
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case  R.id.trangchu:
                        viewP_Tc.setCurrentItem(0);
                        break;
                    case R.id.giohang:
                        viewP_Tc.setCurrentItem(1);
                        break;
                    case R.id.danhmuc:
                        viewP_Tc.setCurrentItem(2);
                        break;
                    case R.id.taikhoan:
                        viewP_Tc.setCurrentItem(3);
                        break;
                    default:
                        viewP_Tc.setCurrentItem(0);
                }
                return true;
            }
        });
    }

    private void setViewP_Tc(){
        viewP_Tc.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.trangchu).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.giohang).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.danhmuc).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.taikhoan).setChecked(true);
                        break;
                    default:
                        navigationView.getMenu().findItem(R.id.trangchu).setChecked(true);
                        break;
                }
            }
        });
    }

}