package in.ac.ksrmce.kgcetbackend;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import in.ac.ksrmce.config.questions_config.QuestionsDao;
import in.ac.ksrmce.config.questions_config.QuestionsEntity;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QuestionsEntity> questions = QuestionsDao.randomquesions();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/html/exam/quizVone.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        System.out.println(action);
        // Handle different button actions
        if ("next".equals(action)) {
            // Proceed to the next question
            // Retrieve the next question and forward to quiz.jsp
        } else if ("mark".equals(action)) {
            // Mark the current question for review
        } else if ("clear".equals(action)) {
            // Clear the selected option for the current question
        } else if ("save".equals(action)) {
            // Save the selected option for the current question and proceed to the next question
        } else {
            // Handle other actions or errors
        }
    }
}

