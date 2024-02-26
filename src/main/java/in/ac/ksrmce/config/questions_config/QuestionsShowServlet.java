package in.ac.ksrmce.config.questions_config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/quiz")
public class QuestionsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QuestionsShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       

		List<QuestionsEntity> question = QuestionsDao.listquestions();
		
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String question = resultSet.getString("question");
//                String optionOne = resultSet.getString("option_one");
//                String optionTwo = resultSet.getString("option_two");
//                String optionThree = resultSet.getString("option_three");
//                String optionFour = resultSet.getString("option_four");
//                
//                QuestionsEntity q = new QuestionsEntity(id,question, optionOne, optionTwo, optionThree, optionFour);
//                questionsEntity.add(q);
//            }
		
		// Display the first question
		for(QuestionsEntity q : question) {
			displayQuestion(out, q,request,response);
		}
		
		
    }

	private void displayQuestion(PrintWriter out, QuestionsEntity questionsEntity ,HttpServletRequest request, HttpServletResponse response) {
		
		out.println("<html><body>");
        out.println("<form action='QuizServlet' method='post'>");
        out.println("<p>" + questionsEntity.getQuestion() + "</p>");
        
        out.println("<label><input type='radio' name='option' value='1'><img src='" +request.getContextPath()+"\\src\\main\\webapp\\html\\admin\\preview_all_questions\\" + questionsEntity.getOption_one() + "' alt='Option 1'></label><br>");
        System.out.println(questionsEntity.getQuestion());
//        out.println("<input type='radio' name='option' value='1'>" + questionsEntity.getOption_one() + "<br>");
        out.println("<input type='radio' name='option' value='2'>" + questionsEntity.getOption_two() + "<br>");
        out.println("<input type='radio' name='option' value='3'>" + questionsEntity.getOption_three() + "<br>");
        out.println("<input type='radio' name='option' value='4'>" + questionsEntity.getOption_four() + "<br>");
        out.println("<input type='submit' value='Next'>");
        out.println("</form>");
        out.println("</body></html>");
	}



}
