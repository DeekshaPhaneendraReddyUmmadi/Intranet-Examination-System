// Capture form submission
const registrationForm = document.getElementById('registrationForm');

registrationForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Prevents the form from submitting normally

    // Get form data
    const formData = new FormData(registrationForm);

    // Convert form data to JSON
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    // Store data in localStorage for later use
    localStorage.setItem('registrationData', JSON.stringify(jsonData));

    alert('Registration form submitted successfully!');
});