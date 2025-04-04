package in.ac.ksrmce.config.admin_config;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveAdmin")
public class AdminSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminSaveServlet() {
        super();
    }

    static int i = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");

        AdminEntity admin = new AdminEntity();
        admin.setUser_name(user_name);
        admin.setPassword(password);

        int saved = AdminDao.save(admin);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (saved > 0) {
            out.println("<h2>Question saved successfully!</h2>");
        } else {
            out.println("<h2>Failed to save question. Please try again.</h2>");
        }
    }

}
