package com.example.uddd_project;

import java.io.Serializable;

public class DanhMucDomain implements Serializable {
    private String madm,tendm;
    private int hinh;

    public DanhMucDomain(String madm, String tendm, int hinh) {
        this.madm = madm;
        this.tendm = tendm;
        this.hinh = hinh;
    }

    public String getMadm() {
        return madm;
    }

    public void setMadm(String madm) {
        this.madm = madm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
