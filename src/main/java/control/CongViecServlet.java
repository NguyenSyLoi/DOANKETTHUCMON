package control;

import Model.CongViec;
import context.DBContext;
import dao.CongViecDAO;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * @author PC-31
 */
@WebServlet("/CongViecServlet")
public class CongViecServlet extends HttpServlet {

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
                int id = Integer.parseInt(idStr);
                String sql = "DELETE FROM CongViec WHERE id = ?";

                try (Connection con = DBContext.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }

                response.sendRedirect("CongViecServlet?success=delete");
                return;
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
                int id = Integer.parseInt(idStr);
                String sql = "SELECT * FROM CongViec WHERE id = ?";

                try (Connection con = DBContext.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // TÌM KIẾM & DANH SÁCH CÔNG VIỆC
        String keyword = request.getParameter("keyword");
        List<CongViec> dsCongViec = new ArrayList<>();

        try {
            Connection con = DBContext.getConnection();
            String sql;
            PreparedStatement ps;

            if (keyword != null && !keyword.trim().isEmpty()) {
                sql = "SELECT * FROM CongViec WHERE tenCongViec LIKE ? ORDER BY id DESC";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword.trim() + "%");
            } else {
                sql = "SELECT * FROM CongViec ORDER BY id DESC";
                ps = con.prepareStatement(sql);
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
                dsCongViec.add(cv);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Không lấy được danh sách công việc!");
        }

        // LẤY DANH SÁCH DỰ ÁN
        List<DuAn> dsDuAn = new ArrayList<>();
        try {
            Connection con = DBContext.getConnection();
            String sql = "SELECT id, tenDuAn FROM DuAn ORDER BY id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DuAn da = new DuAn();
                da.setId(rs.getInt("id"));
                da.setTenDuAn(rs.getString("tenDuAn"));
                dsDuAn.add(da);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("dsCongViec", dsCongViec);
        request.setAttribute("dsDuAn", dsDuAn);
        request.getRequestDispatcher("/congviec.jsp").forward(request, response);
    }

    // ============================
    // POST: Thêm / Sửa
    // ============================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // THÊM
        if ("insert".equals(action)) {
            String tenCongViec = request.getParameter("tenCongViec");
            String moTa = request.getParameter("moTa");
            String hanHoanThanh = request.getParameter("hanHoanThanh");
            String trangThai = request.getParameter("trangThai");
            String mucDoUuTien = request.getParameter("mucDoUuTien");
            String duAnId = request.getParameter("duAnId");

            String sql = "INSERT INTO CongViec (tenCongViec, moTa, hanHoanThanh, trangThai, mucDoUuTien, duAnId) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection con = DBContext.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, tenCongViec);
                ps.setString(2, moTa);
                ps.setDate(3, Date.valueOf(hanHoanThanh));
                ps.setString(4, trangThai);
                ps.setString(5, mucDoUuTien);
                ps.setInt(6, Integer.parseInt(duAnId));
                ps.executeUpdate();

                response.sendRedirect("CongViecServlet?success=insert");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("CongViecServlet?error=insert");
            }
            return;
        }

        // SỬA
        if ("update".equals(action)) {
            String id = request.getParameter("id");
            String tenCongViec = request.getParameter("tenCongViec");
            String moTa = request.getParameter("moTa");
            String hanHoanThanh = request.getParameter("hanHoanThanh");
            String trangThai = request.getParameter("trangThai");
            String mucDoUuTien = request.getParameter("mucDoUuTien");
            String duAnId = request.getParameter("duAnId");

            String sql = "UPDATE CongViec SET tenCongViec = ?, moTa = ?, hanHoanThanh = ?, trangThai = ?, mucDoUuTien = ?, duAnId = ? WHERE id = ?";

            try (Connection con = DBContext.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, tenCongViec);
                ps.setString(2, moTa);
                ps.setDate(3, Date.valueOf(hanHoanThanh));
                ps.setString(4, trangThai);
                ps.setString(5, mucDoUuTien);
                ps.setInt(6, Integer.parseInt(duAnId));
                ps.setInt(7, Integer.parseInt(id));
                ps.executeUpdate();

                response.sendRedirect("CongViecServlet?success=update");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("CongViecServlet?error=update");
            }
            return;
        }

        response.sendRedirect("CongViecServlet");
    }

    // ============================
    // CLASS CÔNG VIỆC
    // ============================
    public static class CongViec {
        private int id;
        private String tenCongViec;
        private String moTa;
        private Date hanHoanThanh;
        private String trangThai;
        private String mucDoUuTien;
        private int duAnId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTenCongViec() {
            return tenCongViec;
        }

        public void setTenCongViec(String tenCongViec) {
            this.tenCongViec = tenCongViec;
        }

        public String getMoTa() {
            return moTa;
        }

        public void setMoTa(String moTa) {
            this.moTa = moTa;
        }

        public Date getHanHoanThanh() {
            return hanHoanThanh;
        }

        public void setHanHoanThanh(Date hanHoanThanh) {
            this.hanHoanThanh = hanHoanThanh;
        }

        public String getTrangThai() {
            return trangThai;
        }

        public void setTrangThai(String trangThai) {
            this.trangThai = trangThai;
        }

        public String getMucDoUuTien() {
            return mucDoUuTien;
        }

        public void setMucDoUuTien(String mucDoUuTien) {
            this.mucDoUuTien = mucDoUuTien;
        }

        public int getDuAnId() {
            return duAnId;
        }

        public void setDuAnId(int duAnId) {
            this.duAnId = duAnId;
        }
    }

    // ============================
    // CLASS DỰ ÁN
    // ============================
    public static class DuAn {
        private int id;
        private String tenDuAn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTenDuAn() {
            return tenDuAn;
        }

        public void setTenDuAn(String tenDuAn) {
            this.tenDuAn = tenDuAn;
        }
    }
}