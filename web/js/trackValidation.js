
function validateTrackForm() {
    const form = document.getElementById("trackForm");
    const complaintId = form.complaintId.value.trim();

    if (complaintId === "") {
        alert("Please enter a valid Complaint ID.");
        return false;
    }

    // Simulated dummy response
    document.getElementById("cID").innerText = complaintId;
    document.getElementById("cStatus").innerText = "In Progress";
    document.getElementById("cStatus").style.color = "orange";
    document.getElementById("statusResult").style.display = "block";

    return false; // Prevent actual form submission
}
