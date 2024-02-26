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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Assume you have a QuestionDAO class to interact with the database
//		List<QuestionsEntity> questions;
//		try {
//			questions = QuestionsDao.listquestions();
//			// Set the list of questions as an attribute in the request scope
//			request.setAttribute("questions", questions);
//
//			// Forward the request to your JSP page
////			RequestDispatcher dispatcher = request.getRequestDispatcher("/project/html/admin/preview_all_questions.jsp");
////			dispatcher.forward(request, response);
//			response.sendRedirect("/project/html/admin/preview_all_questions.jsp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} // Retrieve questions from the database
		
//		List<QuestionsEntity> questions = new ArrayList<>();
//		List<QuestionsEntity> list = new QuestionsDao.listquestions();
		
		List<QuestionsEntity> questions = QuestionsDao.listquestions();
//		for(QuestionsEntity q : questions) {
//			System.out.println("-----------------------------------------------------\n");
//			System.out.println(q.getId());
//			System.out.println(q.getQuestion());
//			System.out.println(q.getOption_one());
//			System.out.println(q.getOption_two());
//			System.out.println(q.getOption_three());
//			System.out.println(q.getOption_four());
//			System.out.println(q.getCorrect_option());
//			System.out.println("-----------------------------------------------------\n");
//			
//		}
//		questions = list;	
		request.setAttribute("questions", questions);
//		response.sendRedirect("/project/html/admin/preview_all_questions.jsp");
//		RequestDispatcher  rd = request.getRequestDispatcher("/project/html/admin/preview_all_questions.jsp");
//		rd.forward(request, response);

//		String contextPath = request.getContextPath();
//		String jspPath = "/html/admin/preview_all_questions.jsp";
//		RequestDispatcher  rd = request.getRequestDispatcher(contextPath + jspPath);
//		rd.forward(request, response);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("questions", questions);
		response.sendRedirect("/project/html/admin/preview_all_questions.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
