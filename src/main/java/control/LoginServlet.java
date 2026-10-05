package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Truy cập bằng phương thức GET sẽ chuyển về trang đăng nhập
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra tài khoản và mật khẩu
        if ("Admin".equals(username) && "123456".equals(password)) {
            // Đăng nhập thành công -> Lưu session
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            
            // LỰA CHỌN 1: Chuyển hướng trực tiếp tới giao diện Home.jsp
            response.sendRedirect("Home.jsp");
            

        } else {
            // Đăng nhập thất bại -> Truyền thông báo lỗi và load lại login.jsp
            request.setAttribute("errorMessage", "Tài khoản và mật khẩu của bạn đã sai!");
            request.setAttribute("enteredUser", username);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}