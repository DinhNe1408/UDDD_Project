package com.example.uddd_project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Adapter.DanhMucAdapter;
import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.R;

import java.util.ArrayList;
import java.util.List;

public class DanhMucTab extends Fragment {
    private RecyclerView recV_Danhmuc_Dm;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.tab_danh_muc, container, false);

        recV_Danhmuc_Dm = view.findViewById(R.id.recV_Danhmuc_Dm);
        recV_Danhmuc_Dm();

        return view;
    }

    private void recV_Danhmuc_Dm() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recV_Danhmuc_Dm.setLayoutManager(gridLayoutManager);
        DanhMucAdapter danhMucAdapter = new DanhMucAdapter(getListDanhMuc());
        recV_Danhmuc_Dm.setAdapter(danhMucAdapter);
    }

    private List<DanhMucDomain> getListDanhMuc() {
        List < DanhMucDomain> DSDanhmuc = new ArrayList<>();
        DSDanhmuc.add(new DanhMucDomain("DM1","Rau Củ Tươi",R.drawable.vegetable));
        DSDanhmuc.add(new DanhMucDomain("DM2","Trái Cây Tươi",R.drawable.fruits));
        DSDanhmuc.add(new DanhMucDomain("DM3","Cà phê",R.drawable.coffeecup));
        DSDanhmuc.add(new DanhMucDomain("DM4","Đồ hộp",R.drawable.canned_food));
        DSDanhmuc.add(new DanhMucDomain("DM5","Trứng và sữa",R.drawable.food));
        DSDanhmuc.add(new DanhMucDomain("DM6","Nước giải khát",R.drawable.softdrink));
        return DSDanhmuc;
    }
}
