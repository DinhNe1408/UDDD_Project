package com.example.uddd_project;

import java.io.Serializable;

public class SanPhamDomain  implements Serializable {
    private String ten;
    private int hinh;
    private Double gia;
    private int mathe;

    public SanPhamDomain(String ten, int hinh, Double gia) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
    }

    public SanPhamDomain(String ten, int hinh, Double gia, int mathe) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.mathe = mathe;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public int getMathe() {
        return mathe;
    }

    public void setMathe(int mathe) {
        this.mathe = mathe;
    }
}
