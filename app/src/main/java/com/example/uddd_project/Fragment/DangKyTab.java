package com.example.uddd_project.Fragment;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uddd_project.Activity.DangNhap;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;

public class DangKyTab extends Fragment {

    EditText editT_SDT_Dk,editT_MatKhau_Dk, editT_HoTen_Dk;
    Button btn_DangKy_Dk;
    View view;
    DAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = inflater.inflate(R.layout.tab_dang_ky, container, false);
        AnhXa();


        btn_DangKy_Dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editT_SDT_Dk.getText().length() == 0 || editT_HoTen_Dk.length() == 0 || editT_MatKhau_Dk.length() == 0){
                    Toast.makeText(view.getContext(),"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else if (editT_SDT_Dk.getText().length() == 10){
                    if (dao.isTK(editT_SDT_Dk.getText().toString())){
                        dao.TaoTK(editT_SDT_Dk.getText().toString(),
                                editT_MatKhau_Dk.getText().toString(),
                                editT_HoTen_Dk.getText().toString(),"","USER");

                        if(dao.isTK(editT_SDT_Dk.getText().toString())){
                            Toast.makeText(view.getContext(), "Tạo tài khoản thất bại",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(view.getContext(), "Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(), DangNhap.class);
                            intent.putExtra("posTab_DN",0);
                            startActivity(intent);
                        }
                    }else {
                        Toast.makeText(view.getContext(), "Số điện thoại đã được sử dụng",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Vui lòng điền đúng định đạng số điện thoại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private void AnhXa() {
        dao = new DAO(view.getContext());
        btn_DangKy_Dk = view.findViewById(R.id.btn_DangKy_dk);

        editT_SDT_Dk = view.findViewById(R.id.editT_SDT_dk);
        editT_MatKhau_Dk = view.findViewById(R.id.editT_MatKhau_dk);
        editT_HoTen_Dk = view.findViewById(R.id.editT_HoTen_dk);
    }
}
