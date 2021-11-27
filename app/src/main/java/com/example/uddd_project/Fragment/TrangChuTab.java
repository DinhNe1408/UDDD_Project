package com.example.uddd_project.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Adapter.SanPhamAdapter;
import com.example.uddd_project.Adapter.TieuDeTCApdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;
import com.example.uddd_project.TieuDeTCDomain;

import java.util.ArrayList;

public class TrangChuTab extends Fragment implements View.OnClickListener {
    private RecyclerView recV_TongDS_tc;
    DAO dao;
    Button btn_KhuyenMai_tc,btn_BanChay_tc,btn_Raucutuoi_tc;
    ArrayList<TieuDeTCDomain> dsTieuDe;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.SmoothScroller smoothScroller;



    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        View view =inflater.inflate(R.layout.tab_trang_chu, container, false);

        AnhXa(view);


        dsTieuDe = new ArrayList<TieuDeTCDomain>();
        dsTieuDe.add(new TieuDeTCDomain("Bán chạy",dao.LayBanChay()));
        dsTieuDe.add(new TieuDeTCDomain("Khuyến mãi",dao.LayBanChay()));
        dsTieuDe.add(new TieuDeTCDomain("Rau củ tươi",dao.LayTop10(2)));

        recV_TongDS_tc.setLayoutManager(linearLayoutManager);
        TieuDeTCApdapter tieuDeTCApdapter = new TieuDeTCApdapter(view.getContext(),dsTieuDe);
        recV_TongDS_tc.setAdapter(tieuDeTCApdapter);

        btn_KhuyenMai_tc.setOnClickListener(this);
        btn_BanChay_tc.setOnClickListener(this);
        btn_Raucutuoi_tc.setOnClickListener(this);
        return view;
    }

    void AnhXa(View view){
        dao = new DAO(getContext());
        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recV_TongDS_tc = view.findViewById(R.id.recV_TongDS_tc);
        btn_KhuyenMai_tc = view.findViewById(R.id.btn_KhuyenMai_tc);
        btn_BanChay_tc = view.findViewById(R.id.btn_BanChay_tc);
        btn_Raucutuoi_tc = view.findViewById(R.id.btn_Raucutuoi_tc);

        smoothScroller = new LinearSmoothScroller(view.getContext()) {
            @Override protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_BanChay_tc:
                ScrollToItem(0);
                break;
            case R.id.btn_KhuyenMai_tc:
                ScrollToItem(1);
                break;
            case R.id.btn_Raucutuoi_tc:
                ScrollToItem(2);
                break;
        }
    }

    private void ScrollToItem(int position) {
        smoothScroller.setTargetPosition(position);
        linearLayoutManager.startSmoothScroll(smoothScroller);
    }
}
