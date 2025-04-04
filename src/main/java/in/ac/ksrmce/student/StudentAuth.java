package in.ac.ksrmce.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import in.ac.ksrmce.config.student_config.StudentDao;
import in.ac.ksrmce.config.student_config.StudentEntity;

/**
 * Servlet implementation class StudentAuth
 */
@WebServlet("/studentAuth")
public class StudentAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentAuth() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		// System.out.println("userName : "+ userName);
		// System.out.println("password :"+password);

		StudentEntity auth = StudentDao.getstudentByName(userName);

		// System.out.println("userName :(990) "+auth.getName());
		// System.out.println("password : (990)"+auth.getDob());

		HttpSession session = request.getSession();

		// once the user login then this application is hold the user session attributes
		// till the 3.5 hours which means even the user is inactive it will hold the
		// session data.
		int sessionDurationInSeconds = (int) (3.5 * 60 * 60);
		session.setMaxInactiveInterval(sessionDurationInSeconds);

		session.setAttribute("userName", auth.getName());
		session.setAttribute("photo", request.getContextPath() + "/images/students/photo/" + auth.getPhoto());
		session.setAttribute("reference_number", auth.getReferenceNumber());

		if (userName.equals(auth.getName()) && password.equals(auth.getDob())) {
			response.sendRedirect(request.getContextPath() + "/html/student/instructions.jsp");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
