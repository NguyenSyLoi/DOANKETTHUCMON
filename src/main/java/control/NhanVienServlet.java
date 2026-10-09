/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.NhanVienDAO;
import Model.NhanVien;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/nhanvien")
public class NhanVienServlet extends HttpServlet {

    private NhanVienDAO nhanVienDAO;

    @Override
    public void init() {
        nhanVienDAO = new NhanVienDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteNhanVien(request, response);
                break;
            case "search":
                searchNhanVien(request, response);
                break;
            default:
                listNhanVien(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        if ("insert".equals(action)) {
            insertNhanVien(request, response);
        } else if ("update".equals(action)) {
            updateNhanVien(request, response);
        }
    }

    private void listNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NhanVien> list = nhanVienDAO.getAllNhanVien();
        request.setAttribute("listNhanVien", list);
        request.getRequestDispatcher("nhanvien.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("nhanvien-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        NhanVien existingNhanVien = nhanVienDAO.getNhanVienById(id);
        request.setAttribute("nhanVien", existingNhanVien);
        request.getRequestDispatcher("nhanvien-form.jsp").forward(request, response);
    }

    private void insertNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String hoTen = request.getParameter("hoTen");
        String chucVu = request.getParameter("chucVu");
        String email = request.getParameter("email");
        String soDienThoai = request.getParameter("soDienThoai");

        NhanVien newNV = new NhanVien(hoTen, chucVu, email, soDienThoai);
        nhanVienDAO.insertNhanVien(newNV);
        response.sendRedirect("nhanvien");
    }

    private void updateNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String hoTen = request.getParameter("hoTen");
        String chucVu = request.getParameter("chucVu");
        String email = request.getParameter("email");
        String soDienThoai = request.getParameter("soDienThoai");

        NhanVien nv = new NhanVien(id, hoTen, chucVu, email, soDienThoai);
        nhanVienDAO.updateNhanVien(nv);
        response.sendRedirect("nhanvien");
    }

    private void deleteNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        nhanVienDAO.deleteNhanVien(id);
        response.sendRedirect("nhanvien");
    }

    private void searchNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<NhanVien> list = nhanVienDAO.searchNhanVien(keyword);
        request.setAttribute("listNhanVien", list);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("nhanvien.jsp").forward(request, response);
    }
}
