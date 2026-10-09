package dao;

import context.DBContext;
import Model.DuAn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuAnDAO {

    public List<DuAn> getAllDuAn() {
        List<DuAn> list = new ArrayList<>();
        String sql = "SELECT * FROM DuAn";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) {
                throw new SQLException("Không thể kết nối SQL Server. Hãy kiểm tra DBContext.");
            }

            try (PreparedStatement ps = conn.prepareStatement(sql);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertDuAn(String tenDuAn, String ngayBatDau, String ngayKetThuc, String moTa) {
        String sql = "INSERT INTO DuAn (tenDuAn, ngayBatDau, ngayKetThuc, moTa) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) {
                throw new SQLException("Không thể kết nối SQL Server. Hãy kiểm tra DBContext.");
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, tenDuAn);
                ps.setString(2, ngayBatDau);
                ps.setString(3, ngayKetThuc);
                ps.setString(4, moTa);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDuAn(int id) {
        String sql = "DELETE FROM DuAn WHERE id = ?";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) {
                throw new SQLException("Không thể kết nối SQL Server. Hãy kiểm tra DBContext.");
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDuAn(int id, String tenDuAn, String ngayBatDau, String ngayKetThuc, String moTa) {
        // Dùng cùng tên bảng DuAn như các hàm lấy/thêm/xóa ở trên.
        String sql = "UPDATE DuAn SET tenDuAn = ?, ngayBatDau = ?, ngayKetThuc = ?, moTa = ? WHERE id = ?";

        try (Connection conn = DBContext.getConnection()) {
            if (conn == null) {
                throw new SQLException("Không thể kết nối SQL Server. Hãy kiểm tra DBContext.");
            }

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, tenDuAn);
                ps.setString(2, ngayBatDau);
                ps.setString(3, ngayKetThuc);
                ps.setString(4, moTa);
                ps.setInt(5, id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
