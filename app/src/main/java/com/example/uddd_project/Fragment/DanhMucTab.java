package com.example.uddd_project.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Adapter.DanhMucAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;

public class DanhMucTab extends Fragment {
    private RecyclerView recV_Danhmuc_Dm;
    DAO dao;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.tab_danh_muc, container, false);

        dao = new DAO(getContext());

        recV_Danhmuc_Dm = view.findViewById(R.id.recV_Danhmuc_Dm);
        recV_Danhmuc_Dm();

        return view;
    }

    private void recV_Danhmuc_Dm() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recV_Danhmuc_Dm.setLayoutManager(gridLayoutManager);

        DanhMucAdapter danhMucAdapter = new DanhMucAdapter(getContext(),dao.LayDanhMuc());
        recV_Danhmuc_Dm.setAdapter(danhMucAdapter);
    }
}
