<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
    <link href="../../css/exam/exam.css">
    <!-- Add any necessary CSS -->
</head>
<body>
    <div id="quiz-container">
    <div id="timer">Time Left: <span id="time">10</span> seconds</div>
        
        <div id="question-container">
            <img id="question-image" src="question1.jpg" alt="Question Image">
        </div>
        <div id="options-container">

	            <input type="radio" id="option1" name="option" value="1">
	            <label for="option1"><img src="option1.jpg" alt="Option 1"></label><br>
	            
	            <input type="radio" id="option2" name="option" value="2">
	            <label for="option2"><img src="option1.jpg" alt="Option 2"></label><br>
            
	            <input type="radio" id="option3" name="option" value="3">
	            <label for="option3"><img src="option1.jpg" alt="Option 3"></label><br>
	            
	            <input type="radio" id="option1" name="option" value="4">
	            <label for="option4"><img src="option1.jpg" alt="Option 4"></label><br>
            
            <!-- Repeat for option 2, 3, and 4 -->
        </div>
        
        <label for="reset">Reset</label><br>
        <input type="reset" value="reset">
        <label for="next">Submit</label><br>
        <input type="submit" value="next">
        
    </div>
    <!-- Add any necessary scripts -->
    <script src="../../js/exam/exam.js"></script>
</body>
</html>
