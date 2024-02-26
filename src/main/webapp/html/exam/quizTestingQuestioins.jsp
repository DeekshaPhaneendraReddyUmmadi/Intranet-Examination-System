<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="in.ac.ksrmce.config.admin_config.Authenticate" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsEntity" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsDao" %>	
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.Serializable" %>

 







<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>quiz</title>
    <!-- <link rel="stylesheet" href="./src/main/webapp/html/css/exam/quizTestingQuestioins.css" /> -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/exam/quizTestingQuestioins.css" />
   
  </head>
  <body>
    <div class="main-container">
      <div class="head">
        <p>KGCET Examination System</p>
        <div class="left">
          <a class="instruction-btn" href="#">P</a>
          <p style="font-size: 14px">Question Paper</p>
        </div>
        <div class="right">
          <a class="instruction-btn i" href="#">I</a>
          <p style="font-size: 14px">View Instruction</p>
        </div>
      </div>
      <div class="top">
        <div class="left">
          <div class="information">KGCET - ENGINEERING</div>
          <div class="information timer-sec">
            <div class="left">
              Section
            </div>
            <div class="right" >
                Time Left : <span id="timer" >0</span> minutes
            </div>
          </div>
          <div class="information subject">
            <div>
              <div> <a href="#">Maths</a> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
              <div> <a href="#">Physics</a> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
              <div style="border-right: 3px solid gray"> <a href="#">Chemistry</a> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
            </div>
        </div>
          <div class="question-type information">
            Question Type : Multiple Choice Questions
          </div>
        </div>
        <div class="right">
          <img src="<%= request.getContextPath() %>/images/exam/myphoto.jpg" alt="student image" />
          <div>Ummadi Deeksha Phaneendra Reddy</div>
	        </div>
	      </div>
	      <div class="middle">
        <div class="left">
          <p style="padding-top: 20px"></p>
          
<!-- ________________________________________________________________________________________________________________________ -->

<%
    int currentQuestionIndex = (Integer)request.getAttribute("currentQuestionIndex");
    int answered = (Integer)request.getAttribute("answered");
    int notAnswered = (Integer)request.getAttribute("notAnswered");
    int markForReview = (Integer)request.getAttribute("markForReview");
    int answeredMarkForReview = (Integer)request.getAttribute("answeredMarkForReview");
/* QuestionsEntity questions = (QuestionsEntity) request.getAttribute("question"); */
/* QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);  */
%>



<div class="questions-dis">

<h1>Question <%= currentQuestionIndex + 1 %></h1>
    <form class="quizForm" action="${pageContext.request.contextPath}/examTestStart" method="post">
    	<img style="width:50%" src="${question.question}" alt="Question <%= currentQuestionIndex + 1 %>">
        <input type="hidden" name="questionIndex" value="<%= currentQuestionIndex + 1 %>">
        <label>
        	<input type="radio" name="option" value="1">
        	<img style="width:11%" src="${question.option_one}" alt="Option 1">
        </label><br>
        <label>
        	<input type="radio" name="option" value="2">
        	<img style="width:11%" src="${question.option_two}" alt="Option 2">
        </label><br>
        <label>
        	<input type="radio" name="option" value="3">
        	<img style="width:11%" src="${question.option_three}" alt="Option 3">
        </label><br>
        <label>
        	<input type="radio" name="option" value="4">
        	<img style="width:11%" src="${question.option_four}" alt="Option 4">
        </label><br>
        <button type="submit" id="saveAndNext" style="display: none;">Save & Next</button>
	    
    </form>

</div>
    
<%-- <form action="<%= request.getContextPath()%>/examTestStart" id="quizForm" method="post">
    <fieldset class="question-div" id="question" >
        <legend>Question</legend>
        <img style="width:50%" src="${q.question}" alt="Question"><br>
        <label>
            <input type="radio" name="option" value="1">
            <img style="width:11%" src="${q.option_one}" alt="Option 1">
        </label><br>
        <label>
            <input type="radio" name="option" value="2">
            <img style="width:11%" src="${question.option_two}" alt="Option 2">
        </label><br>
        <label>
            <input type="radio" name="option" value="3">
            <img style="width:11%" src="${question.option_three}" alt="Option 3">
        </label><br>
        <label>
            <input type="radio" name="option" value="4">
            <img style="width:11%" src="${question.option_four}" alt="Option 4">
        </label>
    </fieldset>
    
    <!-- Hidden field to store the question number -->
    <input type="hidden" name="selectedOption" value="">
    
    <button type="submit">Submit</button>
</form> --%>




