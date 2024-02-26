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





/*function startTimer() {
    let timeLeft = 10;
    const timerElement = document.getElementById("time");

    const timerInterval = setInterval(() => {
        timeLeft--;
        timerElement.textContent = timeLeft;

        if (timeLeft === 0) {
            clearInterval(timerInterval);
            submitQuiz(); // Automatically submit quiz when time is up
        }
    }, 1000);
}
*/
// Function to handle quiz submission
function submitQuiz() {
    // Implement logic to submit quiz (e.g., send selected options to server)
    console.log("Quiz submitted");
    // Add your submission logic here (e.g., AJAX request to send data to the server)
}

// Function to display next question
function displayNextQuestion() {
    // Implement logic to display the next question and options
    // For demonstration purposes, we'll just change the image source
    document.getElementById("question-image").src = "question2.jpg";
}

// Event listener for next button click
document.getElementById("next-button").addEventListener("click", displayNextQuestion);

// Event listener for submit button click
document.getElementById("submit-button").addEventListener("click", submitQuiz);

// Call startTimer() to start the timer
startTimer();