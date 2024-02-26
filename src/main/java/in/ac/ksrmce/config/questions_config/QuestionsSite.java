package in.ac.ksrmce.config.questions_config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/siteQuestions")
public class QuestionsSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QuestionsSite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page").toLowerCase();	
		switch(page) {

		case "display":
			listquestions(request,response);
			break;
			
		case "adduser":
			adduser(request,response);
			break;
			
		case "updateuser":
			updateuser(request,response);
			break;
			
		case "deleteuser":
			new QuestionsModel().deleteQuestion(Integer.parseInt(request.getParameter("id")));
			listquestions(request,response);
			break;
			
		default:
			request.setAttribute("title","Error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = request.getParameter("form").toLowerCase();	
		switch(form) {

		case "savequestion":
			QuestionsEntity question = new QuestionsEntity(Integer.parseInt(request.getParameter("id")),request.getParameter("question"),request.getParameter("option_one"),request.getParameter("option_two"),request.getParameter("option_three"),request.getParameter("option_four"),Integer.parseInt(request.getParameter("correct_option")));
			new QuestionsModel().save(question);
			listquestions(request,response);
			break;
			
		case "updatequestion":
			QuestionsEntity updatequestion = new QuestionsEntity(Integer.parseInt(request.getParameter("id")),request.getParameter("question"),request.getParameter("option_one"),request.getParameter("option_two"),request.getParameter("option_three"),request.getParameter("option_four"),Integer.parseInt(request.getParameter("correct_option")));
			new QuestionsModel().updateQuestion(updatequestion);
			listquestions(request,response);
			break;
		
		default:
			request.setAttribute("title","Error");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			break;
		}
	}
	
	protected void listquestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QuestionsEntity> questions = new ArrayList<>();
		List<QuestionsEntity> list = new QuestionsModel().listquestions();
		questions = list;
//		request.setAttribute( "title","Dispaly Users");
		request.setAttribute("questions", questions);
		request.getRequestDispatcher("/project/html/admin/preview_all_questions.jsp").forward(request, response);
	}
	
	protected void adduser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute( "title","Add Users");
		request.getRequestDispatcher("adduser.jsp").forward(request, response);
	}
	
	protected void updateuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute( "title","Update Users");
		request.getRequestDispatcher("updateuser.jsp").forward(request, response);
	}

}
