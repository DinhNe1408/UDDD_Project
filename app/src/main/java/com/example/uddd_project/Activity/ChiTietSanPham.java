package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {

    ImageView imgV_HinhSP_Ctsp,imgV_Thich_Ctsp,imgV_QuayLai_Ctsp;
    Button btn_GiamSP_Ctsp, btn_TangSP_Ctsp, btn_ThemGH_Ctsp;
    TextView txtV_TenSP_Ctsp, txtV_GiaSP_Ctsp, txtV_SLMSP_Ctsp, txtV_TongTien_Ctsp, txtV_GiaGoc_Ctsp, txtV_NoiDung_ctsp;
    DAO dao;
    int SLMSP;
    String gia;
    boolean isThich;
    SanPhamDomain sanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        AnhXa();

        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 VND");



        btn_GiamSP_Ctsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SLMSP = Integer.parseInt(txtV_SLMSP_Ctsp.getText().toString());

                if( SLMSP >= 2){
                    SLMSP -= 1;
                    gia = decimalFormat.format(SLMSP * sanPham.getGiaSP());
                    txtV_TongTien_Ctsp.setText(gia);
                    txtV_SLMSP_Ctsp.setText(String.valueOf(SLMSP));

                } else {
                    Toast.makeText(ChiTietSanPham.this,"Số lượng sản phẩm không được bé hơn 1",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_TangSP_Ctsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SLMSP = Integer.parseInt(txtV_SLMSP_Ctsp.getText().toString());
                if( SLMSP < sanPham.getSLSP() && SLMSP < sanPham.getSLMTD()){
                    SLMSP += 1;
                    gia = decimalFormat.format(SLMSP * sanPham.getGiaSP());
                    txtV_TongTien_Ctsp.setText(gia);
                    txtV_SLMSP_Ctsp.setText(String.valueOf(SLMSP));
                } else {
                    Toast.makeText(ChiTietSanPham.this,"Số lượng sản phẩm được mua tối đa",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_ThemGH_Ctsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChu.taikhoan.getIDTK() != -1){
                    SLMSP = Integer.parseInt(txtV_SLMSP_Ctsp.getText().toString());
                    dao.CapNhatSPGH(TrangChu.taikhoan.getIDTK(), sanPham.getIDSP(), SLMSP);
                    if (dao.isSPGH(TrangChu.taikhoan.getIDTK(),sanPham.getIDSP()) == false){
                        Toast.makeText(ChiTietSanPham.this, " Sản phẩm đã được thêm vào giỏ hàng ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ChiTietSanPham.this, " Thất bại khi thêm sản phẩm vào giỏ hàng ", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    DenDangNhap();
                }
            }
        });

        imgV_Thich_Ctsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChu.taikhoan.getIDTK() != -1){
                    if(isThich){
                        dao.XoaThich(TrangChu.taikhoan.getIDTK(),sanPham.getIDSP());
                        imgV_Thich_Ctsp.setImageResource(R.drawable.ic_favorite_border_black_48dp);
                    } else {
                        dao.ThemThich(TrangChu.taikhoan.getIDTK(),sanPham.getIDSP());
                        imgV_Thich_Ctsp.setImageResource(R.drawable.ic_favorite_black_48dp);
                    }
                    isThich = !isThich;
                } else {
                    DenDangNhap();
                }
            }
        });

        imgV_QuayLai_Ctsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void DenDangNhap(){
        Intent intent = new Intent(ChiTietSanPham.this,DangNhap.class);
        intent.putExtra("isRoot",0);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        if(intent != null){
            int IDSP = intent.getIntExtra("IDSP_CTSP",-1);

            if (IDSP == -1){ return; }
            else { sanPham = dao.MotSPGH(TrangChu.taikhoan.getIDTK(),IDSP); }
        } else {return;}


        if(TrangChu.taikhoan.getIDTK() != -1){
            isThich = dao.isThich(TrangChu.taikhoan.getIDTK(),sanPham.getIDSP());
            if(isThich){
                imgV_Thich_Ctsp.setImageResource(R.drawable.ic_favorite_black_48dp);
            } else {
                imgV_Thich_Ctsp.setImageResource(R.drawable.ic_favorite_border_black_48dp);
            }
        }

        if(sanPham.getGiaGocSP() == 0){
            txtV_GiaGoc_Ctsp.setVisibility(View.INVISIBLE);
        }

        byte[] hinhSP = sanPham.getHinhSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhSP,0,hinhSP.length);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0 VND");
        gia = decimalFormat.format(sanPham.getGiaSP());

        imgV_HinhSP_Ctsp.setImageBitmap(bitmap);
        txtV_TenSP_Ctsp.setText(sanPham.getTenSP());
        txtV_GiaSP_Ctsp.setText(gia);
        txtV_GiaGoc_Ctsp.setText(decimalFormat.format(sanPham.getGiaGocSP()));
        txtV_GiaGoc_Ctsp.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        txtV_NoiDung_ctsp.setText(sanPham.getNoiDung());
        if(sanPham.getSLMSP() != 0){
            btn_ThemGH_Ctsp.setText("Cập nhật sản phẩm");
            txtV_SLMSP_Ctsp.setText(String.valueOf(sanPham.getSLMSP()));
        } else {
            btn_ThemGH_Ctsp.setText("Thêm vào giỏ");
            txtV_SLMSP_Ctsp.setText("1");
        }

        if(sanPham.getTongTien() != 0 ){
            txtV_TongTien_Ctsp.setText(decimalFormat.format(sanPham.getTongTien()));
        } else {
            txtV_TongTien_Ctsp.setText(gia);
        }
    }

    void AnhXa(){
        dao = new DAO(ChiTietSanPham.this);

        imgV_HinhSP_Ctsp = findViewById(R.id.imgV_HinhSP_Ctsp);

        imgV_Thich_Ctsp = findViewById(R.id.imgV_Thich_Ctsp);
        imgV_QuayLai_Ctsp = findViewById(R.id.imgV_QuayLai_Ctsp);
        btn_GiamSP_Ctsp = findViewById(R.id.btn_GiamSP_Ctsp);
        btn_TangSP_Ctsp = findViewById(R.id.btn_TangSP_Ctsp);
        btn_ThemGH_Ctsp = findViewById(R.id.btn_ThemGH_Ctsp);

        txtV_NoiDung_ctsp = findViewById(R.id.txtV_NoiDung_ctsp);
        txtV_GiaGoc_Ctsp = findViewById(R.id.txtV_GiaGocSP_Ctsp);
        txtV_TenSP_Ctsp = findViewById(R.id.txtV_TenSP_Ctsp);
        txtV_GiaSP_Ctsp = findViewById(R.id.txtV_GiaSP_Ctsp);
        txtV_SLMSP_Ctsp = findViewById(R.id.txtV_SLMSP_Ctsp);
        txtV_TongTien_Ctsp = findViewById(R.id.txtV_TongTien_Ctsp);
    }
}