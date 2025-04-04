<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start Exam</title>
</head>
<body>


<!-- <a href="quizTestingQuestioins.jsp" style="color:black; text-decoration:none;">Start Exam</a>
<a href="quizTestingQuestioins.jsp" target="_blank">Start Exam</a> -->


<br>
<br>
<!-- <button onclick="openExam()">Start Exam</button> -->


<!-- 
<a href="#" onclick="openExam()" id="startBtn">Start Exam</a><br>



<a href="#" onclick="openExamOne()" id="startBtnOne">Start Exam 0</a><br> -->




<form action="/examTestStart" >

	<input type="text" name="username"  placeholder="User Name"/>
	<input type="text" name="password" placeholder="Password"/>
	<input type="submit" />

</form>



<a href="<%= request.getContextPath()%>/examTestStart" >Start Exam 1</a>



<script src="../../js/exam/quiz.js"></script>
</body>
</html>