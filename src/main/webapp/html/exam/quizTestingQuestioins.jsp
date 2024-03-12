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
                Time Left : <span id="timer" style="font-size:24px;">0</span> minutes
            </div>
          </div>
         <!--  <div class="information subject">
		    <div class="subs">
		        <form action="/yourServletURL" method="GET" >
		            <div class="sub"> <button type="submit" name="subject" value="Maths">Maths</button> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
		            <div class="sub"> <button type="submit" name="subject" value="Physics">Physics</button> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
		            <div class="sub" style="border-right: 3px solid gray"> <button type="submit" name="subject" value="Chemistry">Chemistry</button> <a class="instruction-btn i" style="color: white;" href="#">I</a></div>
		        </form>
		    </div>
		  </div> -->
		  
		  
		  <div class="information subject">
		    <div class="button-container">
		        <form action="${pageContext.request.contextPath}/examTestStart" method="GET">
			        <a href="${pageContext.request.contextPath}/examTestStart?subject=maths" style="text-decoration:none;" class="sub-btn">Maths</a> 
			        <a href="${pageContext.request.contextPath}/examTestStart?subject=physics" style="text-decoration:none;" class="sub-btn">Physics</a> 
			        <a href="${pageContext.request.contextPath}/examTestStart?subject=chemistry" style="text-decoration:none;" class="sub-btn">Chemistry</a> 
		           <!--  <button class="sub-btn" type="submit" name="subject" value="maths">Maths</button> 
		            <button class="sub-btn" type="submit" name="subject" value="physics">Physics</button>
		            <button class="sub-btn" type="submit" name="subject" value="chemistry">Chemistry</button> -->
		        </form>
	    	</div>
</div>
		  
<% 
String name = (String)session.getAttribute("userName");
String photo = (String)session.getAttribute("photo");

%>
          <div class="question-type information">
            Question Type : Multiple Choice Questions
          </div>
        </div>
	        <div class="right">
	          <img src="<%= photo %>" alt="student image" />
	          <div><%= name %></div>
		    </div>
	      </div>
	      <div class="middle">
        	<div class="left">
          		<p style="padding-top: 20px"></p>

				<%
				    int currentQuestionIndex = (Integer)request.getAttribute("currentQuestionIndex");
				    int answered = (Integer)request.getAttribute("answered");
				    int notAnswered = (Integer)request.getAttribute("notAnswered");
				    int markForReview = (Integer)request.getAttribute("markForReview");
				    int answeredMarkForReview = (Integer)request.getAttribute("answeredMarkForReview");
				    String sub = (String)request.getAttribute("sub");
				    
				/* QuestionsEntity questions = (QuestionsEntity) request.getAttribute("question"); */
				/* QuestionsEntity currentQuestion = questions.get(currentQuestionIndex);  */
				
					String path = request.getContextPath() + "/images/questions/";
				%>
	
	
	
				<div class="questions-dis">
				
				<h1>Question <%= currentQuestionIndex + 1 %></h1>
				    <form class="quizForm" id="quizForm" action="${pageContext.request.contextPath}/examTestStart" method="post">
				    	<img style="width:50%" src="<%=path %>${question.question}" alt="Question <%= currentQuestionIndex + 1 %>">
				        <input type="hidden" name="questionIndex" value="<%= currentQuestionIndex + 1 %>">
				        <label>
				        	<input type="radio" name="option" value="1">
				        	<img style="width:11%" src="<%=path %>${question.option_one}" alt="Option 1">
				        </label><br>
				        <label>
				        	<input type="radio" name="option" value="2">
				        	<img style="width:11%" src="<%=path %>${question.option_two}" alt="Option 2">
				        </label><br>
				        <label>
				        	<input type="radio" name="option" value="3">
				        	<img style="width:11%" src="<%=path %>${question.option_three}" alt="Option 3">
				        </label><br>
				        <label>
				        	<input type="radio" name="option" value="4">
				        	<img style="width:11%" src="<%=path %>${question.option_four}" alt="Option 4">
				        </label><br>
				        <button type="submit" name="subject" value="sub" style="display:none;"></button>
				        <button type="submit" id="saveAndNext" name="saveandnext" style="display: none;">Save & Next</button>
					    <button type="submit" id="markForReview" name="markforreview" value="markForReview" style="display:none;">Mark for Review</button>
				    </form>
				
				</div>
    
        	</div>

	        <div class="right">
	          <div class="top">
	
	            <div style="display: flex;">
	              <a href="#" class="answered-link"><%= answered  %></a><p>Answered</p>
	              <a href="#" class="notAnswered-link"><%= notAnswered  %></a><p>Not Answered</p>
	            </div>
	
	            <div style="display: flex;">
	              <a href="#" class="notVisited-link"><% out.print(QuestionsDao.countQuestions(sub) - (currentQuestionIndex +1)); %></a><p>Not Visited</p>
	              <a href="#" class="markedforreview-link"><%= markForReview  %></a><div style="width: 80px;">Marked for Review</div>
	            </div>
	
	            <div style="display: flex;">
	              <a href="#" class="answeredandmarkedforreview-link"><%= answeredMarkForReview  %></a>
	              <p style="color: black;width:78%;">Answered & Marked for Review (will be considered for evaluation)</p>
	            </div>
	
	            <div class="middle"><%= sub %></div>	
	            <div style="background-color: #bad3ed; padding-bottom:7px;">
	              choose a question
	            </div>
	            </div>

	          <div class="bottom " style="overflow-y: auto; min-height: 218px; margin-top:-25px; ">
	            <div class="coloring-btn" style="background-color: #bad3ed ; ">
	
					<%
					    int[] buttons_color = (int[]) request.getAttribute("buttons_color");
						int count =(int) request.getAttribute("count");
					    for (int i = 1; i < count+1; i++) {
					        String classCol = null;
					        if (buttons_color[i-1] == 0) {
					            classCol = "notVisited-link";
					        } else if (buttons_color[i-1] == 1 || (currentQuestionIndex+1) == i) {
					            classCol = "notanswered-link";    
					        } else if (buttons_color[i-1] == 2) {
					            classCol = "answered-link";    
					        } else if (buttons_color[i-1] == 3) {
					            classCol = "markedforreview-link";    
					        } else if (buttons_color[i-1] == 4) {
					            classCol = "answeredandmarkedforreview-link";    
					        }
					%>
					    <a href="${pageContext.request.contextPath}/examTestStart?num=<%= i-1 %>" class="<%= classCol %>"><%= i %></a>
					    
					<%
					    }
					%>
					
	          	</div>
	          </div>
	
	        </div>
      </div>
      <div class="bottom">
      	<div class="navigation left">
            <div class="btm-btn">
                <button type="button" id="actualMarkForReview" class="btm-btn-left" >Mark for Review & Next</button>
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
