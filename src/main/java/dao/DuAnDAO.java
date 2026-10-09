package dao;

import context.DBContext;
import Model.DuAn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuAnDAO {

    // 1. Lấy toàn bộ danh sách dự án
    public List<DuAn> getAllDuAn() {
        List<DuAn> list = new ArrayList<>();
        String sql = "SELECT * FROM DuAn";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DuAn(
                    rs.getInt("id"),
                    rs.getString("tenDuAn"),
                    rs.getDate("ngayBatDau"),
                    rs.getDate("ngayKetThuc"),
                    rs.getString("moTa")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới dự án
    public void insertDuAn(String tenDuAn, String ngayBatDau, String ngayKetThuc, String moTa) {
        String sql = "INSERT INTO DuAn (tenDuAn, ngayBatDau, ngayKetThuc, moTa) VALUES (?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDuAn);
            ps.setString(2, ngayBatDau);
            ps.setString(3, ngayKetThuc);
            ps.setString(4, moTa);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Xóa dự án
    public void deleteDuAn(int id) {
        String sql = "DELETE FROM DuAn WHERE id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Cập nhật dự án (Sửa lại tên bảng thành DuAn cho đồng bộ)
    public void updateDuAn(int id, String tenDuAn, String ngayBatDau, String ngayKetThuc, String moTa) {
        String sql = "UPDATE DuAn SET tenDuAn = ?, ngayBatDau = ?, ngayKetThuc = ?, moTa = ? WHERE id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
           
            ps.setString(1, tenDuAn);
            ps.setString(2, ngayBatDau);
            ps.setString(3, ngayKetThuc);
            ps.setString(4, moTa);
            ps.setInt(5, id);
           
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Lấy thông tin 1 dự án theo ID để đổ lên Form
    public DuAn getDuAnById(int id) {
        String sql = "SELECT * FROM DuAn WHERE id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DuAn(
                        rs.getInt("id"),
                        rs.getString("tenDuAn"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc"),
                        rs.getString("moTa")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}