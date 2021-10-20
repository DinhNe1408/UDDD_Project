package com.example.uddd_project.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.uddd_project.Fragment.DanhMucTab;
import com.example.uddd_project.Fragment.GioHangTab;
import com.example.uddd_project.Fragment.TaiKhoanTab;
import com.example.uddd_project.Fragment.TrangChuTab;

public class TrangChuAdapter extends FragmentStatePagerAdapter {

    public TrangChuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TrangChuTab();
            case 1:
                return new GioHangTab();
            case 2:
                return new DanhMucTab();
            case 3:
                return new TaiKhoanTab();
            default:
                return new TrangChuTab();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
