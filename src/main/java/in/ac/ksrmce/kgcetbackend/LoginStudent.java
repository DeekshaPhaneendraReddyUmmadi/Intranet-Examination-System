package in.ac.ksrmce.kgcetbackend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import in.ac.ksrmce.config.admin_config.AdminDao;
import in.ac.ksrmce.config.admin_config.AdminEntity;


@WebServlet("/loginstudent")
public class LoginStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String user_name=request.getParameter("user_name");
		String password=request.getParameter("password");
			
		AdminEntity auth = AdminDao.getEmployeeByName(user_name);
		
		if(user_name.equals(auth.getUser_name()) && password.equals(auth.getPassword())) {
			response.sendRedirect("/project/QuizServlet");
		}
	}

}
