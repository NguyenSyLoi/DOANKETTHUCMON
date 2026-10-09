/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.ThongKe;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongkeDAO {

    // 1. Đếm tổng quan số công việc Đã hoàn thành / Đang làm / Quá hạn
    public Map<String, Integer> getThongKeTongQuan() {
        Map<String, Integer> map = new HashMap<>();
        map.put("DaHoanThanh", 0);
        map.put("DangLam", 0);
        map.put("QuaHan", 0);

        String sql = "SELECT "
                + " SUM(CASE WHEN trang_thai = N'Đã hoàn thành' THEN 1 ELSE 0 END) AS DaHoanThanh, "
                + " SUM(CASE WHEN trang_thai = N'Đang làm' AND (han_chat >= GETDATE() OR han_chat IS NULL) THEN 1 ELSE 0 END) AS DangLam, "
                + " SUM(CASE WHEN trang_thai != N'Đã hoàn thành' AND han_chat < GETDATE() THEN 1 ELSE 0 END) AS QuaHan "
                + "FROM CongViec";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                map.put("DaHoanThanh", rs.getInt("DaHoanThanh"));
                map.put("DangLam", rs.getInt("DangLam"));
                map.put("QuaHan", rs.getInt("QuaHan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    // 2. Thống kê theo từng Nhân viên
    public List<ThongKe> getThongKeTheoNhanVien() {
        List<ThongKe> list = new ArrayList<>();
        String sql = "SELECT nv.ma_nv, nv.ten_nv, "
                + " SUM(CASE WHEN cv.trang_thai = N'Đã hoàn thành' THEN 1 ELSE 0 END) AS DaHoanThanh, "
                + " SUM(CASE WHEN cv.trang_thai = N'Đang làm' AND (cv.han_chat >= GETDATE() OR cv.han_chat IS NULL) THEN 1 ELSE 0 END) AS DangLam, "
                + " SUM(CASE WHEN cv.trang_thai != N'Đã hoàn thành' AND cv.han_chat < GETDATE() THEN 1 ELSE 0 END) AS QuaHan "
                + "FROM NhanVien nv LEFT JOIN CongViec cv ON nv.ma_nv = cv.ma_nv "
                + "GROUP BY nv.ma_nv, nv.ten_nv";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ThongKe(
                        rs.getString("ma_nv"),
                        rs.getString("ten_nv"),
                        rs.getInt("DaHoanThanh"),
                        rs.getInt("DangLam"),
                        rs.getInt("QuaHan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Thống kê theo từng Dự án
    public List<ThongKe> getThongKeTheoDuAn() {
        List<ThongKe> list = new ArrayList<>();
        String sql = "SELECT da.ma_du_an, da.ten_du_an, "
                + " SUM(CASE WHEN cv.trang_thai = N'Đã hoàn thành' THEN 1 ELSE 0 END) AS DaHoanThanh, "
                + " SUM(CASE WHEN cv.trang_thai = N'Đang làm' AND (cv.han_chat >= GETDATE() OR cv.han_chat IS NULL) THEN 1 ELSE 0 END) AS DangLam, "
                + " SUM(CASE WHEN cv.trang_thai != N'Đã hoàn thành' AND cv.han_chat < GETDATE() THEN 1 ELSE 0 END) AS QuaHan "
                + "FROM DuAn da LEFT JOIN CongViec cv ON da.ma_du_an = cv.ma_du_an "
                + "GROUP BY da.ma_du_an, da.ten_du_an";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ThongKe(
                        rs.getString("ma_du_an"),
                        rs.getString("ten_du_an"),
                        rs.getInt("DaHoanThanh"),
                        rs.getInt("DangLam"),
                        rs.getInt("QuaHan")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}