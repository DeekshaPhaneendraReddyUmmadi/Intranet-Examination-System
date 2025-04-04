<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Student Information Form</title>
</head>
<body style="text-align:center;">
<h2>Student Information Form</h2>
<form id="studentForm" action="<%= request.getContextPath()%>/studentRegistration" method="post" enctype="multipart/form-data">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="fatherName">Father's Name:</label>
    <input type="text" id="fatherName" name="fatherName" required><br><br>
    
    <label for="roll">Roll Number:</label>
    <input type="text" id="roll" name="roll" required><br><br>
        
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>
    
    <label for="mobile">Mobile:</label>
    <input type="text" id="mobile" name="mobile" required><br><br>
    
    <label for="gender">Gender:</label>
    <select id="gender" name="gender" required>
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
    </select><br><br>
    
    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" required><br><br>
    
    <label for="district">District:</label>
    <input type="text" id="district" name="district" required><br><br>
    
    <label for="photo">Student Photo:</label>
    <input type="file" id="photo" name="photo" accept="image/*" required><br><br>
    
    <label for="signature">Signature:</label>
    <input type="file" id="signature" name="signature" accept="image/*" required><br><br>
    
    <button type="submit">Submit</button>
</form>
</body>
</html>
