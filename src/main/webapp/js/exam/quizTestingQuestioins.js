
/*const duration = 180 * 60 * 1000;
const startTime = new Date();
startTime.setHours(19); 
startTime.setMinutes(41);
startTime.setSeconds(0);

const startTimeInMillis = startTime.getTime();


const endTime = startTimeInMillis + duration;

const timerElement = document.getElementById('timer'); 

function updateTimer() {
    const currentTime = new Date().getTime();

    const remainingTime = endTime - currentTime;

    if (remainingTime <= 0) {
        clearTimeout(timerInterval);
        console.log('Timer expired!');
        return;
    }

    const minutes = Math.floor(remainingTime / (1000 * 60));
    const seconds = Math.floor((remainingTime % (1000 * 60)) / 1000);

    timerElement.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;

    timerInterval = setTimeout(updateTimer, 1000);
}

updateTimer();*/


const duration = 180 * 60 * 1000;
const startTime = new Date();
startTime.setHours(20);
startTime.setMinutes(15);
startTime.setSeconds(0);

const startTimeInMillis = startTime.getTime();

const endTime = startTimeInMillis + duration;

const timerElement = document.getElementById('timer');

function updateTimer() {
    const currentTime = new Date().getTime();
    const remainingTime = endTime - currentTime;

    if (remainingTime <= 0) {
        clearTimeout(timerInterval);
        console.log('Timer expired!');
        return;
    }

    const minutes = Math.floor(remainingTime / (1000 * 60));
    const seconds = Math.floor((remainingTime % (1000 * 60)) / 1000);

    timerElement.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;

    // Check if remaining time is 30 minutes or less and update timer color accordingly
    if (remainingTime <= 30 * 60 * 1000) { // 30 minutes = 30 * 60 * 1000 milliseconds
        timerElement.style.color = 'red'; // Change color to red
    } else {
        timerElement.style.color = 'black'; // Reset color to default
    }

    timerInterval = setTimeout(updateTimer, 1000);
}

updateTimer();



function openExam() {
    var url = "quizTestingQuestioins.jsp"; // URL of the exam interface HTML file
    //var windowFeatures = "width=800,height=600,resizable=yes"; // Customize window features
    var windowFeatures = "width=" + window.screen.availWidth + ",height=" + window.screen.availHeight + ",resizable=no,toolbar=no,location=no,menubar=no,status=no";
    window.open(url, "_blank", windowFeatures);
}

function submitExam() {
        // Perform any final actions before submitting the exam form
    document.forms['quizForm'].submit();
}

var valueToSend = "";

document.getElementById('actualSaveAndNext').addEventListener('click', function() {
	valueToSend = "1"; 
    document.getElementById('saveAndNext').value = valueToSend; 
    document.getElementById('saveAndNext').click();
});


document.getElementById('actualMarkForReview').addEventListener('click', function() {
	valueToSend = "2"; 
    document.getElementById('markForReview').value = valueToSend; 
    document.getElementById('markForReview').click();
});


function clearResponse() {
    document.getElementById("quizForm").reset();
}


function openExam() {
    var url = "quizTestingQuestioins.jsp"; // URL of the exam interface HTML file
    //var windowFeatures = "width=800,height=600,resizable=yes"; // Customize window features
    var windowFeatures = "width=" + window.screen.availWidth + ",height=" + window.screen.availHeight + ",resizable=no,toolbar=no,location=no,menubar=no,status=no";
    window.open(url, "_blank", windowFeatures);
}

function submitExam() {
        // Perform any final actions before submitting the exam form
    document.forms['quizForm'].submit();
}


/*var selectedOptions = {};

function saveAndNext() {
    var currentQuestion = document.querySelector('.question-div:visible');
    var questionNumber = currentQuestion.id.substring(8); // Extract question number from the ID
    var selectedOption = currentQuestion.querySelector('input[name="option' + questionNumber + '"]:checked').value;
    selectedOptions[questionNumber] = selectedOption;
    
    // Hide the current question
    currentQuestion.style.display = 'none';
    
    // Show the next question or submit button
    var nextQuestionNumber = parseInt(questionNumber) + 1;
    var nextQuestion = document.getElementById('question' + nextQuestionNumber);
    if (nextQuestion) {
        nextQuestion.style.display = 'block';
    } else {
        document.getElementById('submitButton').style.display = 'block';
    }
    
    // Update the hidden input field with the selected options
    document.getElementById('selectedOptions').value = JSON.stringify(selectedOptions);
}

// Submit the form when the submit button is clicked
document.getElementById('submitButton').addEventListener('click', function() {
    document.getElementById('quizForm').submit();
});*/

/*
var currentQuestion = 1;
var totalQuestions = new QuestionsDao.count();

function saveAndNext() {
        // Save the selected option for the current question (you can add your logic here)
        // Hide the current question
	document.getElementById('question' + currentQuestion).style.display = 'none';

	console.log(document.getElementsByName('option'));

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

*/




    
    
    
/*     $(document).ready(function() {
        // Add a change event listener to all radio buttons with the name "option"
        $("input[name='option']").change(function() {
            // Get the value of the selected radio button
            var selectedOption = $("input[name='option']:checked").val();
            
            // Update the value of the hidden input field
            $("input[name='selectedOption']").val(selectedOption);
        });
    });*/

    
    
    
    function submitQuiz() {
            const form = document.getElementById('quizForm');
            const formData = new FormData(form);
            
            for (const pair of formData.entries()) {
                console.log(`Question: ${pair[0]}, Selected Option: ${pair[1]}`);
            }
        }