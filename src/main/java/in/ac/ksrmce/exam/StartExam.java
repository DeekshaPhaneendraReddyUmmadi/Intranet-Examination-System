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
    }
    
    static int answered = 0;
    static int notAnswered = 0;
    static int notVisited = QuestionsDao.count()-1;	
    static int markForReview = 0;
    static int answeredMarkForReview = 0;
    static int totalMarks = 0;
    
    static boolean oneTime = true;
    
    static String sub = "maths";


	static int totalQuestions = QuestionsDao.count()+1;
    String[] selectedOptions = new String[totalQuestions];
    int qNum = 0 ;
    int[] qNums = new int[totalQuestions];
    static boolean over = false;
    
    int mathsC = QuestionsDao.countofsubject("maths");
    int physicsC = QuestionsDao.countofsubject("physics");
    int chemistryC = QuestionsDao.countofsubject("chemistry");
    
    boolean mO = false;
    boolean pO = false;
    boolean cO = false;
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int currentQuestionIndex = 0;
    	
        String questionIndexParam = request.getParameter("questionIndex");
        if (questionIndexParam != null && !questionIndexParam.isEmpty()) {
            currentQuestionIndex = Integer.parseInt(questionIndexParam);
        }
    	
    	
//    	sub = request.getParameter("subject");

        
    	if( mathsC == currentQuestionIndex ) {
    		mO = true;
    		sub = "physics";
    		currentQuestionIndex = 0 ;
    		answered = 0;
    		oneTime = true;
    		notAnswered = 0;
    	}else if( physicsC ==  currentQuestionIndex && mO == true) {
    		sub="chemistry";
    		currentQuestionIndex = 0 ;
    		answered = 0;
    		notAnswered = 0;
    		oneTime = true;
    	}
//    	if( QuestionsDao.countofsubject("physics") == currentQuestionIndex ) {
//    		sub = "chemistry";
//    		currentQuestionIndex = 0 ;
//    	}
    	
//    	System.out.println("currentQuestionsINdex : " + currentQuestionIndex);
//    	System.out.println("maths over : "+ (QuestionsDao.countofsubject("maths") == currentQuestionIndex));
//    	System.out.println("subject : " + sub);
//    	System.out.println(" maths : " + QuestionsDao.countofsubject("maths"));
//    	System.out.println(" physics : " + QuestionsDao.countofsubject("physics"));
//    	System.out.println(" chemistry : " + QuestionsDao.countofsubject("chemistry"));
//    	System.out.println("  m + p  : " + (QuestionsDao.countofsubject("maths") + QuestionsDao.countofsubject("physics")));
//    	System.out.println("  m + p + c  : " + (QuestionsDao.countofsubject("maths") + QuestionsDao.countofsubject("physics") + QuestionsDao.countofsubject("chemistry")));
    	
        List<QuestionsEntity> questions = QuestionsDao.randomquesionswithsubject(sub);
        
        
        
        if (currentQuestionIndex >= 0 && currentQuestionIndex < QuestionsDao.count()) {
        	QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);
            
            String imagePath = request.getContextPath() + "/images/questions/";
            currentQuestion.setId(currentQuestion.getId());
            currentQuestion.setQuestion(imagePath + currentQuestion.getQuestion());
            currentQuestion.setOption_one(imagePath + currentQuestion.getOption_one());
            currentQuestion.setOption_two(imagePath + currentQuestion.getOption_two());
            currentQuestion.setOption_three(imagePath + currentQuestion.getOption_three());
            currentQuestion.setOption_four(imagePath + currentQuestion.getOption_four());
            request.setAttribute("question", currentQuestion);
            
            qNum = currentQuestion.getId();
        } else {
            over = true;
        }

        request.setAttribute("currentQuestionIndex", currentQuestionIndex);

        String selectedOption = request.getParameter("option");
        
        
        
//        System.out.println("current question :  " + qNum);
//        System.out.println("selected option : " + selectedOption);
        if(currentQuestionIndex != 0) {
        	selectedOptions[currentQuestionIndex-1] = selectedOption;        	
        	qNums[currentQuestionIndex-1] = qNum;        	
        }
        
        if(sub == "physics") {
        	selectedOptions[currentQuestionIndex-1 + mathsC] = selectedOption; 
        	qNums[currentQuestionIndex-1 + mathsC] = qNum; 
        	
        }
        if(sub == "chemistry") {
        	selectedOptions[currentQuestionIndex-1 + mathsC + physicsC] = selectedOption; 
        	qNums[currentQuestionIndex-1 + mathsC + physicsC] = qNum; 
        }
        System.out.print("[ ");
        for(int q : qNums) {
        	System.out.print(q+" ");
        }System.out.print(" ]");
        System.out.println("[ ");
        for(String o : selectedOptions) {
        	System.out.print(o+" ");
        }System.out.println(" ]");
        
        if(selectedOption != null && selectedOptions[currentQuestionIndex+1] == null) {
        	System.out.println("in answered : ");
        	totalMarks++;
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
       
        request.setAttribute("answered", answered);
        request.setAttribute("notAnswered", notAnswered);
        request.setAttribute("markForReview", markForReview);
        request.setAttribute("answeredMarkForReview", answeredMarkForReview);
        request.setAttribute("sub", sub);
        
        if(over) {
        	answered = 0;
            notAnswered = 0;
            markForReview = 0;
            answeredMarkForReview = 0;
        	request.setAttribute("count", totalMarks);
            over=false;
            request.getRequestDispatcher("/html/exam/totalmarks.jsp").forward(request, response);
        }else {
        	request.getRequestDispatcher("/html/exam/quizTestingQuestioins.jsp").forward(request, response);        	
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
