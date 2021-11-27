package com.example.uddd_project.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uddd_project.Activity.DangNhap;
import com.example.uddd_project.Activity.DonHang;
import com.example.uddd_project.Activity.TrangChu;
import com.example.uddd_project.Adapter.GioHangAdapter;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangTab extends Fragment {

    LinearLayout layout;
    Button btn_ThanhToan_Gh;
    ImageView imgV_TB_Gh,imgV_XoaHetGH_gh;
    TextView txtV_TB_Gh,txtV_TongTien_Gh,txtV_TBphu_Gh,txtV_TongTien2_Gh;
    View view;
    DAO dao;
    boolean isClickable;
    RecyclerView recV_GioHang_Gh;
    public static GioHangAdapter gioHangAdapter;
    List<SanPhamDomain> listGH;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle saveInstanceState){
        view = inflater.inflate(R.layout.tab_gio_hang, container, false);

        AnhXa();

        imgV_TB_Gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClickable == false){
                    Intent intent = new Intent(view.getContext(), DangNhap.class);
                    startActivity(intent);
                }
            }
        });

        imgV_XoaHetGH_gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thông báo")
                        .setMessage("Bạn có chắc muốn xóa hết tất cả sản phẩm ra khỏi giỏ hàng không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.CapNhatSPGH(TrangChu.taikhoan.getIDTK(),
                                        1, -1);

                                GioHangAdapter.spGioHangDomains.clear();
                                gioHangAdapter.notifyDataSetChanged();

                                Intent intent = new Intent(getContext(),TrangChu.class);
                                intent.putExtra("posTab",1);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });

        btn_ThanhToan_Gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
                Intent intent = new Intent(getContext(), DonHang.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();


        if(TrangChu.taikhoan.getIDTK() == -1){
            isClickable = false;
            txtV_TB_Gh.setText("Bạn chưa đăng nhập.");
            txtV_TBphu_Gh.setText("Bấm vào biểu tượng để đăng nhập");
            HienThi(View.VISIBLE,View.INVISIBLE);
            imgV_XoaHetGH_gh.setVisibility(View.INVISIBLE);
            recV_GioHang_Gh(new ArrayList<>());
        }
        else {
            listGH = dao.DuLieuGH(TrangChu.taikhoan.getIDTK());
            if (listGH.size() != 0){
                recV_GioHang_Gh(listGH);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 đ");
                String gia = decimalFormat.format(TongTien(listGH));
                txtV_TongTien2_Gh.setText(gia);
                imgV_XoaHetGH_gh.setVisibility(View.VISIBLE);
                HienThi(View.INVISIBLE,View.VISIBLE);
            }else {
                isClickable = true;
                txtV_TB_Gh.setText("Bạn chưa có gì trong giỏ hàng.");
                txtV_TBphu_Gh.setText("");
                imgV_XoaHetGH_gh.setVisibility(View.INVISIBLE);
                HienThi(View.VISIBLE,View.INVISIBLE);
            }
        }
    }
    private void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.don_hang,null);
        final EditText editT_HoTen_dh = view.findViewById(R.id.editT_HoTen_dh);
        final EditText editT_SDT_dh = view.findViewById(R.id.editT_SDT_dh);
        final EditText editT_DiaChi_dh = view.findViewById(R.id.editT_DiaChi_dh);

        builder.setView(view);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int idhd = dao.LayIDHD();
                if (idhd == -1){

                }
            }
        }).setNegativeButton("Hủy", null);
        builder.show();
    }
    
    private int TongTien(List<SanPhamDomain> sanPhamDomain){
        int Tong = 0;
        for (int i = 0; i< sanPhamDomain.size(); i++){
            Tong += sanPhamDomain.get(i).getTongTien();
        }
        return Tong;
    }

    private void recV_GioHang_Gh(List<SanPhamDomain> sanPhamDomain) {
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recV_GioHang_Gh.setLayoutManager(linearLayout);

        gioHangAdapter = new GioHangAdapter(getContext(), sanPhamDomain);
        recV_GioHang_Gh.setAdapter(gioHangAdapter);
    }

    void HienThi(int Vis1, int Vis2){
        txtV_TB_Gh.setVisibility(Vis1);
        imgV_TB_Gh.setVisibility(Vis1);
        txtV_TBphu_Gh.setVisibility(Vis1);
        layout.setVisibility(Vis1);

        txtV_TongTien2_Gh.setVisibility(Vis2);
        txtV_TongTien_Gh.setVisibility(Vis2);
        btn_ThanhToan_Gh.setVisibility(Vis2);
    }

    void AnhXa(){
        dao = new DAO(view.getContext());
        listGH = new ArrayList<>();
        recV_GioHang_Gh = view.findViewById(R.id.recV_GioHang_Gh);
        layout = view.findViewById(R.id.layout_TB_Gh);
        btn_ThanhToan_Gh = view.findViewById(R.id.btn_ThanhToan_Gh);
        imgV_XoaHetGH_gh = view.findViewById(R.id.imgV_XoaHetGH_gh);
        imgV_TB_Gh = view.findViewById(R.id.imgV_TB_Gh);
        txtV_TB_Gh = view.findViewById(R.id.txtV_TB_Gh);
        txtV_TBphu_Gh = view.findViewById(R.id.txtV_TBphu_Gh);
        txtV_TongTien_Gh = view.findViewById(R.id.txtV_TongTien_Gh);
        txtV_TongTien2_Gh = view.findViewById(R.id.txtV_TongTien2_Gh);
    }

}
