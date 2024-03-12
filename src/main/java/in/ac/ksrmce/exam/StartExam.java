package in.ac.ksrmce.exam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import in.ac.ksrmce.config.questions_config.QuestionsDao;
import in.ac.ksrmce.config.questions_config.QuestionsEntity;


 

@WebServlet("/examTestStart")
public class StartExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StartExam() {
        super();
    }
    

	static int totalQuestions = QuestionsDao.count()+1;
    String[] selectedOptions = new String[totalQuestions];
    int qNum = 0 ;
    int[] qNums = new int[totalQuestions];
    
    int[] buttons_color = new int[totalQuestions];
    
    boolean mO = false;
    boolean pO = false;
    boolean cO = false;
    
    static List<QuestionsEntity> questions = null;
    
    
    static boolean oneTime = true;
    static boolean singleTime = true;
   
    
    static String sub= "maths";
    
    static int mathsC = QuestionsDao.countQuestions("maths");
    static int physicsC = QuestionsDao.countQuestions("physics");
    static int chemistryC = QuestionsDao.countQuestions("chemistry");
    
    
    static int[] mathsSelected = new int[mathsC];
    static int[] physicsSelected = new int[physicsC];
    static int[] chemistrySelected = new int[chemistryC];

    static int[] btnmathsSelected = new int[mathsC];
    static int[] btnphysicsSelected = new int[physicsC];
    static int[] btnchemistrySelected = new int[chemistryC];
    
    static int notVisited = QuestionsDao.count()-1;				// notVisited = 0
    static int answered = 0;                                    // answered = 2
    static int notAnswered = 0;									// notAnswered = 1
    static int markForReview = 0;								// markForReview = 3
    static int answeredMarkForReview = 0;						// answeredMarkForReview = 4
    static int totalMarks = 0;									

    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int currentQuestionIndex = 0;
    	
		HttpSession session = request.getSession();
		String reference_number = (String)session.getAttribute("reference_number");
		
		String selectedSubject = request.getParameter("subject");
		if(selectedSubject != null) {
			sub = selectedSubject;
			singleTime = true;
		}

        String questionIndexParam = request.getParameter("questionIndex");
        if (questionIndexParam != null && !questionIndexParam.isEmpty()) {
            currentQuestionIndex = Integer.parseInt(questionIndexParam);
        }
				
		String selectedOption = request.getParameter("option");
        int selectedOptionInt = 0;
        if(selectedOption != null) {
        	selectedOptionInt = Integer.parseInt(selectedOption);
        }else if(selectedOption == null) {
        	selectedOptionInt = 0 ;
        }
		
        if(currentQuestionIndex != 0) {
//        	selectedOptions[currentQuestionIndex-1] = selectedOption;        	
//        	qNums[currentQuestionIndex-1] = qNum;     
        	
        	mathsSelected[currentQuestionIndex-1] = selectedOptionInt; 
        }
        
        if(sub == "physics") {
//        	selectedOptions[currentQuestionIndex-1 + mathsC] = selectedOption; 
//        	qNums[currentQuestionIndex-1 + mathsC] = qNum; 
        	
        	
        	physicsSelected[currentQuestionIndex-1] = selectedOptionInt; 
        }
        if(sub == "chemistry") {
//        	selectedOptions[currentQuestionIndex-1 + mathsC + physicsC] = selectedOption; 
//        	qNums[currentQuestionIndex-1 + mathsC + physicsC] = qNum; 
        	
        	chemistrySelected[currentQuestionIndex-1] = selectedOptionInt; 
        }
		
        
        
        if(currentQuestionIndex == QuestionsDao.countQuestions(sub)) {
        	String array = "[ ]";
        	if(sub == "maths") {
        		array =  Arrays.toString(mathsSelected);
        	}else if(sub == "physics") {
        		array =  Arrays.toString(physicsSelected);
        	}else if(sub == "chemistry") {
        		array =  Arrays.toString(chemistrySelected);
        	}
        	
        	QuestionsDao.saveMarks(reference_number,array , sub);
        }


        
        if(sub == "chemistry" && currentQuestionIndex == chemistryC) {
    		pO=false;
    	}
        if(sub == "physics" && currentQuestionIndex == physicsC && mO == true) {
        	pO=true;
        }
        
    	// without selecting subject
    	if( mathsC == currentQuestionIndex ) {
    		singleTime = true;
    		mO = true;
    		sub = "physics";
    		currentQuestionIndex = 0 ;
    		answered = 0;
    		oneTime = true;
    		notAnswered = 0;
    	}else if( physicsC ==  currentQuestionIndex && mO == true && pO == true) {
    		singleTime = true;
    		sub="chemistry";
    		currentQuestionIndex = 0 ;
    		answered = 0;
    		notAnswered = 0;
    		oneTime = true;
    	}
    	
        if(singleTime) {
            for(int i = 0 ; i < buttons_color.length ; i++) {
            	buttons_color[i]=0;
            }
        	questions = QuestionsDao.listrandomQuestions(sub,reference_number);
        	singleTime = false;
        }
        
        
        String selectedQuestion = request.getParameter("num");
        if(selectedQuestion != null) {
        	currentQuestionIndex = Integer.parseInt(selectedQuestion);
        }
        System.out.println("selectedQuestion : "+ selectedQuestion);
        System.out.println("currentQuestionIndex : "+ currentQuestionIndex);
        
        if(sub == "chemistry" && currentQuestionIndex == chemistryC) {
        	currentQuestionIndex = chemistryC - 1;
        }
        
        if (currentQuestionIndex >= 0 && currentQuestionIndex <= mathsC) {
        	QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);
 
            currentQuestion.setId(currentQuestion.getId());
            currentQuestion.setQuestion( currentQuestion.getQuestion());
            currentQuestion.setOption_one( currentQuestion.getOption_one());
            currentQuestion.setOption_two( currentQuestion.getOption_two());
            currentQuestion.setOption_three( currentQuestion.getOption_three());
            currentQuestion.setOption_four( currentQuestion.getOption_four());
            request.setAttribute("question", currentQuestion);
            
            qNum = currentQuestion.getId();
        }

        
        

        
