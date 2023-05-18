package com.example.testapp;

public class SinhVien {
    String maSV, tenSV, NgaySinh;
    Boolean gioiTinh, chon;

    public SinhVien(){

    }

    public SinhVien(String maSV, String tenSV, String ngaySinh, Boolean gioiTinh) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        NgaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        chon=false;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Boolean getChon() {
        return chon;
    }

    public void setChon(Boolean chon) {
        this.chon = chon;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSV='" + maSV + '\'' +
                ", tenSV='" + tenSV + '\'' +
                ", NgaySinh='" + NgaySinh + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", chon=" + chon +
                '}';
    }


}
