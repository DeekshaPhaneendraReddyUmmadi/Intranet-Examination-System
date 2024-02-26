// Set the duration in milliseconds
const duration = 180 * 60 * 1000; // 180 minutes * 60 seconds * 1000 milliseconds

// Calculate the start time (3:00 PM)
const startTime = new Date();
startTime.setHours(19); // Set the hours to 15 for 3:00 PM
startTime.setMinutes(41);
startTime.setSeconds(0);

// Convert the start time to milliseconds since the Unix Epoch
const startTimeInMillis = startTime.getTime();

// Calculate the end time by adding the duration to the start time
const endTime = startTimeInMillis + duration;

// Update the timer every second
const timerElement = document.getElementById('timer'); // Assuming you have an element with id 'timer' to display the timer

function updateTimer() {
    // Get the current time
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

updateTimer();


function clearResponse() {
    document.getElementById("quizForm").reset();
}


function openExam() {
    var url = "quizTestDesign.jsp"; // URL of the exam interface HTML file
    //var windowFeatures = "width=800,height=600,resizable=yes"; // Customize window features
    var windowFeatures = "width=" + window.screen.availWidth + ",height=" + window.screen.availHeight + ",resizable=no,toolbar=no,location=no,menubar=no,status=no";
    window.open(url, "_blank", windowFeatures);
}

function openExamOne() {
    var url = "/project/examTestStart"; // URL of the exam interface HTML file
    //var windowFeatures = "width=800,height=600,resizable=yes"; // Customize window features
    var windowFeatures = "width=" + window.screen.availWidth + ",height=" + window.screen.availHeight + ",resizable=no,toolbar=no,location=no,menubar=no,status=no";
    window.open(url, "_blank", windowFeatures);
}


function saveAndNext() {
    // Save the selected option for the current question (you can add your logic here)
    
    // Submit the form to load the next question
    document.querySelector('.quizForm').submit();
}


function submitExam() {
        // Perform any final actions before submitting the exam form
    document.forms['quizForm'].submit();
}

/* 2 */
/*function saveAndNext() {
    // Save the selected option for the current question (you can add your logic here)

    // Remember the current scroll position
    var scrollPosition = window.pageYOffset || document.documentElement.scrollTop;

    // Hide the current question
    document.getElementById('question' + currentQuestion).style.display = 'none';

    // Move to the next question
    currentQuestion++;

    // If all questions are answered, show the submit button
    if (currentQuestion > totalQuestions) {
        document.querySelector('.submit-button').style.display = 'block';
        document.querySelector('.save-next-button').style.display = 'none'; // Hide the "Save & Next" button
    } else {
        // Show the next question
        document.getElementById('question' + currentQuestion).style.display = 'block';
    }

    // Restore the scroll position
    window.scrollTo(0, scrollPosition);
}*/
/*  
var currentQuestion = 1;
    var totalQuestions = new QuestionsDao.count();
  function saveAndNext() {
        // Save the selected option for the current question (you can add your logic here)
	scrollToTop();
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
       
    }*/
/*
 function scrollToTop() {
        window.scrollTo(0, 0); // Scroll to the top of the page
    }
*/

/*function getSelectedOption() {
    var optionElements = document.getElementsByName('option');
    
    for (var i = 0; i < optionElements.length; i++) {
        if (optionElements[i].checked) {
            return optionElements[i].value;
        }
    }
    
    return null; // If no option is selected
}*/
/*
function getCorrectOption(currentQuestion) {
    return questions[currentQuestion].correctOption;
}
*/
    function submitExam() {
        // Perform any final actions before submitting the exam form
        document.forms['quizForm'].submit();
    }