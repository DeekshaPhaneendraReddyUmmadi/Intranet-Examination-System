<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results </title>
</head>
<body>

<%= request.getAttribute("count") %>

<% 

out.println(request.getAttribute("answered"));
out.println(request.getAttribute("notAnswered"));


%>

</body>
</html>