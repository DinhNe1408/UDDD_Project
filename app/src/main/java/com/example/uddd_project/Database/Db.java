package com.example.uddd_project.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {

    //Bảng sản phẩm
    public static String tb_SanPham = "SanPham";
    public static String tb_SanPham_IDSP = "IDSP";
    public static String tb_SanPham_TenSP = "TenSP";
    public static String tb_SanPham_HinhSP = "HinhSP";
    public static String tb_SanPham_GiaSP = "GiaSP";
    public static String tb_SanPham_MaDM = "IDDM";

    //Bảng Danh mục
    public static String tb_DanhMuc ="DanhMuc";
    public static String tb_DanhMuc_IDDM = "IDDM";
    public static String tb_DanhMuc_TenDM = "TenDM";
    public static String tb_DanhMuc_HinhDM = "HinhDM";

    // Bảng Tài khoản
    public static String tb_TaiKhoan = "TaiKhoan";
    public static String tb_TaiKhoan_IDTK = "IDTK";
    public static String tb_TaiKhoan_HoTen = "HoTen";
    public static String tb_TaiKhoan_SDT = "SDT";
    public static String tb_TaiKhoan_MK = "MatKhau";
    public static String tb_TaiKhoan_Quyen = "Quyen";

    //Bảng Hóa đơn
    public static String tb_HoaDon = "HoaDon";
    public static String tb_HoaDon_IDHD = "IDHD";
    public static String tb_HoaDon_IDTK = "IDTK";
    public static String tb_HoaDon_TGM = "TGM";
    public static String tb_HoaDon_TongTien = "TongTien";
    public static String tb_HoaDon_HTTT = "HTTT";
    public static String tb_HoaDon_TTTT = "TTTT";
    public static String tb_HoaDon_TTHD = "TTHD";
    public static String tb_HoaDon_GhiChu = "GhiChu";

    //Bảng Chi tiết hóa đơn
    public static String tb_ChiTietHoaDon = "ChiTietHoaDon";
    public static String tb_ChiTietHoaDon_IDHD = "IDHD";
    public static String tb_ChiTietHoaDon_IDCTHD = "IDCTHD";
    public static String tb_ChiTietHoaDon_IDSP = "IDSP";
    public static String tb_ChiTietHoaDon_TenSP = "TenSP";
    public static String tb_ChiTietHoaDon_HinhSP = "HinhSP";
    public static String tb_ChiTietHoaDon_GiaSP = "GiaSP";
    public static String tb_ChiTietHoaDon_SLSP = "SLSP";

    //Bảng Giỏ hàng
    public static String tb_GioHang = "GioHang";
    public static String tb_GioHang_IDTK = "IDTK";
    public static String tb_GioHang_IDSP = "IDSP";
    public static String tb_GioHang_TenSP = "TenSP";
    public static String tb_GioHang_HinhSP = "HinhSP";
    public static String tb_GioHang_SLSP = "SLSP";
    public static String tb_GioHang_ThanhTien = "ThanhTien";


    public Db(@Nullable Context context) {
        super(context,"UDDD",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String sanpham = "CREATE TABLE " + tb_SanPham + " ( " + tb_SanPham_IDSP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + tb_SanPham_TenSP + " TEXT NOT NULL, " + tb_SanPham_HinhSP + " BLOB NOT NULL," + tb_SanPham_MaDM + " INTERGER )" ;
//
//        String danhmuc = "CREATE TABLE " + tb_DanhMuc + " ( " + tb_DanhMuc_IDDM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + tb_DanhMuc_TenDM +  " TEXT, " + tb_DanhMuc_HinhDM + " BLOB )";
//
//        String taikhoan = "CREATE TABLE " + tb_TaiKhoan + " ( " + tb_TaiKhoan_IDTK +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + tb_TaiKhoan_HoTen + " TEXT, " + tb_TaiKhoan_SDT + " INTEGER (10), " + tb_TaiKhoan_MK + " VARCHAR (20), "
//                + tb_TaiKhoan_Quyen +  " VARCHAR (20) DEFAULT 'USER' )";
//
//        String hoadon = "CREATE TABLE " +  tb_HoaDon + " ( " + tb_HoaDon_IDHD + " INTEGER, " + tb_HoaDon_IDTK + " INTEGER, "
//                + tb_HoaDon_TGM + " DATETIME, " + tb_HoaDon_TongTien + " INTEGER, " + tb_HoaDon_HTTT + " TEXT, " + tb_HoaDon_TTTT
//                + " TEXT, " + tb_HoaDon_TTHD + " TEXT, " + tb_HoaDon_GhiChu + " TEXT, PRIMARY KEY ( " + tb_HoaDon_IDHD + " ," + tb_HoaDon_IDTK + "))";
//
//        String chitiethoadon = "CREATE TABLE " + tb_ChiTietHoaDon + " ( " + tb_ChiTietHoaDon_IDHD + " INTEGER REFERENCES " + tb_HoaDon + " ( "
//                + tb_HoaDon_IDHD + " ), " + tb_ChiTietHoaDon_IDCTHD + " INTEGER, " + tb_ChiTietHoaDon_IDSP + " INTEGER, " + tb_ChiTietHoaDon_TenSP
//                + " TEXT, " + tb_ChiTietHoaDon_HinhSP + " BLOB, " + tb_ChiTietHoaDon_GiaSP + " INTEGER, " + tb_ChiTietHoaDon_SLSP
//                + " INTEGER CHECK ( " + tb_ChiTietHoaDon_SLSP + " > 0), PRIMARY KEY ( " + tb_ChiTietHoaDon_IDHD + " ," + tb_ChiTietHoaDon_IDCTHD + " ))";
//
//        String giohang = "CREATE TABLE " + tb_GioHang + " ( " + tb_GioHang_IDTK +  " INTEGER, " + tb_GioHang_IDSP + " INTEGER, "
//                + tb_GioHang_TenSP + " TEXT, " + tb_GioHang_HinhSP + " BLOB, " + tb_GioHang_SLSP + " INTEGER, " + tb_GioHang_ThanhTien + " INTEGER )";
//
//        sqLiteDatabase.execSQL(sanpham);
//        sqLiteDatabase.execSQL(danhmuc);
//        sqLiteDatabase.execSQL(taikhoan);
//        sqLiteDatabase.execSQL(hoadon);
//        sqLiteDatabase.execSQL(chitiethoadon);
//        sqLiteDatabase.execSQL(giohang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Query(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor Get(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public SQLiteDatabase WritableData (){
        return this.getWritableDatabase();
    }
}
