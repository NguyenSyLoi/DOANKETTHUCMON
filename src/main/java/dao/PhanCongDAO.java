/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import Model.PhanCong;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhanCongDAO {

    public List<PhanCong> getAll(String keyword) {
        List<PhanCong> ds = new ArrayList<>();

        String sql = "SELECT pc.id, pc.CongViecId, pc.NhanVienId, "
                + "pc.ngayPhanCong, cv.tenCongViec, nv.hoTen "
                + "FROM PhanCong pc "
                + "INNER JOIN CongViec cv ON pc.CongViecId = cv.id "
                + "INNER JOIN NhanVien nv ON pc.NhanVienId = nv.id ";

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += "WHERE cv.tenCongViec LIKE ? OR nv.hoTen LIKE ? ";
        }

        sql += "ORDER BY pc.id DESC";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword.trim() + "%");
                ps.setString(2, "%" + keyword.trim() + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PhanCong pc = new PhanCong();

                    pc.setId(rs.getInt("id"));
                    pc.setCongViecId(rs.getInt("CongViecId"));
                    pc.setNhanVienId(rs.getInt("NhanVienId"));
                    pc.setNgayPhanCong(rs.getDate("ngayPhanCong"));
                    pc.setTenCongViec(rs.getString("tenCongViec"));
                    pc.setTenNhanVien(rs.getString("hoTen"));

                    ds.add(pc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public PhanCong getById(int id) {
        String sql = "SELECT * FROM PhanCong WHERE id = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PhanCong pc = new PhanCong();

                    pc.setId(rs.getInt("id"));
                    pc.setCongViecId(rs.getInt("CongViecId"));
                    pc.setNhanVienId(rs.getInt("NhanVienId"));
                    pc.setNgayPhanCong(rs.getDate("ngayPhanCong"));

                    return pc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean insert(PhanCong pc) {
        String sql = "INSERT INTO PhanCong "
                + "(CongViecId, NhanVienId, ngayPhanCong) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pc.getCongViecId());
            ps.setInt(2, pc.getNhanVienId());

            if (pc.getNgayPhanCong() != null) {
                ps.setDate(3, pc.getNgayPhanCong());
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(PhanCong pc) {
        String sql = "UPDATE PhanCong SET "
                + "CongViecId = ?, NhanVienId = ?, ngayPhanCong = ? "
                + "WHERE id = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pc.getCongViecId());
            ps.setInt(2, pc.getNhanVienId());

            if (pc.getNgayPhanCong() != null) {
                ps.setDate(3, pc.getNgayPhanCong());
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setInt(4, pc.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM PhanCong WHERE id = ?";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}