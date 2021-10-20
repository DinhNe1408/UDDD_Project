package com.example.uddd_project;

import java.io.Serializable;

public class SPGioHangDomain implements Serializable {
    String Tensp,Masp;

    int Solsp,Giamgia,Hinhsp;
    Double GiaGoc, Gia;

    public SPGioHangDomain(String masp, String tensp, int hinhsp, int solsp, int giamgia, Double giaGoc, Double gia) {
        Tensp = tensp;
        Hinhsp = hinhsp;
        Masp = masp;
        Solsp = solsp;
        Giamgia = giamgia;
        GiaGoc = giaGoc;
        Gia = gia;
    }

    public SPGioHangDomain(String masp, String tensp, int hinhsp , int solsp, Double gia) {
        Tensp = tensp;
        Hinhsp = hinhsp;
        Masp = masp;
        Solsp = solsp;
        Gia = gia;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public int getHinhsp() {
        return Hinhsp;
    }

    public void setHinhsp(int hinhsp) {
        Hinhsp = hinhsp;
    }

    public String getMasp() {
        return Masp;
    }

    public void setMasp(String masp) {
        Masp = masp;
    }

    public int getSolsp() {
        return Solsp;
    }

    public void setSolsp(int solsp) {
        Solsp = solsp;
    }

    public int getGiamgia() {
        return Giamgia;
    }

    public void setGiamgia(int giamgia) {
        Giamgia = giamgia;
    }

    public Double getGiaGoc() {
        return GiaGoc;
    }

    public void setGiaGoc(Double giaGoc) {
        GiaGoc = giaGoc;
    }

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double gia) {
        Gia = gia;
    }
}
