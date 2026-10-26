/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import Model.Thongke;
import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongkeDAO {

    // 1. Thống kê tổng quan
    public Map<String, Integer> getTongQuan() {

        Map<String, Integer> stats = new HashMap<>();

        String sql = "SELECT "
                + "SUM(CASE WHEN trangThai = N'Hoàn thành' "
                + "THEN 1 ELSE 0 END) AS DaHoanThanh, "

                + "SUM(CASE WHEN trangThai = N'Đang thực hiện' "
                + "THEN 1 ELSE 0 END) AS DangLam, "

                + "SUM(CASE WHEN hanHoanThanh < CAST(GETDATE() AS DATE) "
                + "AND trangThai <> N'Hoàn thành' "
                + "THEN 1 ELSE 0 END) AS QuaHan "

                + "FROM CongViec";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                stats.put("DaHoanThanh", rs.getInt("DaHoanThanh"));
                stats.put("DangLam", rs.getInt("DangLam"));
                stats.put("QuaHan", rs.getInt("QuaHan"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stats;
    }

    // 2. Thống kê theo nhân viên
    public List<Thongke> getTheoNhanVien() {

        List<Thongke> list = new ArrayList<>();

        String sql = "SELECT nv.id, nv.hoTen, "

                + "COUNT(CASE WHEN cv.trangThai = N'Hoàn thành' "
                + "THEN 1 END) AS DaHoanThanh, "

                + "COUNT(CASE WHEN cv.trangThai = N'Đang thực hiện' "
                + "THEN 1 END) AS DangLam, "

                + "COUNT(CASE WHEN cv.hanHoanThanh < CAST(GETDATE() AS DATE) "
                + "AND cv.trangThai <> N'Hoàn thành' "
                + "THEN 1 END) AS QuaHan "

                + "FROM NhanVien nv "

                + "LEFT JOIN PhanCong pc "
                + "ON nv.id = pc.NhanVienId "

                + "LEFT JOIN CongViec cv "
                + "ON pc.CongViecId = cv.id "

                + "GROUP BY nv.id, nv.hoTen "
                + "ORDER BY nv.id";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Thongke tk = new Thongke();

                tk.setObjectId(rs.getInt("id"));
                tk.setObjectName(rs.getString("hoTen"));
                tk.setDaHoanThanh(rs.getInt("DaHoanThanh"));
                tk.setDangLam(rs.getInt("DangLam"));
                tk.setQuaHan(rs.getInt("QuaHan"));

                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 3. Thống kê theo dự án
    public List<Thongke> getTheoDuAn() {

        List<Thongke> list = new ArrayList<>();

        String sql = "SELECT da.id, da.tenDuAn, "

                + "COUNT(CASE WHEN cv.trangThai = N'Hoàn thành' "
                + "THEN 1 END) AS DaHoanThanh, "

                + "COUNT(CASE WHEN cv.trangThai = N'Đang thực hiện' "
                + "THEN 1 END) AS DangLam, "

                + "COUNT(CASE WHEN cv.hanHoanThanh < CAST(GETDATE() AS DATE) "
                + "AND cv.trangThai <> N'Hoàn thành' "
                + "THEN 1 END) AS QuaHan "

                + "FROM DuAn da "

                + "LEFT JOIN CongViec cv "
                + "ON da.id = cv.MaDuAn "

                + "GROUP BY da.id, da.tenDuAn "
                + "ORDER BY da.id";

        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Thongke tk = new Thongke();

                tk.setObjectId(rs.getInt("id"));
                tk.setObjectName(rs.getString("tenDuAn"));
                tk.setDaHoanThanh(rs.getInt("DaHoanThanh"));
                tk.setDangLam(rs.getInt("DangLam"));
                tk.setQuaHan(rs.getInt("QuaHan"));

                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}