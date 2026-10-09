/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import Model.ThongKe;
import dao.ThongkeDAO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author PC_29
 */
@WebServlet(name = "ThongkeServlet", urlPatterns = {"/thongke"})
public class ThongkeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ThongkeDAO dao = new ThongkeDAO();

        Map<String, Integer> statsTongQuan = dao.getThongKeTongQuan();
        List<ThongKe> listTheoNhanVien = dao.getThongKeTheoNhanVien();
        List<ThongKe> listTheoDuAn = dao.getThongKeTheoDuAn();

        request.setAttribute("statsTongQuan", statsTongQuan);
        request.setAttribute("listTheoNhanVien", listTheoNhanVien);
        request.setAttribute("listTheoDuAn", listTheoDuAn);

        request.getRequestDispatcher("thongke.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
