package com.example.uddd_project.DAO_DTO;

import android.content.Context;
import android.database.Cursor;

import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.Database.Db;
import com.example.uddd_project.SPGioHangDomain;
import com.example.uddd_project.SanPhamDomain;
import com.example.uddd_project.TaiKhoanDomain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {
    Context icontext;
    Db db;

    public DAO(Context context) {
        db = new Db(context);
        icontext = context;
    }

    //region Sản phẩm
    public ArrayList<SanPhamDomain> LayTop10 (int IDMD) {
        ArrayList<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM " + db.tb_SanPham + " WHERE IDDM =" + IDMD +" LIMIT 10");
        while (tro.moveToNext()){
            list.add(new SanPhamDomain(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    tro.getInt(3),
                    tro.getInt(4),
                    tro.getInt(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8)
            ));
        }
        return list;
    }

    public ArrayList<SanPhamDomain> LayBanChay () {
        ArrayList<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM " + db.tb_SanPham + " LIMIT 10");
        while (tro.moveToNext()){
            list.add(new SanPhamDomain(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    tro.getInt(3),
                    tro.getInt(4),
                    tro.getInt(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8)
            ));
        }
        return list;
    }

    public ArrayList<SanPhamDomain> ListSP(int IDDM){
        ArrayList<SanPhamDomain> list = new ArrayList<>();
        String truyvan = "";

//        if(IDDM == -1) {
//            truyvan += "SELECT * FROM SANPHAM";
//        } else {
//            truyvan = truyvan + "SELECT * FROM SANPHAM WHERE IDDM = " + IDDM;
//        }
        Cursor tro;
        if(IDDM == -1){
            tro = db.Get("SELECT * FROM SANPHAM ");
        }else {
            tro = db.Get("SELECT * FROM SANPHAM WHERE IDDM = " + IDDM);
        }

        while (tro.moveToNext()){
            list.add(new SanPhamDomain(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    tro.getInt(3),
                    tro.getInt(4),
                    tro.getInt(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8)
            ));
        }
        return list;
    }

    //endregion

    //region Danh mục

    public ArrayList<DanhMucDomain> LayDanhMuc () {
        ArrayList<DanhMucDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM " + db.tb_DanhMuc);
        while (tro.moveToNext()){
            list.add(new DanhMucDomain(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2)
            ));
        }
        return list;
    }
    //endregion

    public SanPhamDomain MotSPGH(int IDTK, int IDSP){
        Cursor tro;

        if(IDTK == -1 || isSPGH(IDTK,IDSP)){
            tro = db.Get("SELECT * FROM " + db.tb_SanPham + " WHERE " + db.tb_SanPham_IDSP + " = " + IDSP);
            while (tro.moveToNext()){

                return new SanPhamDomain(tro.getInt(0),
                        tro.getString(1),
                        tro.getBlob(2),
                        tro.getInt(3),
                        tro.getInt(4),
                        tro.getInt(5),
                        tro.getInt(6),
                        tro.getString(7),
                        tro.getInt(8)
                );
            }

        } else {
            tro = db.Get("SELECT * FROM SANPHAM A, GIOHANG B WHERE B.IDTK = " + IDTK + " AND B.IDSP = "
                    + IDSP + " AND A.IDSP = B.IDSP");
            while (tro.moveToNext()){

                return new SanPhamDomain(tro.getInt(0),
                        tro.getString(1),
                        tro.getBlob(2),
                        tro.getInt(3),
                        tro.getInt(4),
                        tro.getInt(5),
                        tro.getInt(6),
                        tro.getString(7),
                        tro.getInt(8),
                        tro.getInt(10),
                        tro.getInt(3)*tro.getInt(10)
                );
            }
        }
        return null;
    }


    //region Giỏ hàng
    public boolean isSPGH(int IDTK, int IDSP){
        // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng chưa
        Cursor tro;
        tro = db.Get("SELECT * FROM GIOHANG WHERE IDTK = " + IDTK + " AND IDSP = " + IDSP);
        while (tro.moveToNext()){
            return false;
        }
        return true;
    }

    public void CapNhatSPGH(int IDTK, int IDSP, int SLSP){
        if(SLSP == -1){
            db.Query("DELETE FROM GIOHANG WHERE IDTK = " + IDTK );
        } else if ( SLSP == 0 ){
            db.Query("DELETE FROM GIOHANG WHERE IDTK = " + IDTK + " AND IDSP = " + IDSP);
        } else if (isSPGH(IDTK, IDSP)){
            db.Query("INSERT INTO " + db.tb_GioHang + " ( " + db.tb_GioHang_IDTK + " , " +
                    db.tb_GioHang_IDSP + " , " + db.tb_GioHang_SLSP + " ) VALUES ( " + IDTK +
                    " , " + IDSP + " , " + SLSP + " ) " );
        } else {
            db.Query("UPDATE GIOHANG SET SLSP = " + SLSP + " WHERE IDTK = " + IDTK + " AND IDSP = " + IDSP);
        }
    }

    public List<SanPhamDomain> DuLieuGH(int IDTK){
        List<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM " + db.tb_SanPham + " A, " +db.tb_GioHang + " B WHERE " +
                db.tb_GioHang_IDTK + " = " + IDTK  + " AND A." + db.tb_SanPham_IDSP + " = B." +
                db.tb_GioHang_IDSP);
        while (tro.moveToNext()){
            list.add( new SanPhamDomain(tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    tro.getInt(3),
                    tro.getInt(4),
                    tro.getInt(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8),
                    tro.getInt(10),
                    tro.getInt(3)*tro.getInt(11)
                    ));
        }
        return list;
    }
    //endregion

    // region Tài khoản
    public void DoiMatKhau(int IDTK, String MatKhauMoi) {
        db.Query("UPDATE TAIKHOAN SET MATKHAU = '" + MatKhauMoi + "' WHERE IDTK =" + IDTK);
    }

    public boolean isTK( String SDT){
        // Kiểm tra tài khoản đã tồn tại chưa

        Cursor tro = db.Get("SELECT * FROM " + db.tb_TaiKhoan + " WHERE " + db.tb_TaiKhoan_SDT +
                " = " + SDT);
        while (tro.moveToNext()){
            return false;
        }
        return true;
    }

    public void TaoTK(String SDT,String MatKhau,String HoTen){
        db.Query("INSERT INTO " + db.tb_TaiKhoan + "( " + db.tb_TaiKhoan_SDT + " , " +
                db.tb_TaiKhoan_MK + " , " + db.tb_TaiKhoan_HoTen + " ) VALUES( '" + SDT + "' , '"
                + MatKhau + "' , '" + HoTen + "' )");
    }

    public TaiKhoanDomain DuLieuTK (String SDT, String MatKhau) {
        Cursor tro = db.Get("SELECT * FROM " + db.tb_TaiKhoan + " WHERE " + db.tb_TaiKhoan_SDT +
                " = '" + SDT + "' AND " + db.tb_TaiKhoan_MK + " = '" + MatKhau + "'");
        while (tro.moveToNext()){
            TaiKhoanDomain taikhoan  =  new TaiKhoanDomain(
                   tro.getInt(0),
                   tro.getString(1),
                   tro.getString(2),
                   tro.getString(3),
                    tro.getString(4),
                    tro.getString(5)
           );

            return taikhoan;
        }
        return null;
    }

    public void CapNhatTKNguoiDung(int IDTK, String HoTen, String DiaChi){
        db.Query("UPDATE TAIKHOAN SET HoTen = '" + HoTen + "', DiaChi = '"
                + DiaChi + "' WHERE IDTK = " +IDTK );
    }

    //endregion


    // region Thích
    public boolean isThich(int idtk, int idsp) {
        Cursor tro =  db.Get("SELECT * FROM YEUTHICH WHERE IDTK = " + idtk + " AND IDSP = " + idsp);
        while (tro.moveToNext()){
            return true;
        }
        return false;
    }

    public void XoaThich(int idtk, int idsp) {
        db.Query("DELETE FROM YEUTHICH WHERE IDTK = " + idtk + " AND IDSP = " + idsp);
    }

    public void ThemThich(int idtk, int idsp) {
        db.Query("INSERT INTO YEUTHICH(IDTK,IDSP,THOIGIAN) VALUES (" + idtk + ", " + idsp
                + ",'" + Calendar.getInstance().getTime() + "')" );
    }

    public ArrayList<SanPhamDomain> LayYeuThich(int idtk) {
        ArrayList<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM SANPHAM A,YEUTHICH B WHERE A.IDSP = B.IDSP AND B.IDTK = "
                + idtk + " ORDER BY B.THOIGIAN");
        while (tro.moveToNext()){
            list.add(new SanPhamDomain(
                    tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    tro.getInt(3),
                    tro.getInt(4),
                    tro.getInt(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8)
            ));
        }
        return list;
    }

    // endregion

    // region Hóa Đơn
    public int LayIDHD(){
        Cursor tro = db.Get("SELECT * FROM HOADON ORDER BY IDSP DESC");
        while (tro.moveToNext()){
            return tro.getInt(0);
        }
        return -1;
    }

    // endregion
}
