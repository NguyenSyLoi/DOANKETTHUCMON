/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Date;
/**
 *
 * @author PC-31
 */
public class DuAn {
    private int id;
    private String tenDuAn;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String moTa;
    public DuAn() {}

    public DuAn(int id, String tenDuAn, Date ngayBatDau, Date ngayKetThuc, String moTa) {
        this.id = id;
        this.tenDuAn = tenDuAn;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.moTa = moTa;
        
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenDuAn() { return tenDuAn; }
    public void setTenDuAn(String tenDuAn) { this.tenDuAn = tenDuAn; }
    public Date getNgayBatDau() { return ngayBatDau; }
    public void setNgayBatDau(Date ngayBatDau) { this.ngayBatDau = ngayBatDau; }
    public Date getNgayKetThuc() { return ngayKetThuc; }
    public void setNgayKetThuc(Date ngayKetThuc) { this.ngayKetThuc = ngayKetThuc; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}
