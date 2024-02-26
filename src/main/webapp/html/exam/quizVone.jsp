<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="QuizServlet" method="post">
    <img src="<%=  %>" alt="Question Image">
    <br>
    <label><input type="radio" name="option" value="1"><img src="../../images/questions/<%=  %>" alt="Option 1"></label><br>
    <label><input type="radio" name="option" value="2"><img src="../../images/questions/<%=  %>" alt="Option 2"></label><br>
    <label><input type="radio" name="option" value="3"><img src="../../images/questions/<%=  %>" alt="Option 3"></label><br>
    <label><input type="radio" name="option" value="4"><img src="../../images/questions/<%=  %>" alt="Option 4"></label><br>

    <br>
    <button type="submit" name="action" value="next">Next</button>
    <button type="submit" name="action" value="mark">Mark for Review</button>
    <button type="submit" name="action" value="clear">Clear Response</button>
    <button type="submit" name="action" value="save">Save and Next</button>
</form>



</body>
</html>