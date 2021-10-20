package com.example.uddd_project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.uddd_project.Adapter.TrangChuAdapter;
import com.example.uddd_project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChu extends AppCompatActivity {

    private ViewPager viewP_Tc;
    BottomNavigationView navigationView;
    public boolean da_DangNhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        navigationView = findViewById(R.id.navigation_view);
        viewP_Tc = findViewById(R.id.viewP_Tc);

        setViewP_Tc();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.trangchu:
                        viewP_Tc.setCurrentItem(0);
                        break;
                    case R.id.giohang:
                        if ( da_DangNhap ){
                            viewP_Tc.setCurrentItem(1);
                        }
                        else {
                            Intent intent = new Intent(TrangChu.this, DangNhap.class);
                            startActivity(intent);
                        }
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
        TrangChuAdapter viewP_TC_Adap = new TrangChuAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewP_Tc.setAdapter(viewP_TC_Adap);

        viewP_Tc.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
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

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}