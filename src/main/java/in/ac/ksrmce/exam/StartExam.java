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

    static int totalQuestions = QuestionsDao.count() + 1;

    int[] buttons_color = new int[totalQuestions];

    boolean mO = false;
    boolean pO = false;
    boolean cO = false;

    List<QuestionsEntity> questions = null;

    boolean oneTime = true;
    boolean singleTime = true;
    boolean absolute = true;

    String sub = "maths";

    int mathsC = QuestionsDao.countQuestions("maths");
    int physicsC = QuestionsDao.countQuestions("physics");
    int chemistryC = QuestionsDao.countQuestions("chemistry");

    int[] mathsSelected = new int[mathsC];
    int[] physicsSelected = new int[physicsC];
    int[] chemistrySelected = new int[chemistryC];

    int[] btnmathsSelected = new int[mathsC];
    int[] btnphysicsSelected = new int[physicsC];
    int[] btnchemistrySelected = new int[chemistryC];

    String[] selectedOptions = new String[totalQuestions];

    int notVisited = QuestionsDao.count() - 1; // notVisited = 0
    int answered = 0; // answered = 2
    int notAnswered = 0; // notAnswered = 1
    int markForReview = 0; // markForReview = 3
    int answeredMarkForReview = 0; // answeredMarkForReview = 4
    int totalMarks = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int currentQuestionIndex = 0;
        int button = 0;

        HttpSession session = request.getSession();
        String reference_number = (String) session.getAttribute("reference_number");
        System.out.println("reference_number : " + reference_number);

        String selectedSubject = request.getParameter("subject");
        if (selectedSubject != null) {
            sub = selectedSubject;
            singleTime = true;
        }

        String questionIndexParam = request.getParameter("questionIndex");
        if (questionIndexParam != null && !questionIndexParam.isEmpty()) {
            currentQuestionIndex = Integer.parseInt(questionIndexParam);
        }

        if (currentQuestionIndex > 0) {
            button = Integer.parseInt(request.getParameter("button"));

            absolute = QuestionsDao.isTrue(reference_number);
        }

        String selectedOption = request.getParameter("option");
        int selectedOptionInt = 0;
        if (selectedOption != null) {
            selectedOptionInt = Integer.parseInt(selectedOption);
        } else if (selectedOption == null) {
            selectedOptionInt = 0;
        }

        if (currentQuestionIndex != 0) {
            mathsSelected[currentQuestionIndex - 1] = selectedOptionInt;
        }
        if (sub.equals("physics") && currentQuestionIndex != 0) {
            physicsSelected[currentQuestionIndex - 1] = selectedOptionInt;
        }
        if (sub.equals("chemistry") && currentQuestionIndex != 0) {
            chemistrySelected[currentQuestionIndex - 1] = selectedOptionInt;
        }

        // buttons_color[currentQuestionIndex] = 1;
        if (selectedOption != null && selectedOptions[currentQuestionIndex + 1] == null && button == 1) {
            answered++;
            notVisited--;

            if (sub.equals("maths")) {
                btnmathsSelected[currentQuestionIndex - 1] = 2;
            } else if (sub.equals("physics") && currentQuestionIndex != 0) {
                btnphysicsSelected[currentQuestionIndex - 1] = 2;
            } else if (sub.equals("chemistry") && currentQuestionIndex != 0) {
                btnchemistrySelected[currentQuestionIndex - 1] = 2;
            }

        } else if (selectedOption == null && currentQuestionIndex != 0 && button == 1) {
            notAnswered++;
            notVisited--;
            if (sub.equals("maths")) {
                btnmathsSelected[currentQuestionIndex - 1] = 1;
            } else if (sub.equals("physics") && currentQuestionIndex != 0) {
                btnphysicsSelected[currentQuestionIndex - 1] = 1;
            } else if (sub.equals("chemistry") && currentQuestionIndex != 0) {
                btnchemistrySelected[currentQuestionIndex - 1] = 1;
            }
        } else if (selectedOption != null && selectedOptions[currentQuestionIndex + 1] == null && button == 2) {
            answeredMarkForReview++;
            notVisited--;

            if (sub.equals("maths")) {
                btnmathsSelected[currentQuestionIndex - 1] = 4;
            } else if (sub.equals("physics") && currentQuestionIndex != 0) {
                btnphysicsSelected[currentQuestionIndex - 1] = 4;
            } else if (sub.equals("chemistry") && currentQuestionIndex != 0) {
                btnchemistrySelected[currentQuestionIndex - 1] = 4;
            }

        } else if (selectedOption == null && currentQuestionIndex != 0 && button == 2) {
            buttons_color[currentQuestionIndex] = 1;
            markForReview++;
            notVisited--;
            if (sub.equals("maths")) {
                btnmathsSelected[currentQuestionIndex - 1] = 3;
            } else if (sub.equals("physics") && currentQuestionIndex != 0) {
                btnphysicsSelected[currentQuestionIndex - 1] = 3;
            } else if (sub.equals("chemistry") && currentQuestionIndex != 0) {
                btnchemistrySelected[currentQuestionIndex - 1] = 3;
            }
        }

        // store the selected answers and button colors
        // if (currentQuestionIndex == QuestionsDao.countQuestions(sub) ||
        // currentQuestionIndex == (QuestionsDao.countQuestions(sub)/2)) {
        String array = "[ ]";
        String btnColor = "";
        if (sub.equals("maths")) {
            array = Arrays.toString(mathsSelected);
            btnColor = Arrays.toString(btnmathsSelected);
        } else if (sub.equals("physics")) {
            array = Arrays.toString(physicsSelected);
            btnColor = Arrays.toString(btnphysicsSelected);
        } else if (sub.equals("chemistry")) {
            array = Arrays.toString(chemistrySelected);
            btnColor = Arrays.toString(btnchemistrySelected);
        }
        QuestionsDao.saveMarks(reference_number, array, sub, btnColor);
        // }

        if (sub.equals("chemistry") && currentQuestionIndex == chemistryC) {
            pO = false;
        }
        if (sub.equals("physics") && currentQuestionIndex == physicsC && mO) {
            pO = true;
        }

        // without selecting subject
        if (mathsC == currentQuestionIndex) {
            sub = "physics";
            mO = true;
            oneTime = true;
            singleTime = true;
            currentQuestionIndex = 0;
        } else if (physicsC == currentQuestionIndex && mO && pO) {
            sub = "chemistry";
            oneTime = true;
            singleTime = true;
            currentQuestionIndex = 0;
        }

        if (singleTime) {
            questions = QuestionsDao.listrandomQuestions(sub, reference_number);
            singleTime = false;
        }

        if (absolute) {
            System.out.println("absolute");
            for (int i = 0; i < btnmathsSelected.length; i++) {
                btnmathsSelected[i] = 0;
            }
            for (int i = 0; i < btnphysicsSelected.length; i++) {
                btnphysicsSelected[i] = 0;
            }
            for (int i = 0; i < btnchemistrySelected.length; i++) {
                btnchemistrySelected[i] = 0;
            }

            btnmathsSelected = QuestionsDao.subjectColorOptionsGet("maths", reference_number);
            mathsSelected = QuestionsDao.subjectOptionsGet("maths", reference_number);

            btnphysicsSelected = QuestionsDao.subjectColorOptionsGet("physics", reference_number);
            physicsSelected = QuestionsDao.subjectOptionsGet("physics", reference_number);

            btnchemistrySelected = QuestionsDao.subjectColorOptionsGet("chemistry", reference_number);
            chemistrySelected = QuestionsDao.subjectOptionsGet("chemistry", reference_number);
            System.out.println("absolute");
        }

        String selectedQuestion = request.getParameter("num");
        if (selectedQuestion != null) {
            currentQuestionIndex = Integer.parseInt(selectedQuestion);
        }

        if (sub.equals("chemistry") && currentQuestionIndex == chemistryC) {
            currentQuestionIndex = chemistryC - 1;
        }

        // setting the next question and selected question
        if (currentQuestionIndex >= 0 && currentQuestionIndex <= mathsC) {
            QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);

            currentQuestion.setId(currentQuestion.getId());
            currentQuestion.setQuestion(currentQuestion.getQuestion());
            currentQuestion.setOption_one(currentQuestion.getOption_one());
            currentQuestion.setOption_two(currentQuestion.getOption_two());
            currentQuestion.setOption_three(currentQuestion.getOption_three());
            currentQuestion.setOption_four(currentQuestion.getOption_four());
            request.setAttribute("question", currentQuestion);
        }

        if (currentQuestionIndex == 0) {
            answered = 0;
        }

        if (oneTime) {
            notAnswered++;
            oneTime = false;
        } else if (currentQuestionIndex == QuestionsDao.countQuestions(sub)) {
            notAnswered--;
            oneTime = true;
        }

        String s = null;
        String subjectOnWeb = null;
        if (sub.equals("maths")) {
            subjectOnWeb = "Mathematics";
            s = Integer.toString(mathsSelected[currentQuestionIndex]);
        } else if (sub.equals("physics")) {
            subjectOnWeb = "Physics";
            s = Integer.toString(physicsSelected[currentQuestionIndex]);
        } else if (sub.equals("chemistry")) {
            subjectOnWeb = "Chemistry";
            s = Integer.toString(chemistrySelected[currentQuestionIndex]);
        }

        if (sub.equals("maths") && btnmathsSelected[currentQuestionIndex] == 0) {
            btnmathsSelected[currentQuestionIndex] = 1;
        } else if (sub.equals("physics") && btnphysicsSelected[currentQuestionIndex] == 0) {
            btnphysicsSelected[currentQuestionIndex] = 1;
        } else if (sub.equals("chemistry") && btnchemistrySelected[currentQuestionIndex] == 0) {
            btnchemistrySelected[currentQuestionIndex] = 1;
        }

        if (sub.equals("maths")) {
            int[] questionsLeft = new int[5];
            questionsLeft = numCount(btnmathsSelected);
            answered = questionsLeft[2];
            notAnswered = questionsLeft[1];
            notVisited = questionsLeft[0];
            markForReview = questionsLeft[3];
            answeredMarkForReview = questionsLeft[4];
        } else if (sub.equals("physics")) {
            int[] questionsLeft = new int[5];
            questionsLeft = numCount(btnphysicsSelected);
            answered = questionsLeft[2];
            notAnswered = questionsLeft[1];
            notVisited = questionsLeft[0];
            markForReview = questionsLeft[3];
            answeredMarkForReview = questionsLeft[4];
        } else if (sub.equals("chemistry")) {
            int[] questionsLeft = new int[5];
            questionsLeft = numCount(btnchemistrySelected);
            answered = questionsLeft[2];
            notAnswered = questionsLeft[1];
            notVisited = questionsLeft[0];
            markForReview = questionsLeft[3];
            answeredMarkForReview = questionsLeft[4];
        }

        if (sub.equals("maths")) {
            request.setAttribute("buttons_color", btnmathsSelected);
        } else if (sub.equals("physics")) {
            request.setAttribute("buttons_color", btnphysicsSelected);
        } else if (sub.equals("chemistry")) {
            request.setAttribute("buttons_color", btnchemistrySelected);
        }

        for (int b : buttons_color) {
            System.out.print(b + "  ");
        }
        System.out.print("\nmaths : ");
        for (int b : mathsSelected) {
            System.out.print(b + "  ");
        }
        System.out.print("\nphysics : ");
        for (int b : physicsSelected) {
            System.out.print(b + "  ");
        }
        System.out.print("\nchemistry : ");
        for (int b : chemistrySelected) {
            System.out.print(b + "  ");
        }

        System.out.print("\nmaths : ");
        for (int b : btnmathsSelected) {
            System.out.print(b + "  ");
        }
        System.out.print("\nphysics : ");
        for (int b : btnphysicsSelected) {
            System.out.print(b + "  ");
        }
        System.out.print("\nchemistry : ");
        for (int b : btnchemistrySelected) {
            System.out.print(b + "  ");
        }

        request.setAttribute("answered", answered);
        request.setAttribute("notAnswered", notAnswered);
        request.setAttribute("markForReview", markForReview);
        request.setAttribute("answeredMarkForReview", answeredMarkForReview);
        request.setAttribute("notVisited", notVisited);
        request.setAttribute("sub", sub);
        request.setAttribute("subjectOnWeb", subjectOnWeb);
        request.setAttribute("s", s);
        request.setAttribute("count", questions.size());
        request.setAttribute("currentQuestionIndex", currentQuestionIndex);

        String submit = request.getParameter("submitAll");
        if (submit != null && submit.equals("submit")) {
            Arrays.toString(mathsSelected);
            Arrays.toString(btnmathsSelected);
            Arrays.toString(physicsSelected);
            Arrays.toString(btnphysicsSelected);
            Arrays.toString(chemistrySelected);
            Arrays.toString(btnchemistrySelected);

            request.setAttribute("maths", numCount(btnmathsSelected));
            request.setAttribute("physics", numCount(btnphysicsSelected));
            request.setAttribute("chemistry", numCount(btnchemistrySelected));
            request.getRequestDispatcher("/html/student/summary.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/html/exam/quizTestingQuestioins.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    static int[] numCount(int[] nums) {

        int[] cth = new int[5];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cth[nums[i]]++;
            } else if (nums[i] == 1) {
                cth[nums[i]]++;
            } else if (nums[i] == 2) {
                cth[nums[i]]++;
            } else if (nums[i] == 3) {
                cth[nums[i]]++;
            } else if (nums[i] == 4) {
                cth[nums[i]]++;
            }
        }
        return cth;
    }
}