//        System.out.print("questions [ ");
//        for(int q : qNums) {
//        	System.out.print(q+" ");
//        }System.out.println(" ]");
//        System.out.print("answers [ ");
//        for(String o : selectedOptions) {
//        	System.out.print(o+" ");
//        }System.out.println(" ]");
        
//        buttons_color[currentQuestionIndex] = 1;
//        buttons_color[currentQuestionIndex] = 1;
//        System.out.println("");
        
        
        
        
        for(int b : buttons_color) {
        	System.out.print(b+"  ");
        }
        System.out.print("\nmaths : ");
        for(int b : mathsSelected) {
        	System.out.print(b+"  ");
        }
        System.out.print("\nphysics : ");
        for(int b : physicsSelected) {
        	System.out.print(b+"  ");
        }
        System.out.print("\nchemistry : ");
        for(int b : chemistrySelected) {
        	System.out.print(b+"  ");
        }
        
        if(selectedOption != null && selectedOptions[currentQuestionIndex+1] == null) {
        	buttons_color[currentQuestionIndex] = 2 ;
        	totalMarks++;
        	answered++;
        	notVisited--;
        }else if(selectedOption == null && currentQuestionIndex!=0) {
        	buttons_color[currentQuestionIndex] = 1 ;
        	notAnswered++;
        	notVisited--;
        }
//        }else {
        	buttons_color[currentQuestionIndex] = 1;
//        }

        if(oneTime) {
        	notAnswered++;
        	oneTime = false;	
        }else if(currentQuestionIndex == QuestionsDao.countQuestions(sub)) {
        	notAnswered--;
        	oneTime=true;
        }
       
        request.setAttribute("answered", answered);
        request.setAttribute("notAnswered", notAnswered);
        request.setAttribute("markForReview", markForReview);
        request.setAttribute("answeredMarkForReview", answeredMarkForReview);
        request.setAttribute("sub", sub);
        request.setAttribute("buttons_color", buttons_color);
        request.setAttribute("count", questions.size());
        request.setAttribute("currentQuestionIndex", currentQuestionIndex);
        
        request.getRequestDispatcher("/html/exam/quizTestingQuestioins.jsp").forward(request, response);        	
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
