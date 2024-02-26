<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
    <link href="../../css/admin/view_questions.css" rel="stylesheet">
</head>
<body>
    <div class="question">
        <h2>Question 1:</h2>
        <img src="question1.jpg" alt="Question 1">
    </div>
    <div class="options">
        <div class="option">
            <img src="option1.jpg" alt="Option 1" onclick="selectOption(1)">
        </div>
        <div class="option">
            <img src="option2.jpg" alt="Option 2" onclick="selectOption(2)">
        </div>
        <div class="option">
            <img src="option3.jpg" alt="Option 3" onclick="selectOption(3)">
        </div>
        <div class="option">
            <img src="option4.jpg" alt="Option 4" onclick="selectOption(4)">
        </div>
    </div>
    <button class="btn-next" onclick="nextQuestion()">Next</button>

    <script>
        function selectOption(option) {
            alert('Option ' + option + ' selected');
        }

        function nextQuestion() {
            // Logic to navigate to the next question
            // Redirect to the next question page or dynamically change the content
            // For demonstration purposes, simply alert the user
            alert('Navigating to next question...');
        }
    </script>
</body>
</html>
