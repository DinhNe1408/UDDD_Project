package com.example.uddd_project.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.uddd_project.Fragment.DangKyTab;
import com.example.uddd_project.Fragment.DangNhapTab;

public class DangNhapAdapter extends FragmentStatePagerAdapter {

    public DangNhapAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DangNhapTab();
            case 1:
                return new DangKyTab();
            default:
                return new DangNhapTab();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Đăng nhập";
                break;
            case 1:
                title = "Đăng ký";
                break;
        }
        return title;
    }
}
