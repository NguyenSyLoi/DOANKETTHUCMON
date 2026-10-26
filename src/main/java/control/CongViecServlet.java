/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import Model.CongViec;
import Model.DuAn;
import context.DBContext;
import dao.CongViecDAO;
import dao.DuAnDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CongViecServlet")
public class CongViecServlet extends HttpServlet {

    CongViecDAO cvDAO = new CongViecDAO();
    DuAnDAO daDAO = new DuAnDAO();

    // ============================
    // GET: Hiển thị danh sách công việc
    // ============================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // XÓA CÔNG VIỆC
        if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            try {
                if (idStr != null && !idStr.isEmpty()) {
                    int id = Integer.parseInt(idStr);
                    String sql = "DELETE FROM CongViec WHERE id = ?";

                    try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                    response.sendRedirect("CongViecServlet?success=delete");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("CongViecServlet?error=delete");
                return;
            }
        }

        // LẤY ID ĐỂ SỬA
        if ("edit".equals(action)) {
            String idStr = request.getParameter("id");
            try {
                if (idStr != null && !idStr.isEmpty()) {
                    int id = Integer.parseInt(idStr);
                    String sql = "SELECT * FROM CongViec WHERE id = ?";

                    try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setInt(1, id);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                request.setAttribute("editId", rs.getInt("id"));
                                request.setAttribute("editTen", rs.getString("tenCongViec"));
                                request.setAttribute("editMoTa", rs.getString("moTa"));
                                request.setAttribute("editHan", rs.getDate("hanHoanThanh"));
                                request.setAttribute("editTrangThai", rs.getString("trangThai"));
                                request.setAttribute("editUuTien", rs.getString("mucDoUuTien"));
                                request.setAttribute("editDuAnId", rs.getInt("duAnId"));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String keyword = request.getParameter("keyword");
        List<CongViec> dsCongViec = cvDAO.seach(keyword);
        List<DuAn> dsDuAn = daDAO.getAllDuAn();

        request.setAttribute("dsCongViec", dsCongViec);
        request.setAttribute("dsDuAn", dsDuAn);
        request.getRequestDispatcher("/congviec.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // THÊM CÔNG VIỆC
        if ("insert".equals(action)) {
            String tenCongViec = request.getParameter("tenCongViec");
            String moTa = request.getParameter("moTa");
            String hanHoanThanh = request.getParameter("hanHoanThanh");
            String trangThai = request.getParameter("trangThai");
            String mucDoUuTien = request.getParameter("mucDoUuTien");
            String duAnIdStr = request.getParameter("duAnId");

            if (tenCongViec == null || tenCongViec.trim().isEmpty()
                    || duAnIdStr == null || duAnIdStr.trim().isEmpty()) {
                response.sendRedirect("CongViecServlet?error=insert");
                return;
            }

            // Đã đổi tên cột 'duAnId' thành 'MaDuAn' chuẩn theo CSDL của bạn
            String sql = "INSERT INTO CongViec (tenCongViec, moTa, hanHoanThanh, trangThai, mucDoUuTien, MaDuAn) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, tenCongViec.trim());
                ps.setString(2, moTa != null ? moTa.trim() : "");

                if (hanHoanThanh != null && !hanHoanThanh.trim().isEmpty()) {
                    ps.setDate(3, Date.valueOf(hanHoanThanh.trim()));
                } else {
                    ps.setNull(3, java.sql.Types.DATE);
                }

                ps.setString(4, trangThai);
                ps.setString(5, mucDoUuTien);
                ps.setInt(6, Integer.parseInt(duAnIdStr.trim()));

                ps.executeUpdate();
                response.sendRedirect("CongViecServlet?success=insert");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("CongViecServlet?error=insert");
            }
            return;
        }// CẬP NHẬT CÔNG VIỆC

        if ("update".equals(action)) {
            String id = request.getParameter("id");
            String tenCongViec = request.getParameter("tenCongViec");
            String moTa = request.getParameter("moTa");
            String hanHoanThanh = request.getParameter("hanHoanThanh");
            String trangThai = request.getParameter("trangThai");
            String mucDoUuTien = request.getParameter("mucDoUuTien");
            String duAnIdStr = request.getParameter("duAnId");

            // Kiểm tra đầu vào
            if (id == null || id.trim().isEmpty()
                    || tenCongViec == null || tenCongViec.trim().isEmpty()
                    || duAnIdStr == null || duAnIdStr.trim().isEmpty()) {
                System.out.println("Lỗi Update: idStr=" + id + ", ten=" + tenCongViec + ", duAn=" + duAnIdStr);
                response.sendRedirect("CongViecServlet?error=update");
                return;
            }

            // Thử câu UPDATE theo 'MaCongViec' (nếu SQL dùng MaCongViec), hoặc 'id' nếu SQL dùng id
            String sql = "UPDATE CongViec SET tenCongViec = ?, moTa = ?, hanHoanThanh = ?, trangThai = ?, mucDoUuTien = ?, MaDuAn = ? WHERE id = ?";

            try (Connection con = DBContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, tenCongViec.trim());
                ps.setString(2, moTa != null ? moTa.trim() : "");

                if (hanHoanThanh != null && !hanHoanThanh.trim().isEmpty()) {
                    ps.setDate(3, Date.valueOf(hanHoanThanh.trim()));
                } else {
                    ps.setNull(3, java.sql.Types.DATE);
                }

                ps.setString(4, trangThai);
                ps.setString(5, mucDoUuTien);
                ps.setInt(6, Integer.parseInt(duAnIdStr.trim()));
                ps.setInt(7, Integer.parseInt(id.trim()));

                ps.executeUpdate();
                response.sendRedirect("CongViecServlet?success=update");
            } catch (Exception e) {
                System.out.println("=== LỖI CẬP NHẬT CÔNG VIỆC ===");
                e.printStackTrace(); // In lỗi chi tiết ra Console
                response.sendRedirect("CongViecServlet?error=update");
            }
            return;
        }
        response.sendRedirect("CongViecServlet");
    }

}


