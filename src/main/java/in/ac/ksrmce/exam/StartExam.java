package in.ac.ksrmce.exam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import in.ac.ksrmce.config.questions_config.QuestionsDao;
import in.ac.ksrmce.config.questions_config.QuestionsEntity;

@WebServlet("/examTestStart")
public class StartExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StartExam() {
        super();
        // TODO Auto-generated constructor stub
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<QuestionsEntity> questions = QuestionsDao.listquestions();
//        int totalQuestions = questions.size();
//        String[] selectedOptions = new String[totalQuestions]; // Array to store selected options
//        int i = 0;
//        
//        // Forward each question to the JSP page
//        for (QuestionsEntity question : questions) {
//            // Set the current question in the request scope
//        	
//        	QuestionsEntity q = new QuestionsEntity();
//        	String imagePath =request.getContextPath()+"/images/questions/";
//        	
//            q.setQuestion(imagePath+question.getQuestion());
//            q.setOption_one(imagePath+question.getOption_one());
//            q.setOption_two(imagePath+question.getOption_two());
//            q.setOption_three(imagePath+question.getOption_three());
//            q.setOption_four(imagePath+question.getOption_four());
//            request.setAttribute("question", q);
//            System.out.println(q);
//            System.out.println(i);
//            
//            // Forward the request to the JSP page
//            request.getRequestDispatcher("/html/exam/quizTestingQuestioins.jsp").forward(request, response);
//            
//            // Collect the selected option from the request parameter
//            String selectedOption = request.getParameter("option");
//            selectedOptions[i++] = selectedOption;
//        }
//        
//        // Now you have all the selected options in the selectedOptions array
//        // You can further process them as needed
//        for (String option : selectedOptions) {
//            System.out.println("Selected option: " + option);
//        }
//    }
    
    static int answered = 0;
    static int notAnswered = 0;
    static int notVisited = QuestionsDao.count()-1;	
    static int markForReview = 0;
    static int answeredMarkForReview = 0;
    static int totalMarks = 0;
    
    static boolean oneTime = true;
    
//    static int check = answered;
    
    static String sub = "maths";


	static int totalQuestions = QuestionsDao.count()+1;
    String[] selectedOptions = new String[totalQuestions];
    static boolean over = false;
    
      
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int currentQuestionIndex = 0;
    	
    	
    	// Check if there's a parameter indicating the current question index
        String questionIndexParam = request.getParameter("questionIndex");
        if (questionIndexParam != null && !questionIndexParam.isEmpty()) {
            // If the parameter is present, parse it to get the current question index
            currentQuestionIndex = Integer.parseInt(questionIndexParam);
        }
    	
    	
//    	sub = request.getParameter("subject");
    	
    	
//    	if(sub == null) {
//    		sub="maths";
//    	}
    	if( QuestionsDao.countofsubject("maths") == currentQuestionIndex) {
    		sub = "physics";
    		currentQuestionIndex = 0 ;
    	}
//    	if( QuestionsDao.countofsubject("physics") == currentQuestionIndex ) {
//    		sub = "chemistry";
//    		currentQuestionIndex = 0 ;
//    	}
    	
    	System.out.println("currentQuestionsINdex : " + currentQuestionIndex);
    	System.out.println("maths over : "+ (QuestionsDao.countofsubject("maths") == currentQuestionIndex));
    	System.out.println("subject : " + sub);
    	System.out.println(" maths : " + QuestionsDao.countofsubject("maths"));
    	System.out.println(" physics : " + QuestionsDao.countofsubject("physics"));
    	System.out.println(" chemistry : " + QuestionsDao.countofsubject("chemistry"));
    	System.out.println("  m + p  : " + (QuestionsDao.countofsubject("maths") + QuestionsDao.countofsubject("physics")));
    	System.out.println("  m + p + c  : " + (QuestionsDao.countofsubject("maths") + QuestionsDao.countofsubject("physics") + QuestionsDao.countofsubject("chemistry")));
    	
    	
//    	List<QuestionsEntity> questions = QuestionsDao.randomquesions(sub);
        List<QuestionsEntity> questions = QuestionsDao.randomquesionswithsubject(sub);
        
//        System.out.println("subject : "+sub);
        
//        int totalQuestions = questions.size();
//        String[] selectedOptions = new String[totalQuestions]; // Array to store selected options
//        int currentQuestionIndex = 0; // Keep track of the current question index
        
//        // Check if there's a parameter indicating the current question index
//        String questionIndexParam = request.getParameter("questionIndex");
//        if (questionIndexParam != null && !questionIndexParam.isEmpty()) {
//            // If the parameter is present, parse it to get the current question index
//            currentQuestionIndex = Integer.parseInt(questionIndexParam);
//        }
        
