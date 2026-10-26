/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.sql.Date;

public class PhanCong {

    private int id;
    private int congViecId;
    private int nhanVienId;
    private Date ngayPhanCong;

    private String tenCongViec;
    private String tenNhanVien;

    public PhanCong() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCongViecId() {
        return congViecId;
    }

    public void setCongViecId(int congViecId) {
        this.congViecId = congViecId;
    }

    public int getNhanVienId() {
        return nhanVienId;
    }

    public void setNhanVienId(int nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public Date getNgayPhanCong() {
        return ngayPhanCong;
    }

    public void setNgayPhanCong(Date ngayPhanCong) {
        this.ngayPhanCong = ngayPhanCong;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
}