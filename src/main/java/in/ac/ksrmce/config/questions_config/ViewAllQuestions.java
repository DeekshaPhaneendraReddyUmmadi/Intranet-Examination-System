package in.ac.ksrmce.config.questions_config;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/listQuestions")
public class ViewAllQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewAllQuestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<QuestionsEntity> questions = QuestionsDao.listquestions();
		request.setAttribute("questions", questions);
		HttpSession session = request.getSession();
		session.setAttribute("questions", questions);
		response.sendRedirect("/project/html/admin/preview_all_questions.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
