/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package control;

import dao.ThongkeDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/thongke")
public class ThongkeServlet extends HttpServlet {

    private final ThongkeDAO dao = new ThongkeDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        request.setAttribute(
                "statsTongQuan",
                dao.getTongQuan()
        );

        request.setAttribute(
                "listTheoNhanVien",
                dao.getTheoNhanVien()
        );

        request.setAttribute(
                "listTheoDuAn",
                dao.getTheoDuAn()
        );

        request.getRequestDispatcher("/thongke.jsp")
                .forward(request, response);
    }
}