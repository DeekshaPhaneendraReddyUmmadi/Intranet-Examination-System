<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="in.ac.ksrmce.config.admin_config.Authenticate" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsEntity" %>
<%@ page import="in.ac.ksrmce.config.questions_config.QuestionsDao" %>	
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KGCET Student Exam</title>
    <style>
        /* Add some basic styling for better presentation */
        label {
            display: block;
            margin-bottom: 10px;
        }
        #timer {
            text-align: right;
            font-size: 18px;
            margin-top: 10px;
        }
        .question {
            font-size: 20px;
            margin-bottom: 15px;
            display: none;
        }
        fieldset {
            border: 2px solid #333; /* Adjust the border size and color as needed */
            padding: 10px;
            margin: 0;
        }
        #header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
            font-size: 24px;
        }
        
        
    </style>
</head>
<body>

<div id="header">
    KGCET Student Exam
</div>

<div id="timer">Time Left: <span id="time">180</span> seconds</div>











<%


Connection con=QuestionsDao.getConnection();
PreparedStatement ps=con.prepareStatement("SELECT * FROM questions ORDER BY RAND() LIMIT "+ QuestionsDao.count());
ResultSet result=ps.executeQuery();

if (result.next()) {
    int questionNumber = 1; // Counter variable for question numbers
    
    String imagePath = "../../images/questions/"; // Replace this with your actual image path

    // Retrieve the image filenames from the database
    String question = imagePath + result.getString("question");
    String option1ImagePath = imagePath + result.getString("option_one");
    String option2ImagePath = imagePath + result.getString("option_two");
    String option3ImagePath = imagePath + result.getString("option_three");
    String option4ImagePath = imagePath + result.getString("option_four");
    do {
%>
        <fieldset class="question" id="question<%= questionNumber %>" style="display: <%= (questionNumber == 1) ? "block" : "none" %>">
            <legend>Question <%= questionNumber %></legend>
            <img src="<%= question %>" alt="Question <%= questionNumber %>">
            <label>
                <input type="radio" name="option" value="1">
                <img src="<%= option1ImagePath %>" alt="Option 1">
            </label>
            <label>
                <input type="radio" name="option" value="2">
                <img src="<%= option2ImagePath %>" alt="Option 2">
            </label>
            <label>
                <input type="radio" name="option" value="3">
                <img src="<%= option3ImagePath %>" alt="Option 3">
            </label>
            <label>
                <input type="radio" name="option" value="4">
                <img src="<%= option4ImagePath %>" alt="Option 4">
            </label>
        </fieldset>
<%
        questionNumber++;
    } while (result.next());

    // Show both buttons initially (it will be adjusted in the script)
%>
    <div class="navigation">
    	
        <button type="reset">Reset</button>
        <button type="button" class="save-next-button" onclick="saveAndNext()">Save & Next</button>
        <button type="button" class="submit-button" onclick="submitExam()">Submit Exam</button>
    </div>
<%
} else {
    out.println("No questions found.");
}
result.close();
%>



















<script>
    var currentQuestion = 1;
    var totalQuestions = new QuestionsDao.count();

    function saveAndNext() {
        // Save the selected option for the current question (you can add your logic here)

        // Hide the current question
        document.getElementById('question' + currentQuestion).style.display = 'none';

        // Move to the next question
        currentQuestion++;

        // If all questions are answered, show the submit button
        if (currentQuestion > totalQuestions) {
            document.querySelector('.submit-button').style.display = 'block';
            document.querySelector('.save-next-button').style.display = 'true'; // Hide the "Save & Next" button
        } else {
            // Show the next question
            document.getElementById('question' + currentQuestion).style.display = 'block';
        }
    }

    function submitExam() {
        // Perform any final actions before submitting the exam form
        document.forms['examForm'].submit();
    }

    // Set the countdown time in seconds
   /*  var timeInSeconds = 60;

    function startCountdown() {
        var countdownElement = document.getElementById("countdown");

        function updateCountdown() {
            var minutes = Math.floor(timeInSeconds / 60);
            var seconds = timeInSeconds % 60;

            // Display the remaining time in the format MM:SS
            countdownElement.textContent = (minutes < 10 ? '0' : '') + minutes + ':' + (seconds < 10 ? '0' : '') + seconds;

            // Check if the time has run out
            if (timeInSeconds === 0) {
                alert("Time's up! Submit your exam.");
                submitExam(); // Submit the form when the timer reaches zero
            } else {
                timeInSeconds--;
                setTimeout(updateCountdown, 1000); // Update the countdown every second
            }
        }

        updateCountdown();
    } */
    
    
 // Set the duration in milliseconds
    const duration = 180 * 60 * 1000; // 180 minutes * 60 seconds * 1000 milliseconds

    // Get the current time
    const startTime = Date.now();

    // Calculate the end time
    const endTime = startTime + duration;

    // Update the timer every second
    const timerElement = document.getElementById('timer'); // Assuming you have an element with id 'timer' to display the timer

    function updateTimer() {
        // Get the current time
        const currentTime = Date.now();

        // Calculate the remaining time in milliseconds
        const remainingTime = endTime - currentTime;

        // Check if the timer has expired
        if (remainingTime <= 0) {
            // Timer expired, do something (e.g., submit form)
            clearTimeout(timerInterval);
            console.log('Timer expired!');
            return;
        }

        // Convert remaining time to minutes and seconds
        const minutes = Math.floor(remainingTime / (1000 * 60));
        const seconds = Math.floor((remainingTime % (1000 * 60)) / 1000);

        // Update the timer element with the remaining time
        timerElement.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;

        // Call updateTimer again after 1 second
        timerInterval = setTimeout(updateTimer, 1000);
    }

    // Call updateTimer initially to start the timer
    updateTimer();
    

    // Start the countdown when the page loads
    window.onload = startCountdown;
</script>

</body>
</html>