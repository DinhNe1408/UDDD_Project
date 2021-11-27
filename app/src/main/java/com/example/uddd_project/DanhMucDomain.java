package com.example.uddd_project;

import java.io.Serializable;

public class DanhMucDomain implements Serializable {
    private int IDDM;
    private String TenDM;
    private byte[] HinhDM;

    public DanhMucDomain(int IDDM, String tenDM) {
        this.IDDM = IDDM;
        TenDM = tenDM;
    }

    public DanhMucDomain(int IDDM, String tenDM, byte[] hinhDM) {
        this.IDDM = IDDM;
        TenDM = tenDM;
        HinhDM = hinhDM;
    }

    public int getIDDM() {
        return IDDM;
    }

    public void setIDDM(int IDDM) {
        this.IDDM = IDDM;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String tenDM) {
        TenDM = tenDM;
    }

    public byte[] getHinhDM() {
        return HinhDM;
    }

    public void setHinhDM(byte[] hinhDM) {
        HinhDM = hinhDM;
    }
}
