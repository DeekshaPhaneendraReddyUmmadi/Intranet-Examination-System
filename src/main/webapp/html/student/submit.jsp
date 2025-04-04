<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit</title>

</head>
<body style="margin: auto;">

	<% 
			String name = (String)session.getAttribute("userName");
			String photo = (String)session.getAttribute("photo");
			
	%>
    <div class="top" style="border-bottom: 2px solid rgb(227, 225, 225);">

        <div style="height: 20%; width: 25%; margin-left: 75% ; display: flex; align-items: center;">
            <img src="<%= photo %>" alt="Student Image" style="height: 85% ; margin: 10px; border: 2px solid rgb(227, 225, 225);">
            <div style="padding-left: 20px; width: 80%; display: flex; height: 80%; align-items: center;"><%= name %></div>
        </div>

    </div>
    <div class="bottom">

            
            <p style=" padding-top: 100px;text-align: center; font-size: 20px; ">Dear Candidate. Thank you. Please note that, your Exam is about to be submitted.Click on 'Ok' to proceed further.</p>
			<p style="text-align: center; font-size: 20px;">Are you sure to submit the exam.</p>

            <div style="display: flex; align-items: center; justify-content: center;">
                <a href="student_login.jsp" style="height: 25px; width: 80px; border:1px solid rgb(227, 225, 225); border-radius: 4px;  color:black; text-align: center;  padding-top: 5px ;  text-decoration: none; margin-right: 5px; font-size: 18px;">Ok</a>
                <a href="project/html/student/summary.jsp" style="height: 25px; width: 80px; border:1px solid rgb(227, 225, 225); border-radius: 4px;  color:black; text-align: center; padding-top: 5px; text-decoration: none; margin-left: 5px; font-size: 18px;">Cancel</a>
            </div>


    </div>
</body>
</html>