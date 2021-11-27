package com.example.uddd_project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.DangNhap;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Adapter.TaiKhoanTabAdapter;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanTab extends Fragment {



    View view;
    RecyclerView recV_taikhoantab;
    List<Integer> listkey;
    TaiKhoanTabAdapter adapter;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        view = inflater.inflate(R.layout.tab_tai_khoan, container, false);
        AnhXa();

        listkey = new ArrayList<>();

        listkey.add(TrangChu.KEY_THONGTINCANHAN);
        listkey.add(TrangChu.KEY_DOIMATKHAU);
        listkey.add(TrangChu.KEY_LSMH);
        listkey.add(TrangChu.KEY_YEUTHICH);
        listkey.add(TrangChu.KEY_TROGIUP);
        listkey.add(TrangChu.KEY_DANGNHAP);

        LinearLayoutManager linear = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        adapter = new TaiKhoanTabAdapter(getContext(), listkey);

        adapter.getItemId(listkey.size()-1);
        recV_taikhoantab.setLayoutManager(linear);
        recV_taikhoantab.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(TrangChu.taikhoan.getIDTK() == -1){
            listkey.remove(adapter.getItemCount()-1);
            listkey.add(TrangChu.KEY_DANGNHAP);
            adapter.notifyDataSetChanged();
        }
        else{ listkey.remove(adapter.getItemCount()-1);
            listkey.add(TrangChu.KEY_DANGXUAT);
            adapter.notifyDataSetChanged(); }
    }

    void AnhXa(){
        recV_taikhoantab = view.findViewById(R.id.recV_taikhoantab);
    }
}
