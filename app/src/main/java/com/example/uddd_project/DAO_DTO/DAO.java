package com.example.uddd_project.DAO_DTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.uddd_project.CTHoaDon;
import com.example.uddd_project.DanhMucDomain;
import com.example.uddd_project.Database.Db;
import com.example.uddd_project.HoaDon;
import com.example.uddd_project.SanPhamDomain;
import com.example.uddd_project.TaiKhoanDomain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        Cursor tro = db.Get("SELECT * FROM SANPHAM WHERE IDDM =" + IDMD +" LIMIT 10");
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

    public void TaoSP(String TENSP, byte[] HINHSP,int GIASP, int GIAGOCSP,int SLSP, int SLMTD,String NOIDUNG,int IDDM){
        SQLiteDatabase database = db.WritableData();
        ContentValues cv = new  ContentValues();
        cv.put("TENSP", TENSP);
        cv.put("HINHSP", HINHSP);
        cv.put("GIASP", GIASP);
        cv.put("GIAGOCSP", GIAGOCSP);
        cv.put("SLSP",   SLSP);
        cv.put("SLMTD", SLMTD);
        cv.put("NOIDUNG", NOIDUNG);
        cv.put("IDDM", IDDM);
        Log.e("hmm",String.valueOf(SLMTD));
        database.insert( "SANPHAM", null, cv );
    }

    public void XoaSP(int IDSP){
        db.Query("DELETE FROM SANPHAM WHERE IDSP = " + IDSP);
    }

    public void CapNhatSP(int IDSP, String TENSP, byte[] HINHSP,int GIASP, int GIAGOCSP,int SLSP, int SLMTD,String NOIDUNG,int IDDM){
        SQLiteDatabase sqLiteDatabase = db.WritableData();
        ContentValues values = new ContentValues();
        values.put("TENSP", TENSP);
        values.put("GIASP", GIASP);
        values.put("GIAGOCSP", GIAGOCSP);
        values.put("SLSP", SLSP);
        values.put("SLMTD", SLMTD);
        values.put("NOIDUNG", NOIDUNG);
        values.put("IDDM", IDDM);

        sqLiteDatabase.update("SANPHAM",values,"IDSP =" + IDSP,null);


        String sql = "UPDATE SANPHAM SET HINHSP = ? WHERE IDSP="+ IDSP ;
        SQLiteDatabase database = db.WritableData();
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1,HINHSP);
        statement.executeInsert();
    }


    public ArrayList<SanPhamDomain> LayKhuyenMai () {
        ArrayList<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM SANPHAM WHERE GIAGOCSP IS NOT NULL ORDER BY TENSP LIMIT 10");
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

    public SanPhamDomain Lay1SP(int IDSP){
        Cursor tro = db.Get("SELECT * FROM SANPHAM WHERE IDSP = " + IDSP );
        while (tro.moveToNext()){
            return new SanPhamDomain(tro.getInt(0),
                    tro.getString(1),
                    tro.getBlob(2),
                    tro.getInt(3),
                    tro.getInt(4),
                    tro.getInt(5),
                    tro.getInt(6),
                    tro.getString(7),
                    tro.getInt(8));
        }
        return null;
    }

    public void XoaTK(int IDTK){
        db.Query("DELETE FROM TAIKHOAN WHERE IDTK = " + IDTK);
    }

    public ArrayList<SanPhamDomain> LayBanChay () {
        ArrayList<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM SANPHAM LIMIT 10");
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
            tro = db.Get("SELECT * FROM SANPHAM WHERE IDSP = " + IDSP);
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
                        tro.getInt(11),
                        tro.getInt(3)*tro.getInt(11)
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
            db.Query("INSERT INTO GIOHANG ( IDTK , IDSP, SLSP) VALUES ( " + IDTK +
                    " , " + IDSP + " , " + SLSP + " ) " );
        } else {
            db.Query("UPDATE GIOHANG SET SLSP = " + SLSP + " WHERE IDTK = " + IDTK + " AND IDSP = " + IDSP);
        }
    }

    public List<SanPhamDomain> DuLieuGH(int IDTK){
        List<SanPhamDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM SANPHAM A, GIOHANG B WHERE IDTK = " + IDTK  +
                " AND A.IDSP = B.IDSP");
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
                    tro.getInt(11),
                    tro.getInt(3)*tro.getInt(11)
                    ));
        }
        return list;
    }
    //endregion

    // region Tài khoản
    public ArrayList<TaiKhoanDomain> LayTatCaTK(){
        ArrayList<TaiKhoanDomain> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM TAIKHOAN ");
        while (tro.moveToNext()){
            list.add(new TaiKhoanDomain(tro.getInt(0),
                    tro.getString(1),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5)));
        }
        return list;
    }

    public void DoiMatKhau(int IDTK, String MatKhauMoi) {
        db.Query("UPDATE TAIKHOAN SET MATKHAU = '" + MatKhauMoi + "' WHERE IDTK =" + IDTK);
    }

    public boolean isTK( String SDT){
        // Kiểm tra tài khoản đã tồn tại chưa
        Cursor tro = db.Get("SELECT * FROM TAIKHOAN WHERE SDT = '" + SDT + "'");
        while (tro.moveToNext()){
            return false;
        }
        return true;
    }

    public void TaoTK(String SDT,String MatKhau,String HoTen, String DIACHI, String Quyen){
        db.Query("INSERT INTO TAIKHOAN ( SDT, MATKHAU, HOTEN, DIACHI, Quyen) VALUES('" + SDT + "','"
                + MatKhau + "','" + HoTen +"','" + DIACHI + "','" + Quyen + "')");
    }

    public void CapNhatQuyen(int IDTK,String Quyen){
        db.Query("UPDATE TAIKHOAN SET QUYEN = '"+ Quyen + "' WHERE IDTK = " + IDTK);
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
        Cursor tro = db.Get("SELECT * FROM HOADON ORDER BY IDHD DESC");
        while (tro.moveToNext()){
            return tro.getInt(0);
        }
        return -1;
    }

    public void ThemHD(int IDHD,int IDTK,String TenNN,String SDTNN,String DIACHINHAN,
                       Date TGGH, int TONGTIEN,String TTTT, String TTHD, String GhiChu) {


        DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();

        Log.e("Tag","INSERT INTO HOADON (IDHD,IDTK,TenNN,SDTNN,DIACHINHAN,TGM,TGGH,TONGTIEN,TTTT,TTHD,GhiChu) VALUES ("
                + IDHD + "," + IDTK + ",'" + TenNN + "','" + SDTNN + "','" + DIACHINHAN + "','"
                + df.format(today) + "','" + df.format(TGGH) + "'," + TONGTIEN + ",'" + TTTT
                + "','" + TTHD + "','" + GhiChu + "')");

        db.Query("INSERT INTO HOADON (IDHD,IDTK,TenNN,SDTNN,DIACHINHAN,TGM,TGGH,TONGTIEN,TTTT,TTHD,GhiChu) VALUES ("
                + IDHD + "," + IDTK + ",'" + TenNN + "','" + SDTNN + "','" + DIACHINHAN + "','"
                + df.format(today) + "','" + df.format(TGGH) + "'," + TONGTIEN + ",'" + TTTT
                + "','" + TTHD + "','" + GhiChu + "')");
    }

    public void ThemCTHD(int IDHD,int IDSP,String TenSP,int GiaSP,int SLMSP) {

        db.Query("INSERT INTO CHITIETHOADON (IDHD,IDSP,TenSP,GiaSP,SLSP) VALUES ("
                + IDHD + ","  + IDSP + ",'" + TenSP + "'," + GiaSP + "," + SLMSP + ")");

        db.Query("UPDATE SANPHAM SET SLSP = SLSP - " + SLMSP + " WHERE IDSP = " + IDSP );
    }


    public ArrayList<HoaDon> LayHoaDon(int IDTK){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM HOADON WHERE IDTK = " + IDTK + " ORDER BY TGM DESC");
        while (tro.moveToNext()){
            list.add(new HoaDon(
                    tro.getInt(0),
                    tro.getString(2),
                    tro.getString(3),
                    tro.getString(4),
                    tro.getString(5),
                    tro.getString(6),
                    tro.getInt(7),
                    tro.getString(8),
                    tro.getString(9),
                    tro.getString(10)
            ));
        }

        return list;
    }

    public ArrayList<CTHoaDon> LayCTHoaDon(int IDHD){
        ArrayList<CTHoaDon> list = new ArrayList<>();
        Cursor tro = db.Get("SELECT * FROM CHITIETHOADON WHERE IDHD = " + IDHD);
        while (tro.moveToNext()){
            list.add(new CTHoaDon(
                    tro.getInt(0),
                    tro.getInt(1),
                    tro.getString(2),
                    tro.getInt(3),
                    tro.getInt(4)
            ));
        }

        return list;
    }
    // endregion
}
