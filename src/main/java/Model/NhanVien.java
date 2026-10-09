/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class NhanVien {
    private int id;
    private String hoTen;
    private String chucVu;
    private String email;
    private String soDienThoai;

    public NhanVien() {
    }

    public NhanVien(int id, String hoTen, String chucVu, String email, String soDienThoai) {
        this.id = id;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public NhanVien(String hoTen, String chucVu, String email, String soDienThoai) {
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
