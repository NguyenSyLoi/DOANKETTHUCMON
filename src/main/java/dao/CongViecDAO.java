/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import Model.CongViec;
import control.CongViecServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CongViecDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // 1. Thêm công việc mới và trả về ID vừa tạo (hoặc thêm công việc kèm phân công)
    public boolean addCongViec(String tenCongViec, String moTa, String ngayBatDau, String ngayKetThuc, String trangThai, int idDuAn, Integer idNhanVien) {
        String queryCV = "INSERT INTO CongViec (tenCongViec, moTa, ngayBatDau, ngayKetThuc, trangThai, idDuAn, idNhanVien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(queryCV);
            ps.setString(1, tenCongViec);
            ps.setString(2, moTa);
            ps.setString(3, ngayBatDau);
            ps.setString(4, ngayKetThuc);
            ps.setString(5, trangThai);
            ps.setInt(6, idDuAn);
           
            if (idNhanVien != null && idNhanVien > 0) {
                ps.setInt(7, idNhanVien);
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }
           
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 2. Cập nhật phân công nhân viên cho công việc đã có
    public boolean phanCongNhanVien(int idCongViec, int idNhanVien) {
        String query = "UPDATE CongViec SET idNhanVien = ? WHERE idCongViec = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idNhanVien);
            ps.setInt(2, idCongViec);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
    public List<CongViec> seach(String keyword)
    {
         List<CongViec> dsCongViec = new ArrayList<>();

        try (Connection con = DBContext.getConnection()) {
            String sql;
            PreparedStatement ps;

            // Sử dụng LEFT JOIN để lấy tên dự án từ bảng DuAn dựa trên MaDuAn
            if (keyword != null && !keyword.trim().isEmpty()) {
                sql = "SELECT c.id, c.tenCongViec, c.moTa, c.hanHoanThanh, c.trangThai, c.mucDoUuTien, c.MaDuAn, d.tenDuAn "
                        + "FROM CongViec c "
                        + "LEFT JOIN DuAn d ON c.MaDuAn = d.id "
                        + "WHERE tenCongViec LIKE ? "
                        + "ORDER BY c.id DESC";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword.trim() + "%");
            } else {
                sql = "SELECT c.id, tenCongViec, c.moTa, hanHoanThanh, trangThai, mucDoUuTien, c.MaDuAn, d.tenDuAn "
                        + "FROM CongViec c "
                        + "LEFT JOIN DuAn d ON c.MaDuAn = d.id "
                        + "ORDER BY c.id DESC";
                ps = con.prepareStatement(sql);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CongViec cv = new CongViec();
                    cv.setId(rs.getInt("id"));             // Đúng tên cột khóa chính MaCongViec
                    cv.setTenCongViec(rs.getString("tenCongViec"));
                    cv.setMoTa(rs.getString("moTa"));
                    cv.setHanHoanThanh(rs.getDate("hanHoanThanh"));
                    cv.setTrangThai(rs.getString("trangThai"));
                    cv.setMucDoUuTien(rs.getString("mucDoUuTien"));
                    cv.setDuAnId(rs.getInt("MaDuAn"));
                    dsCongViec.add(cv);
                }
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("=== LỖI TRUY VẤN CÔNG VIỆC ===:" + e.toString());
            e.printStackTrace(); // In lỗi đỏ ra Output/Console nếu có sự cố
        }
        return  dsCongViec;
    }
   
    public static void main(String[] args) {
         CongViecDAO cvDAO = new CongViecDAO();
         for(var cv : cvDAO.seach(null))
         {
             System.out.println(cv.getTenCongViec());
         }
    }
     
}