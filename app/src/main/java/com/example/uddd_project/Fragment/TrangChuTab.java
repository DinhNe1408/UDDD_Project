package com.example.uddd_project.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.TimSanPham;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Adapter.SanPhamAdapter;
import com.example.uddd_project.Adapter.TieuDeTCApdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;
import com.example.uddd_project.TieuDeTCDomain;

import java.util.ArrayList;
import java.util.Objects;

public class TrangChuTab extends Fragment implements View.OnClickListener {
    private RecyclerView recV_TongDS_tc;
    DAO dao;
    Button btn_KhuyenMai_tc,btn_BanChay_tc,btn_Raucutuoi_tc;
    ArrayList<TieuDeTCDomain> dsTieuDe;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.SmoothScroller smoothScroller;
    SearchView menu_timkiem_tc;
    Toolbar tool3_TrangChuTab;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        View view =inflater.inflate(R.layout.tab_trang_chu, container, false);

        AnhXa(view);
        setHasOptionsMenu(true);

        ((AppCompatActivity)getActivity()).setSupportActionBar(tool3_TrangChuTab);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        dsTieuDe = new ArrayList<TieuDeTCDomain>();
        dsTieuDe.add(new TieuDeTCDomain("Bán chạy",dao.LayBanChay()));
        dsTieuDe.add(new TieuDeTCDomain("Khuyến mãi",dao.LayKhuyenMai()));
        dsTieuDe.add(new TieuDeTCDomain("Rau củ tươi",dao.LayTop10(2)));

        recV_TongDS_tc.setLayoutManager(linearLayoutManager);
        TieuDeTCApdapter tieuDeTCApdapter = new TieuDeTCApdapter(view.getContext(),dsTieuDe);
        recV_TongDS_tc.setAdapter(tieuDeTCApdapter);

        btn_KhuyenMai_tc.setOnClickListener(this);
        btn_BanChay_tc.setOnClickListener(this);
        btn_Raucutuoi_tc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_trang_chu,menu);

        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        menu_timkiem_tc = (SearchView) menu.findItem(R.id.menu_timkiem_tc).getActionView();
        menu_timkiem_tc.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        menu_timkiem_tc.setMaxWidth(Integer.MAX_VALUE);

        menu_timkiem_tc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), TimSanPham.class);
                intent.putExtra("TimKiem", query);
                getActivity().startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    void AnhXa(View view){
        dao = new DAO(getContext());
        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        tool3_TrangChuTab = view.findViewById(R.id.tool3_TrangChuTab);
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
