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
        String tenDuAn = request.getParameter("tenDuAn");
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        String moTa = request.getParameter("moTa");

        DuAnDAO dao = new DuAnDAO();
        dao.insertDuAn(tenDuAn, ngayBatDau, ngayKetThuc, moTa);
        response.sendRedirect("du-an");
    }
}