<!-- ________________________________________________________________________________________________________________________ -->          
          
        </div>

        <div class="right">
          <div class="top">

            <div style="display: flex;">
              <a href="#" style="text-decoration: none;">
                <div style="position: relative; display: inline-block; width: 50px; margin-bottom: -15px;">
                  <div style="border-bottom: 20px solid #53cb35; border-left: 15px solid transparent; border-right: 15px solid transparent;"></div>
                  <div style="height: 25px; background-color: #53cb35;"></div>
                  <div style="font-size: 24px; color: white; text-align: center; transform: translate(-0%, -130%);"><%= answered  %></div>
                </div>
              </a><p>Answered</p>
              <a href="#" style="text-decoration: none;">
                <div style="margin-top: 50px; position: relative; display: inline-block; margin-top: 60px;">
                  <div style="color: white; text-align: center; position: absolute; top: -50px; width: 50px; height: 22px; background-color: #ff6347;"></div>
                  <div style="margin-top: -28px; border-top: 20px solid #ff6347; border-left: 15px solid transparent; border-right: 15px solid transparent; height: 0; width: 21px;"></div>
                  <div style="font-size: 24px; color: white; text-align: center; transform: translate(-0%, -130%);"><%= notAnswered  %></div>
                </div>
              </a><p>Not Answered</p>
            </div>

            <div style="display: flex;">
              <a  style="text-decoration: none;">
                <div  style="display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 4px; background-color: rgb(250, 248, 248);">
                  <div  style="text-align: center; font-size: 24px;">
                    <p ><% out.print(QuestionsDao.count() - (currentQuestionIndex +1)); %></p>
                  </div>
                </div>
              </a><p>Not Visited</p>
              <a  style="text-decoration: none; margin:5px">
                <div style="display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div  style="text-align: center; font-size: 24px;">
                    <p  style="color: white;"><%= markForReview  %></p>
                  </div>
                </div>
              </a>
              <div style="width: 80px;">Marked for Review</div>
            </div>

            <div style="display: flex;">
              <a  href="#" style="text-decoration: none; display: inline-block; position: relative; none; margin:10px;">
                <div style="position: relative; display: flex; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 50%; background-color: rgba(122, 32, 186, 0.86);">
                  <div  style="text-align: center; font-size: 24px; color: white;">
                    <p><%= answeredMarkForReview  %></p>
                  </div>
                  <img src="<%= request.getContextPath() %>/images/exam/text.png" alt="" style="position: absolute; bottom: 0; right: 0; width: 18px; height: auto; border-radius: 50%;">
                </div>
              </a>
              <p style="color: black;">Answered & Marked for Review (will be considered for evaluation)</p>
            </div>

            <div class="middle">Subject</div>
            <div style="background-color: #bad3ed;">
              choose a question
            </div>
            </div>

          <div class="bottom " style="overflow-y: auto; height: 220px; margin-top:0px; ">
            
            <div class="coloring-btn" style="background-color: #bad3ed ; ">
           


<%
	int[] qNum = new int[QuestionsDao.count()];
	for(int i:qNum){
		qNum[i] = 0;
	}
	
    for (int i = 1; i <= QuestionsDao.count(); i++) {
        /* String questionId = "question" + i; */
        
%>

	<a href="${question.id}"  style="text-decoration: none; ">
		<div  style="display:inline-block; justify-content: center; align-items: center; width: 50px; height: 50px; border: 1px solid black; border-radius: 4px; background-color: rgb(250, 248, 248); margin:8px;">
        	<div  style="text-align: center; align-items:center; font-size: 22px;">
        		<p class="initial" style="margin-top:10px; color:black;"><%= i %></p>
            </div>
        </div>
    </a>
    
    
    
<%
    }
%>
       		
<a href="${question.id}" style="text-decoration: none;">
    <button style="width: 50px; height: 50px; border: 1px solid black; border-radius: 4px; background-color: rgb(250, 248, 248); margin: 8px;">
        <span style="font-size: 22px; color: black;">0</span>
    </button>
</a>

       
             
             
          </div>

            
          </div>

          

        </div>
      </div>
      <div class="bottom">
      	<div class="navigation left">
            <div class="btm-btn">
                <button type="button" class="btm-btn-left" >Mark for Review & Next</button>
                <button type="reset" class="btm-btn-left" onclick="clearResponse()">Clear Response</button>
            </div>
            <button type="button" id="actualSaveAndNext" class="save-next-button btm-btn-right" onclick="saveAndNext()">Save & Next</button>
            <form class="quizForm">
            	
            </form>
        </div>
    <!-- <div class="left">
        <a href="#" class="next-btn">Mark for Review & Next</a>
    </div> -->
        <div class="navigation right ">
            <!-- <button class="btm-btn-right" type="button" class="submit-button" onclick="submitExam()">Submit Exam</button> -->
            <a class="btm-btn-right" href="#" onclick="submitExam()"> SUBMIT </a>
        </div>
      </div>
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="<%= request.getContextPath() %>/js/exam/quizTestingQuestioins.js"></script>
   
  </body>
</html>
