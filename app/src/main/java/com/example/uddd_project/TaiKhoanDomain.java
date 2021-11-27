package com.example.uddd_project;

import java.io.Serializable;

public class TaiKhoanDomain implements Serializable {
    private int IDTK;
    private String HoTen, SDT, MatKhau, Quyen, DiaChi;

    public TaiKhoanDomain(int IDTK, String hoTen, String SDT, String matKhau,String DiaChi, String quyen) {
        this.IDTK = IDTK;
        this.HoTen = hoTen;
        this.SDT = SDT;
        this.MatKhau = matKhau;
        this.DiaChi = DiaChi;
        this.Quyen = quyen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public TaiKhoanDomain() {
        this.IDTK = -1;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getQuyen() { return Quyen; }

    public void setQuyen(String quyen) { Quyen = quyen; }
}
