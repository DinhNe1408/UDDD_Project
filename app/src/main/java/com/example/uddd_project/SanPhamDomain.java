package com.example.uddd_project;

import java.io.Serializable;

public class SanPhamDomain  implements Serializable {
    private int IDSP, IDDM;
    private String TenSP,NoiDung;
    private byte[] HinhSP;
    private int GiaSP, SLSP, GiaGocSP,SLMTD, SLMSP, TongTien;

    public SanPhamDomain() {
    }

    public SanPhamDomain(int IDSP,  String tenSP, byte[] hinhSP, int giaSP,int giaGocSP , int slSP,int sLMTD,String NoiDung, int IDDM) {
        this.IDSP = IDSP;
        this.TenSP = tenSP;
        this.HinhSP = hinhSP;
        this.GiaSP = giaSP;
        this.GiaGocSP = giaGocSP;
        this.SLSP = slSP;
        this.SLMTD = sLMTD;
        this.IDDM = IDDM;
        this.NoiDung = NoiDung;
    }

    public SanPhamDomain(int IDSP, String tenSP, byte[] hinhSP,int giaSP,int giaGocSP , int SLSP,int SLMTD,String NoiDung, int IDDM , int SLMSP, int tongTien){
        this.IDSP = IDSP;
        this.TenSP = tenSP;
        this.HinhSP = hinhSP;
        this.GiaSP = giaSP;
        this.GiaGocSP = giaGocSP;
        this.SLSP = SLSP;
        this.SLMTD = SLMTD;
        this.IDDM = IDDM;
        this.SLMSP = SLMSP;
        this.TongTien = tongTien;
        this.NoiDung = NoiDung;
    }

    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public int getIDDM() {
        return IDDM;
    }

    public void setIDDM(int IDDM) {
        this.IDDM = IDDM;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public byte[] getHinhSP() {
        return HinhSP;
    }

    public void setHinhSP(byte[] hinhSP) {
        HinhSP = hinhSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getSLSP() {
        return SLSP;
    }

    public void setSLSP(int SLSP) {
        this.SLSP = SLSP;
    }

    public int getGiaGocSP() {
        return GiaGocSP;
    }

    public void setGiaGocSP(int giaGocSP) {
        GiaGocSP = giaGocSP;
    }

    public int getSLMTD() {
        return SLMTD;
    }

    public void setSLMTD(int SLMTD) {
        this.SLMTD = SLMTD;
    }

    public int getSLMSP() {
        return SLMSP;
    }

    public void setSLMSP(int SLMSP) {
        this.SLMSP = SLMSP;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
