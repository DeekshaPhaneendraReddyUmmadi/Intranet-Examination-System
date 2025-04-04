package in.ac.ksrmce.config.admin_config;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        
        boolean isAuthenticated = authenticate(user_name, password);
        if (isAuthenticated) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_name", user_name);
            response.sendRedirect("/project/html/admin/admin_home.jsp");
        } else {
            response.sendRedirect("/project/html/admin/admin.jsp?error=1");
        }
    }
    
    private boolean authenticate(String user_name, String password) {
    	AdminEntity auth = AdminDao.getEmployeeByName(user_name);
    	return user_name.equals(auth.getUser_name()) && password.equals(auth.getPassword());
    }
}
