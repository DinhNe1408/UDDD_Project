package com.example.uddd_project;

import java.util.ArrayList;

public class TieuDeTCDomain {
    private String TieuDe;
    private ArrayList<SanPhamDomain> sPDomainAL;

    public TieuDeTCDomain(String tieuDe, ArrayList<SanPhamDomain> sPDomainAL) {
        TieuDe = tieuDe;
        this.sPDomainAL = sPDomainAL;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public ArrayList<SanPhamDomain> getsPDomainAL() {
        return sPDomainAL;
    }

    public void setsPDomainAL(ArrayList<SanPhamDomain> sPDomainAL) {
        this.sPDomainAL = sPDomainAL;
    }
}
