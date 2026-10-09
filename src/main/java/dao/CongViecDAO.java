package dao;

import Model.CongViec;
import context.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PC-31
 */
public class CongViecDAO {

    // Lấy toàn bộ công việc
    public List<CongViec> getAll() {
        List<CongViec> list = new ArrayList<>();
        String sql = "SELECT cv.*, da.projectName AS tenDuAn, nv.name AS tenNhanVien "
                   + "FROM CongViec cv "
                   + "INNER JOIN DuAn da ON cv.duAnId = da.id "
                   + "LEFT JOIN PhanCong pc ON cv.id = pc.congViecId "
                   + "LEFT JOIN NhanVien nv ON pc.nhanVienId = nv.id "
                   + "ORDER BY cv.id DESC";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CongViec cv = new CongViec();
                cv.setId(rs.getInt("id"));
                cv.setTenCongViec(rs.getString("tenCongViec"));
                cv.setMoTa(rs.getString("moTa"));
                cv.setHanHoanThanh(rs.getDate("hanHoanThanh"));
                cv.setTrangThai(rs.getString("trangThai"));
                cv.setMucDoUuTien(rs.getString("mucDoUuTien"));
                cv.setDuAnId(rs.getInt("duAnId"));
                cv.setTenDuAn(rs.getString("tenDuAn"));
                cv.setTenNhanVien(rs.getString("tenNhanVien"));
                list.add(cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm công việc
    public boolean insert(CongViec cv) {
        String sql = "INSERT INTO CongViec (tenCongViec, moTa, hanHoanThanh, trangThai, mucDoUuTien, duAnId) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cv.getTenCongViec());
            ps.setString(2, cv.getMoTa());
            ps.setDate(3, cv.getHanHoanThanh());
            ps.setString(4, cv.getTrangThai());
            ps.setString(5, cv.getMucDoUuTien());
            ps.setInt(6, cv.getDuAnId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa công việc
    public boolean delete(int id) {
        String deleteAssignment = "DELETE FROM PhanCong WHERE congViecId = ?";
        String deleteTask = "DELETE FROM CongViec WHERE id = ?";

        try (Connection con = DBContext.getConnection()) {
            try (PreparedStatement ps1 = con.prepareStatement(deleteAssignment)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(deleteTask)) {
                ps2.setInt(1, id);
                return ps2.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật trạng thái
    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE CongViec SET trangThai = ? WHERE id = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm
    public List<CongViec> search(String keyword, String status) {
        List<CongViec> list = new ArrayList<>();
        String sql = "SELECT DISTINCT cv.*, da.projectName AS tenDuAn, nv.name AS tenNhanVien "
                   + "FROM CongViec cv "
                   + "INNER JOIN DuAn da ON cv.duAnId = da.id "
                   + "LEFT JOIN PhanCong pc ON cv.id = pc.congViecId "
                   + "LEFT JOIN NhanVien nv ON pc.nhanVienId = nv.id "
                   + "WHERE (cv.tenCongViec LIKE ? OR da.projectName LIKE ? OR nv.name LIKE ?) "
                   + "AND cv.trangThai LIKE ? "
                   + "ORDER BY cv.id DESC";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            if (status == null || status.equals("Tất cả")) {
                ps.setString(4, "%");
            } else {
                ps.setString(4, status);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CongViec cv = new CongViec();
                cv.setId(rs.getInt("id"));
                cv.setTenCongViec(rs.getString("tenCongViec"));
                cv.setMoTa(rs.getString("moTa"));
                cv.setHanHoanThanh(rs.getDate("hanHoanThanh"));
                cv.setTrangThai(rs.getString("trangThai"));
                cv.setMucDoUuTien(rs.getString("mucDoUuTien"));
                cv.setDuAnId(rs.getInt("duAnId"));
                cv.setTenDuAn(rs.getString("tenDuAn"));
                cv.setTenNhanVien(rs.getString("tenNhanVien"));
                list.add(cv);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}