package com.example.uddd_project;

import java.io.Serializable;

public class SPGioHangDomain implements Serializable {
    String TenSP;
    int IDSP, SLMSP, GiaSP, GiaGoc, TongTien, SLSP;
    byte[] HinhSP;

    public SPGioHangDomain() { }

    public SPGioHangDomain(int IDSP, String tenSP, byte[] hinhSP,int giaSP, int SLSP, int giaGoc, int SLMSP, int tongTien) {
        this.TenSP = tenSP;
        this.IDSP = IDSP;
        this.SLMSP = SLMSP;
        this.GiaSP = giaSP;
        this.GiaGoc = giaGoc;
        this.TongTien = tongTien;
        this.SLSP = SLSP;
        this.HinhSP = hinhSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public int getSLMSP() {
        return SLMSP;
    }

    public void setSLMSP(int SLMSP) {
        this.SLMSP = SLMSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getGiaGoc() {
        return GiaGoc;
    }

    public void setGiaGoc(int giaGoc) {
        GiaGoc = giaGoc;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public int getSLSP() {
        return SLSP;
    }

    public void setSLSP(int SLSP) {
        this.SLSP = SLSP;
    }

    public byte[] getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(byte[] hinhSP) {
        HinhSP = hinhSP;
    }
}
