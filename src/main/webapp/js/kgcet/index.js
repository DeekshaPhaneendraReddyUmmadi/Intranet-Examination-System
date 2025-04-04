function printApplication() {
    // Retrieve form data from localStorage
    const storedData = localStorage.getItem("registrationData");

    if (storedData) {
        // Convert JSON string to object
        const formData = JSON.parse(storedData);

        // Create an HTML table
        const table = `<table border="1">
                                <tr>
                                    <th>Field</th>
                                    <th>Value</th>
                                </tr>
                                ${Object.entries(formData)
                .map(
                    ([key, value]) =>
                        `<tr><td>${key}</td><td>${value}</td></tr>`
                )
                .join("")}
                            </table>`;

        // Generate a downloadable file (e.g., an HTML file)
        const blob = new Blob(
            [
                `<html><head><title>Application Data</title></head><body>${table}</body></html>`,
            ],
            { type: "text/html" }
        );
        const url = window.URL.createObjectURL(blob);

        // Create a link and trigger a click event to download the file
        const link = document.createElement("a");
        link.href = url;
        link.download = "application_data.html";
        document.body.appendChild(link);
        link.click();

        // Cleanup
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
    } else {
        alert(
            "No registration data found. Please submit the registration form first."
        );
    }
}