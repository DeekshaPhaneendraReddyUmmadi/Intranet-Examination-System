<%@ page import ="in.ac.ksrmce.config.student_config.StudentDao" %>

<%@ page import="in.ac.ksrmce.config.admin_config.Authenticate" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"   />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  >
	<link href="../../css/admin/admin_home.css" rel="stylesheet">
</head>
<body>
<%	boolean result = Authenticate.getAuthTrue();
 

	if (result) { %>
        <h1>Welcome, <%= session.getAttribute("user_name") %></h1>
        <!-- Your home page content goes here --> 
        <%! StudentDao reg = new StudentDao(); %>
        <nav>
        	<a href="admin_home.jsp">HOME</a>
        	<a class='active' href="admin_candidates.php">CANDIDATES</a>
        	<a href="./add_questions.jsp">ADD QUESTIONS</a>
        	<a href="contact.html">CONTACT US</a>
    	</nav>

		<div class="dashboard">
    		<div class="card">
        		<h2>Total Registered Students</h2>
        		<% out.println(reg.count()); %>
    		</div>
    
		</div>
    <% } else { %>
        <p>You are not logged in. Please <a href="/project/html/admin/admin.jsp">login</a> to access this page.</p>
    <% } %>




</body>
</html>