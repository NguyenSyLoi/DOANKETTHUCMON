package control;

import dao.DuAnDAO;
import Model.DuAn;
import java.util.List;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DuAnServlet", urlPatterns = {"/du-an"})
public class DuAnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        DuAnDAO dao = new DuAnDAO();

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteDuAn(id);
            response.sendRedirect("du-an");
            return;
        }

        List<DuAn> list = dao.getAllDuAn();
        request.setAttribute("listDuAn", list);

        // Chuyển hướng đúng đến tên file danhsachduan.jsp
        request.getRequestDispatcher("danhsachduan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // 1. Đọc tham số action gửi từ Form
        String action = request.getParameter("action");
        
        String tenDuAn = request.getParameter("tenDuAn");
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        String moTa = request.getParameter("moTa");

        DuAnDAO dao = new DuAnDAO();

        // 2. Kiểm tra nếu action là "update" thì gọi hàm cập nhật
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            // LƯU Ý: Hãy đảm bảo trong DuAnDAO của bạn đã có hàm updateDuAn(id, tenDuAn, ngayBatDau, ngayKetThuc, moTa)
            dao.updateDuAn(id, tenDuAn, ngayBatDau, ngayKetThuc, moTa);
        } else {
            // Ngược lại (khi thêm mới), gọi hàm insertDuAn
            dao.insertDuAn(tenDuAn, ngayBatDau, ngayKetThuc, moTa);
        }

        response.sendRedirect("du-an");
    }
}