/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import Model.NhanVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    // Lấy danh sách tất cả nhân viên
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new NhanVien(
                        rs.getInt("id"),
                        rs.getString("hoTen"),
                        rs.getString("chucVu"),
                        rs.getString("email"),
                        rs.getString("soDienThoai")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy nhân viên theo ID
    public NhanVien getNhanVienById(int id) {
        String sql = "SELECT * FROM NhanVien WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhanVien(
                            rs.getInt("id"),
                            rs.getString("hoTen"),
                            rs.getString("chucVu"),
                            rs.getString("email"),
                            rs.getString("soDienThoai")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mới nhân viên[cite: 2]
    public void insertNhanVien(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (hoTen, chucVu, email, soDienThoai) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getChucVu());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getSoDienThoai());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin nhân viên[cite: 2]
    public void updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET hoTen = ?, chucVu = ?, email = ?, soDienThoai = ? WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getChucVu());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getSoDienThoai());
            ps.setInt(5, nv.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa nhân viên[cite: 2]
    public void deleteNhanVien(int id) {
        String sql = "DELETE FROM NhanVien WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm theo Tên / Email / Số điện thoại[cite: 2]
    public List<NhanVien> searchNhanVien(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE hoTen LIKE ? OR email LIKE ? OR soDienThoai LIKE ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new NhanVien(
                            rs.getInt("id"),
                            rs.getString("hoTen"),
                            rs.getString("chucVu"),
                            rs.getString("email"),
                            rs.getString("soDienThoai")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
