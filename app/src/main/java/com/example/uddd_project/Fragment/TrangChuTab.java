package com.example.uddd_project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Adapter.SanPhamAdapter;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.util.ArrayList;
import java.util.List;

public class TrangChuTab extends Fragment {
    private RecyclerView recV_DSBanchay_Tc,recV_DSKhuyenmai_Tc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view =inflater.inflate(R.layout.tab_trang_chu, container, false);

        recV_DSBanchay_Tc = view.findViewById(R.id.recV_Banchay_Tc);
        recV_DSKhuyenmai_Tc = view.findViewById(R.id.recV_Khuyenmai_Tc);

        recV_Banchay_Tc();
        recV_Khuyenmai_Tc();
        return view;
    }

    private void recV_Khuyenmai_Tc() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recV_DSKhuyenmai_Tc.setLayoutManager(gridLayoutManager);
        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getListSPKhuyenMai());
        recV_DSKhuyenmai_Tc.setAdapter(sanPhamAdapter);
    }

    private List<SanPhamDomain> getListSPKhuyenMai() {
        List < SanPhamDomain> DSsanPham = new ArrayList<>();
        DSsanPham.add(new SanPhamDomain("Sản phẩm 1",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 2",R.drawable.sp1,24000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 3",R.drawable.sp1,10000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 4",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 5",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 6",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 7",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 8",R.drawable.sp1,12000.0));
        return DSsanPham;
    }

    private void recV_Banchay_Tc() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recV_DSBanchay_Tc.setLayoutManager(gridLayoutManager);
        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(getListSPBanChay());
        recV_DSBanchay_Tc.setAdapter(sanPhamAdapter);
//        ArrayList<SanPhamDomain> sanPham = new ArrayList<>();
//        sanPham.add(new SanPhamDomain("Sản phẩm 1","sp1",12000.0));
//        sanPham.add(new SanPhamDomain("Sản phẩm 2","sp1",53000.0));
//        sanPham.add(new SanPhamDomain("Sản phẩm 3","sp1",15000.0));
//        sanPham.add(new SanPhamDomain("Sản phẩm 4","sp1",21000.0));
    }

    private List<SanPhamDomain> getListSPBanChay() {
        List < SanPhamDomain> DSsanPham = new ArrayList<>();
        DSsanPham.add(new SanPhamDomain("Sản phẩm 1",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 2",R.drawable.sp1,24000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 3",R.drawable.sp1,10000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 4",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 5",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 6",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 7",R.drawable.sp1,12000.0));
        DSsanPham.add(new SanPhamDomain("Sản phẩm 8",R.drawable.sp1,12000.0));
        return DSsanPham;
    }
}
