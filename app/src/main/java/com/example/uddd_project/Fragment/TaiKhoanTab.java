package com.example.uddd_project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.uddd_project.Activity.DangNhap;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.R;

public class TaiKhoanTab extends Fragment {
    Button btn_Dangnhap_Tk;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.tab_tai_khoan, container, false);

        btn_Dangnhap_Tk = view.findViewById(R.id.btn_Dangnhap_Tk);

        btn_Dangnhap_Tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DangNhap.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
