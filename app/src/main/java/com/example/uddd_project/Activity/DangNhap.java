package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.uddd_project.Adapter.DangNhapAdapter;
import com.example.uddd_project.R;
import com.google.android.material.tabs.TabLayout;

public class DangNhap extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    EditText editT_TaiKhoan,editT_MatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        AnhXa();

        int posTab = getIntent().getIntExtra("posTab_DN",0);
        viewPager.setCurrentItem(posTab);

        DangNhapAdapter dangNhapAdapter = new DangNhapAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(dangNhapAdapter);

        tablayout.setupWithViewPager(viewPager);
    }

    private void AnhXa() {
        tablayout = findViewById(R.id.tabL_Dn);
        viewPager = findViewById(R.id.viewP_Dn);

        editT_TaiKhoan = findViewById(R.id.editT_HoTen_dk);
        editT_MatKhau = findViewById(R.id.editT_MatKhau_dk);
    }
}