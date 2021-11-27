package com.example.uddd_project.Adapter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.uddd_project.Fragment.DanhMucTab;
import com.example.uddd_project.Fragment.GioHangTab;
import com.example.uddd_project.Fragment.TaiKhoanTab;
import com.example.uddd_project.Fragment.TrangChuTab;

public class TrangChuAdapter extends FragmentStateAdapter {


    public TrangChuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
    public int getItemCount() {
        return 4;
    }
}
