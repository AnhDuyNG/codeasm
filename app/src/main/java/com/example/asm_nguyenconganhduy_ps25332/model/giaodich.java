package com.example.asm_nguyenconganhduy_ps25332.model;

import java.util.Date;

public class giaodich {
    private int maGD;
    private String tieude;
    private Date ngay;
    private float tien;
    private String mo_Ta;
    private int maLoai;

    public giaodich(int maGD, String tieude, Date ngay, float tien, String mo_Ta, int maLoai) {
        this.maGD = maGD;
        this.tieude = tieude;
        this.ngay = ngay;
        this.tien = tien;
        this.mo_Ta = mo_Ta;
        this.maLoai = maLoai;
    }
    public giaodich(String tieude, Date ngay, float tien, String mo_Ta,int maLoai) {
        this.tieude = tieude;
        this.ngay = ngay;
        this.tien = tien;
        this.mo_Ta = mo_Ta;
        this.maLoai = maLoai;
    }

    public int getMaGD() {
        return maGD;
    }

    public void setMaGD(int maGD) {
        this.maGD = maGD;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public float getTien() {
        return tien;
    }

    public void setTien(float tien) {
        this.tien = tien;
    }

    public String getMo_Ta() {
        return mo_Ta;
    }

    public void setMo_Ta(String mo_Ta) {
        this.mo_Ta = mo_Ta;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
