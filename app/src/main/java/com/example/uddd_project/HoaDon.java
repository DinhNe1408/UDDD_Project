package com.example.uddd_project;

import java.io.Serializable;
import java.util.ArrayList;

public class HoaDon implements Serializable {
    int IDHD;
    String TenNN, SDTNN, DiaChiNN,TGM, TGGH, TTTT, TTHD, GhiChu;
    int TongTien;

    ArrayList<CTHoaDon> listCTHD;

    public HoaDon() {
    }

    public HoaDon(int IDHD, String tenNN, String SDTNN, String diaChiNN, String TGM, String TGGH, int tongTien, String TTTT, String TTHD, String ghiChu) {
        this.IDHD = IDHD;
        this.TenNN = tenNN;
        this.SDTNN = SDTNN;
        this.DiaChiNN = diaChiNN;
        this.TGM = TGM;
        this.TGGH = TGGH;
        this.TTTT = TTTT;
        this.TTHD = TTHD;
        this.GhiChu = ghiChu;
        this.TongTien = tongTien;
    }

    public ArrayList<CTHoaDon> getListCTHD() {
        return listCTHD;
    }

    public void setListCTHD(ArrayList<CTHoaDon> listCTHD) {
        this.listCTHD = listCTHD;
    }

    public int getIDHD() {
        return IDHD;
    }

    public void setIDHD(int IDHD) {
        this.IDHD = IDHD;
    }

    public String getTenNN() {
        return TenNN;
    }

    public void setTenNN(String tenNN) {
        TenNN = tenNN;
    }

    public String getSDTNN() {
        return SDTNN;
    }

    public void setSDTNN(String SDTNN) {
        this.SDTNN = SDTNN;
    }

    public String getDiaChiNN() {
        return DiaChiNN;
    }

    public void setDiaChiNN(String diaChiNN) {
        DiaChiNN = diaChiNN;
    }

    public String getTGM() {
        return TGM;
    }

    public void setTGM(String TGM) {
        this.TGM = TGM;
    }

    public String getTGGH() {
        return TGGH;
    }

    public void setTGGH(String TGGH) {
        this.TGGH = TGGH;
    }

    public String getTTTT() {
        return TTTT;
    }

    public void setTTTT(String TTTT) {
        this.TTTT = TTTT;
    }

    public String getTTHD() {
        return TTHD;
    }

    public void setTTHD(String TTHD) {
        this.TTHD = TTHD;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }
}
