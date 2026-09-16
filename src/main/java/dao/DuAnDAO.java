/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import context.DBContext;
import Model.DuAn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PC-31
 */
public class DuAnDAO {
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

    // Xóa dự án
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
}
