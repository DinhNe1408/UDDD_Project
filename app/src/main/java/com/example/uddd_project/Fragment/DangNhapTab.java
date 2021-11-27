package com.example.uddd_project.Fragment;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uddd_project.Activity.DangNhap;
import com.example.uddd_project.Activity.QuanTri;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Adapter.DangNhapAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.TaiKhoanDomain;

public class DangNhapTab extends Fragment {
    Button btn_DangNhap_dn;
    EditText editT_SDT_dn,editT_MatKhau_dn;
    DAO dao;
    Context context;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view = (ViewGroup) inflater.inflate(R.layout.tab_dang_nhap, container, false);

        AnhXa();

        int isRoot = getActivity().getIntent().getIntExtra("isRoot",1);

        btn_DangNhap_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editT_SDT_dn.getText().length() == 0 || editT_MatKhau_dn.getText().length() == 0 ){
                    Toast.makeText(context,"Vui lòng nhập số điện thoại hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }else
                {
                    TaiKhoanDomain taikhoan = dao.DuLieuTK(editT_SDT_dn.getText().toString(),editT_MatKhau_dn.getText().toString());
                    if (taikhoan != null)
                    {
                        TrangChu.taikhoan = taikhoan;
                        if (isRoot == 1){
                            if(TrangChu.taikhoan.getQuyen().equals("USER")) {
                                Intent intent = new Intent(context, TrangChu.class);
                                intent.putExtra("posTab", 0);
                                startActivity(intent);
                                Toast.makeText(getContext(), "Xin chào " + taikhoan.getHoTen(), Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(getContext(), QuanTri.class));
                            }
                            }else {
                            getActivity().onBackPressed();
                        }

                    }else
                    {
                        Toast.makeText(context,"Vui lòng kiểm tra lại số điện thoại hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

    private void AnhXa() {
        context = view.getContext();

        dao = new DAO(view.getContext());
        btn_DangNhap_dn = view.findViewById(R.id.btn_DangNhap_dn);
        editT_SDT_dn = view.findViewById(R.id.editT_SDT_dn);
        editT_MatKhau_dn = view.findViewById(R.id.editT_MatKhau_dn);
    }
}
