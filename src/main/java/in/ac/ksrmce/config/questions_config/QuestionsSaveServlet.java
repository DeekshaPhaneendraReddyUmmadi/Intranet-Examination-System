package in.ac.ksrmce.config.questions_config;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionsSaveServlet
 */

@WebServlet("/saveQuestion")
public class QuestionsSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsSaveServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from request parameters
        String question = request.getParameter("question");
        String optionOne = request.getParameter("option_one");
        String optionTwo = request.getParameter("option_two");
        String optionThree = request.getParameter("option_three");
        String optionFour = request.getParameter("option_four");
        int correctOption = Integer.parseInt(request.getParameter("correct_option"));
        
        // Create a Question object and set its properties
        QuestionsEntity newQuestion = new QuestionsEntity();
        newQuestion.setQuestion(question);
        newQuestion.setOption_one(optionOne);
        newQuestion.setOption_two(optionTwo);
        newQuestion.setOption_three(optionThree);
        newQuestion.setOption_four(optionFour);
        newQuestion.setCorrect_option(correctOption);
        
        // Call the save method of the DAO to save the question to the database
        QuestionsDao questionDAO = new QuestionsDao();
        boolean saved = questionDAO.save(newQuestion);
        
        // Send response to client
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (saved) {
            out.println("<h2>Question saved successfully!</h2>");
        } else {
            out.println("<h2>Failed to save question. Please try again.</h2>");
        }
    }

}
