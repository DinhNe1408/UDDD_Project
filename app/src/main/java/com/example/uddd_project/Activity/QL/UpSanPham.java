package com.example.uddd_project.Activity.QL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.SanPhamDomain;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpSanPham extends AppCompatActivity {

    ImageView img_HinhSP_upsp;
    TextView editT_TenSP_upsp, editT_SLSP_upsp, editT_SLMTD_upsp;
    TextView editT_GiaSP_upsp, editT_GiaGocSP_upsp, editT_NoiDungSP_upsp;
    Button btn_XacNhan_upsp, btn_Huy_upsp, btn_Folder_upsp, btn_Camera_upsp;
    Toolbar tool3_QLSPUp;
    int type;
    boolean isEnable;
    DAO dao;
    SanPhamDomain sanpham;

    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_san_pham);

        AnhXa();

        setSupportActionBar(tool3_QLSPUp);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        tool3_QLSPUp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        int type = getIntent().getIntExtra("Type",0);

        if(type == 1){
            tool3_QLSPUp.setTitle("Thêm sản phẩm");
            btn_Huy_upsp.setText("Làm mới");
        } else if(type == 2){
            int IDSP = getIntent().getIntExtra("IDSP",-1);
            sanpham = dao.Lay1SP(IDSP);
            tool3_QLSPUp.setTitle("Sửa sản phẩm");
            btn_XacNhan_upsp.setText("Cập nhật");
            LoadText(sanpham.getTenSP(),String.valueOf(sanpham.getSLSP()),String.valueOf(sanpham.getSLMSP()),
                    String.valueOf(sanpham.getGiaSP()),String.valueOf(sanpham.getGiaGocSP()),sanpham.getNoiDung());
            Bitmap bitmap = BitmapFactory.decodeByteArray(sanpham.getHinhSP(),0,sanpham.getHinhSP().length);
            img_HinhSP_upsp.setImageBitmap(bitmap);
            isEnable = false;
            HienThi();
        }

        btn_XacNhan_upsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type == 1){
                    TaoSanPham();
                } else if(type == 2){
                    if (isEnable){
                        isEnable = !isEnable;
                        HienThi();

                        sanpham.setTenSP(editT_TenSP_upsp.getText().toString());
                        sanpham.setGiaSP( Integer.parseInt(editT_GiaSP_upsp.getText().toString()) );
                        if(editT_GiaGocSP_upsp.getText().length() > 0 ){
                            sanpham.setGiaGocSP( Integer.parseInt(editT_GiaSP_upsp.getText().toString()) );
                        }else {
                            sanpham.setGiaGocSP(0);
                        }
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) img_HinhSP_upsp.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                        byte[] hinhAnh = byteArray.toByteArray();

                        sanpham.setHinhSP(hinhAnh);
                        sanpham.setSLSP( Integer.parseInt(editT_SLSP_upsp.getText().toString()) );
                        sanpham.setSLMTD( Integer.parseInt(editT_SLMTD_upsp.getText().toString()) );
                        sanpham.setNoiDung( editT_NoiDungSP_upsp.getText().toString() );

                        CapNhatTaiKhoan();
                        Toast.makeText(UpSanPham.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        btn_XacNhan_upsp.setText("Cập nhật");
                    } else
                    {
                        isEnable = !isEnable;
                        HienThi();
                        btn_XacNhan_upsp.setText("Lưu");
                    }

                }

            }
        });

        btn_Huy_upsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1){
                    LoadText("","","","","","");
                } else {
                    isEnable = false;
                    HienThi();

                    LoadText(sanpham.getTenSP(),String.valueOf(sanpham.getSLSP()),String.valueOf(sanpham.getSLMTD()),
                            String.valueOf(sanpham.getGiaSP()),String.valueOf(sanpham.getGiaGocSP()),sanpham.getNoiDung());
                }
            }
        });


        btn_Camera_upsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UpSanPham.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );

            }
        });

        btn_Folder_upsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        UpSanPham.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE_CAMERA);
                }else
                {
                    Toast.makeText(UpSanPham.this," Ban khong cho phep mo camera", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }else
                {
                    Toast.makeText(UpSanPham.this," Ban khong cho phep mo folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_HinhSP_upsp.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_HinhSP_upsp.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void CapNhatTaiKhoan() {
        if(editT_TenSP_upsp.getText().length() > 0 && editT_SLSP_upsp.getText().length() > 0
                && editT_SLMTD_upsp.getText().length() > 0 && editT_GiaSP_upsp.getText().length() > 0
                && editT_NoiDungSP_upsp.getText().length() > 0){
            dao.CapNhatSP(sanpham.getIDSP(),sanpham.getTenSP(),sanpham.getHinhSP(),sanpham.getGiaSP(),sanpham.getGiaGocSP()
            ,sanpham.getSLSP(), sanpham.getSLMTD(), sanpham.getNoiDung(), sanpham.getIDDM());

            Toast.makeText(UpSanPham.this, "Cập nhận thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UpSanPham.this, "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }
    }

    private void TaoSanPham() {
        if(editT_TenSP_upsp.getText().length() > 0 && editT_SLSP_upsp.getText().length() > 0
                && editT_SLMTD_upsp.getText().length() > 0 && editT_GiaSP_upsp.getText().length() > 0
                && editT_NoiDungSP_upsp.getText().length() > 0){
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_HinhSP_upsp.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                int GiaGocSP = 0;
                if(editT_GiaGocSP_upsp.getText().length() > 0){
                    GiaGocSP = Integer.parseInt(editT_GiaGocSP_upsp.getText().toString());
                }
            Toast.makeText(UpSanPham.this, editT_SLMTD_upsp.getText().toString(), Toast.LENGTH_SHORT).show();

                dao.TaoSP(editT_TenSP_upsp.getText().toString(),hinhAnh,
                        Integer.parseInt(editT_GiaSP_upsp.getText().toString()),
                        GiaGocSP,Integer.parseInt(editT_SLSP_upsp.getText().toString()),
                        Integer.parseInt(editT_SLMTD_upsp.getText().toString()),
                        editT_NoiDungSP_upsp.getText().toString(),
                        Integer.parseInt(editT_SLSP_upsp.getText().toString()));

                Toast.makeText(UpSanPham.this, "Tạo sản phẩm thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UpSanPham.this, "Vui lòng điền đầy đủ các trường, có thể không điền giá trước khi giảm", Toast.LENGTH_SHORT).show();
        }
    }

    private void HienThi() {
        editT_TenSP_upsp.setEnabled(isEnable);
        editT_SLSP_upsp.setEnabled(isEnable);
        editT_SLMTD_upsp.setEnabled(isEnable);
        editT_GiaSP_upsp.setEnabled(isEnable);
        editT_GiaGocSP_upsp.setEnabled(isEnable);
        editT_NoiDungSP_upsp.setEnabled(isEnable);
    }

    private void LoadText(String TenSP,String SLSP,String SLMTD,String GiaSP,String GiaGocSP,String NoiDungSP){

        editT_TenSP_upsp.setText(TenSP);
        editT_SLSP_upsp.setText(SLSP);
        editT_SLMTD_upsp.setText(SLMTD);
        editT_GiaSP_upsp.setText(GiaSP);
        editT_GiaGocSP_upsp.setText(GiaGocSP);
        editT_NoiDungSP_upsp.setText(NoiDungSP);
    }

    private void AnhXa() {

        dao = new DAO(UpSanPham.this);
        btn_Folder_upsp = findViewById(R.id.btn_Folder_upsp);
        btn_Camera_upsp = findViewById(R.id.btn_Camera_upsp);
        tool3_QLSPUp = findViewById(R.id.tool3_QLSPUp);
        btn_Huy_upsp = findViewById(R.id.btn_Huy_upsp);
        btn_XacNhan_upsp = findViewById(R.id.btn_XacNhan_upsp);
        img_HinhSP_upsp = findViewById(R.id.img_HinhSP_upsp);
        editT_TenSP_upsp = findViewById(R.id.editT_TenSP_upsp);
        editT_SLSP_upsp = findViewById(R.id.editT_SLSP_upsp);
        editT_SLMTD_upsp = findViewById(R.id.editT_SLMTD_upsp);
        editT_GiaSP_upsp = findViewById(R.id.editT_GiaSP_upsp);
        editT_GiaGocSP_upsp = findViewById(R.id.editT_GiaGocSP_upsp);
        editT_NoiDungSP_upsp = findViewById(R.id.editT_NoiDungSP_upsp);
    }


}