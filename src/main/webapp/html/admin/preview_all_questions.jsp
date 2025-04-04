<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="in.ac.ksrmce.config.admin_config.Authenticate" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsEntity" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsDao" %>	
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Preview Questions</title>
    <link rel="stylesheet" type="text/css" href="../../css/admin/preview_all_questions.css">
</head>
<body>

<%	boolean result = Authenticate.getAuthTrue();

	if (result) { %>
        <% List<QuestionsEntity> questions = QuestionsDao.listquestions(); %>

	<table>
        <tr>
            <th>Question ID</th>
            <th>Subject</th>
            <th>Question Image</th>
            <th>Option 1</th>
            <th>Option 2</th>
            <th>Option 3</th>
            <th>Option 4</th>
            <th>Correct Option</th>
        </tr>
        <% if (questions != null) { %>
            <% for (QuestionsEntity q : questions) { %>
                <div>
                	<tr>
                	<td class="id"><%= q.getId() %></td>
                	<td class="id"><%= q.getSubject() %></td>
                    <td><img class="question" src="../../images/questions/<%= q.getQuestion() %>" alt="Question Image"></td>
                    <td><img src="../../images/questions/<%= q.getOption_one() %>" alt="Option 1"></td>
                    <td><img src="../../images/questions/<%= q.getOption_two() %>" alt="Option 2"></td>
                    <td><img src="../../images/questions/<%= q.getOption_three() %>" alt="Option 3"></td>
                    <td><img src="../../images/questions/<%= q.getOption_four() %>" alt="Option 4"></td>
                    <td class="correct"><%= q.getCorrect_option() %></td>
                	</tr>
                </div>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="5">No questions found in the session</td>
            </tr>
        <% } %>
    </table>
<% } else { %>
    <p>You are not logged in. Please <a href="/project/html/admin/admin.jsp">login</a> to access this page.</p>
<% } %>




</body>
</html>
			 
			
			

	<%-- <table border="1">

		<tr>
            <th>Question ID</th>
            <th>Question Image</th>
            <th>Option 1</th>
            <th>Option 2</th>
            <th>Option 3</th>
            <th>Option 4</th>
            <th>Correct Option</th>
        </tr>
	
	<%
		
	
		List<QuestionsEntity> questions = QuestionsDao.listquestions();
		for(QuestionsEntity q : questions){
			out.print("<tr>");
			out.print("<td>"+q.getId()+"</td>");
			out.print("<td><img href="+q.getQuestion()+"</td>");
			/* out.print("<td>" + + "</td>"); */
			out.print("<td>" + q.getOption_one()+ "</td>");
			out.print("<td>" + q.getOption_two()+ "</td>");
			out.print("<td>" + q.getOption_three()+ "</td>");
			out.print("<td>" + q.getOption_four()+ "</td>");
			out.print("<td>" + q.getCorrect_option()+ "</td>");
			out.print("</tr>");
		}
	%>

	</table> --%>

			
    
    
  <%--  <table border="1">
        <tr>
            <th>Question ID</th>
            <th>Question Image</th>
            <th>Option 1</th>
            <th>Option 2</th>
            <th>Option 3</th>
            <th>Option 4</th>
            <th>Correct Option</th>
        </tr>
        <% List<QuestionsEntity> questions = QuestionsDao.listquestions(); %>
        <c:set var="questions" value="questions"></c:set>
        <c:forEach var="question" items="${questions}">
        <div>
            Display the question
            Question ${question.id}
            <img src="${question.question}" alt="Question Image">
            <img src="${question.option_one}" alt="Option One Image">
            <img src="${question.option_two}" alt="Option Two Image">
            <img src="${question.option_three}" alt="Option Three Image">
            <img src="${question.option_four}" alt="Option Four Image">

            <p>Correct Option: ${question.correct_option}</p>
        </div>
        <hr>
    </c:forEach>
         
    </table>--%>
    

