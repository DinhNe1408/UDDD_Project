package com.example.uddd_project;

import java.io.Serializable;

public class CTHoaDon implements Serializable {
    int IDHD, IDCTHD, IDSP, GiaSP, SLMSP;
    String TenSP;

    public CTHoaDon() {
    }

    public CTHoaDon(int IDHD, int IDSP, String tenSP, int giaSP, int SLMSP) {
        this.IDHD = IDHD;
        this.IDSP = IDSP;
        this.GiaSP = giaSP;
        this.SLMSP = SLMSP;
        this.TenSP = tenSP;
    }

    public int getIDHD() {
        return IDHD;
    }

    public void setIDHD(int IDHD) {
        this.IDHD = IDHD;
    }

    public int getIDCTHD() {
        return IDCTHD;
    }

    public void setIDCTHD(int IDCTHD) {
        this.IDCTHD = IDCTHD;
    }

    public int getIDSP() {
        return IDSP;
    }

    public void setIDSP(int IDSP) {
        this.IDSP = IDSP;
    }

    public int getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(int giaSP) {
        GiaSP = giaSP;
    }

    public int getSLMSP() {
        return SLMSP;
    }

    public void setSLMSP(int SLMSP) {
        this.SLMSP = SLMSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }
}