        // Set the current question in the request scope
//        QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);
//        
//        String imagePath = request.getContextPath() + "/images/questions/";
//        currentQuestion.setId(currentQuestion.getId());
//        currentQuestion.setQuestion(imagePath + currentQuestion.getQuestion());
//        currentQuestion.setOption_one(imagePath + currentQuestion.getOption_one());
//        currentQuestion.setOption_two(imagePath + currentQuestion.getOption_two());
//        currentQuestion.setOption_three(imagePath + currentQuestion.getOption_three());
//        currentQuestion.setOption_four(imagePath + currentQuestion.getOption_four());
//        request.setAttribute("question", currentQuestion);
        
        
       
        
     // Assuming 'questions' is your list
        if (currentQuestionIndex >= 0 && currentQuestionIndex < QuestionsDao.count()) {
        	QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);
//        	QuestionsEntity currentQuestion = questions.get(temp);
            
            String imagePath = request.getContextPath() + "/images/questions/";
            currentQuestion.setId(currentQuestion.getId());
            currentQuestion.setQuestion(imagePath + currentQuestion.getQuestion());
            currentQuestion.setOption_one(imagePath + currentQuestion.getOption_one());
            currentQuestion.setOption_two(imagePath + currentQuestion.getOption_two());
            currentQuestion.setOption_three(imagePath + currentQuestion.getOption_three());
            currentQuestion.setOption_four(imagePath + currentQuestion.getOption_four());
            request.setAttribute("question", currentQuestion);
        } else {
            over = true;
        }

        
        
    	
    	
    	
        
        
        
        
       
//        System.out.println(questions.size());
        
     // Forward the request to the JSP page along with the current question index as a request parameter
        request.setAttribute("currentQuestionIndex", currentQuestionIndex);
//        request.getRequestDispatcher("/html/exam/quizTestingQuestioins.jsp").forward(request, response);

        // Forward the request to the JSP page
        
        // Collect the selected option from the request parameter
        String selectedOption = request.getParameter("option");
        selectedOptions[currentQuestionIndex] = selectedOption;
        
        // Debugging output
//        System.out.println("Current Question Index: " + currentQuestionIndex);
//        System.out.println("Selected Option: " + selectedOption);
        
        
//        for (int i = 0; i < selectedOptions.length; i++) {
//            System.out.println("Selected option for question " + (i) + ": " + selectedOptions[i]);
//        }

//        System.out.println("markForReview : "+ request.getParameter("markForReview") );
//        System.out.println("not visited :  "+ notVisited);
        
        
        // Numbering			
        
        // && (notAnswered + notVisited) == QuestionsDao.count()-currentQuestionIndex
        
        
        if(selectedOption != null && selectedOptions[currentQuestionIndex+1] == null) {
        	answered++;
        	notVisited--;
        }else if(selectedOption == null && currentQuestionIndex!=0) {
        	notAnswered++;
        	notVisited--;
        }
        
        
        if(oneTime) {
        	notAnswered++;
        	oneTime = false;
        }else if(currentQuestionIndex == QuestionsDao.countofsubject(sub)) {
        	notAnswered--;
        	oneTime=true;
        }
        
        
//        System.out.println("A + NA + NV  :" + (answered+notAnswered + notVisited) +"  C- Ind "+ (QuestionsDao.count()-currentQuestionIndex));
//        System.out.println(" value "+ (notVisited+answered) );
        
//        System.out.print("[   NA : "+notAnswered);
//        System.out.print("   nv : "+ notVisited + "  ]\n");
//        System.out.println("a : "+answered);
//        System.out.println("current a index : "+ currentQuestionIndex);
//        System.out.println("");
        
        request.setAttribute("answered", answered);
        request.setAttribute("notAnswered", notAnswered);
        request.setAttribute("markForReview", markForReview);
        request.setAttribute("answeredMarkForReview", answeredMarkForReview);
        request.setAttribute("sub", sub);
        
        
//        int totalMarks = 0;
//        if(Integer.parseInt(selectedOption)   == currentQuestion.getCorrect_option()) {
//        	totalMarks++;
//        }
//        
////        System.out.println(totalQuestions);
//        if(currentQuestionIndex == totalQuestions-1) {
//        	request.setAttribute("count", totalMarks);
//        	request.getRequestDispatcher("/html/exam/totalmarks.jsp").forward(request, response);
////        	response.sendRedirect("/html/exam/totatlmarks.jsp");
//        }

//        if(currentQuestionIndex == totalQuestions-1) {
        if(over) {
        	answered = 0;
            notAnswered = 0;
            markForReview = 0;
            answeredMarkForReview = 0;
//        	request.setAttribute("count", totalMarks);
            over=false;
            request.getRequestDispatcher("/html/exam/totalmarks.jsp").forward(request, response);
        }else {
        	request.getRequestDispatcher("/html/exam/quizTestingQuestioins.jsp").forward(request, response);        	
        }
    }
    


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
