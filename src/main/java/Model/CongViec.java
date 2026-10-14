package Model;

import java.sql.Date;

/**
 * @author PC-31
 */
public class CongViec {

    private int id;
    private String tenCongViec;
    private String moTa;
    private Date hanHoanThanh;
    private String trangThai;
    private String mucDoUuTien;
    private int duAnId;

    // Thông tin hiển thị
    private String tenDuAn;

    public CongViec() {
    }

    public CongViec(int id, String tenCongViec, String moTa, Date hanHoanThanh, String trangThai, String mucDoUuTien, int duAnId) {
        this.id = id;
        this.tenCongViec = tenCongViec;
        this.moTa = moTa;
        this.hanHoanThanh = hanHoanThanh;
        this.trangThai = trangThai;
        this.mucDoUuTien = mucDoUuTien;
        this.duAnId = duAnId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getHanHoanThanh() {
        return hanHoanThanh;
    }

    public void setHanHoanThanh(Date hanHoanThanh) {
        this.hanHoanThanh = hanHoanThanh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMucDoUuTien() {
        return mucDoUuTien;
    }

    public void setMucDoUuTien(String mucDoUuTien) {
        this.mucDoUuTien = mucDoUuTien;
    }

    public int getDuAnId() {
        return duAnId;
    }

    public void setDuAnId(int duAnId) {
        this.duAnId = duAnId;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }  

   
}