
function validateForm() {
    const form = document.getElementById("complaintForm");
    const phone = form.phone.value.trim();

    // Validate phone number (10 digits)
    const phonePattern = /^[0-9]{10}$/;
    if (!phonePattern.test(phone)) {
        alert("Please enter a valid 10-digit phone number.");
        form.phone.focus();
        return false;
    }

    // All other fields are handled by "required" in HTML
    alert("Form submitted successfully! (Dummy for now)");
    return false; // Prevents actual submission (for now)
}
