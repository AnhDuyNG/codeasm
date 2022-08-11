package com.example.asm_nguyenconganhduy_ps25332.model;

public class phanloai {
    private int maLoai;
    private String tenloai;
    private String trangthai;

    public phanloai(int maLoai, String tenloai, String trangthai) {
        this.maLoai = maLoai;
        this.tenloai = tenloai;
        this.trangthai = trangthai;
    }

    public phanloai(String tenloai, String trangthai) {
        this.tenloai = tenloai;
        this.trangthai = trangthai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
