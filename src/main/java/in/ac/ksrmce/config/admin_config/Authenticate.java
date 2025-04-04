package in.ac.ksrmce.config.admin_config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/auth")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Authenticate() {
		super();
	}

	private static boolean authTrue = false;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");

		AdminEntity auth = AdminDao.getEmployeeByName(user_name);

		if (user_name.equals(auth.getUser_name()) && password.equals(auth.getPassword())) {
			authTrue = true;
			response.sendRedirect("/project/html/admin/admin_home.jsp");
		}
	}

	public static boolean getAuthTrue() {
		return authTrue;
	}
}
