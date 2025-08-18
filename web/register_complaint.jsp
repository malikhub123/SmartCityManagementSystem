<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String complaintId = "CMP" + System.currentTimeMillis(); // Unique complaint ID
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Complaint | Smart City CMS</title>
    <link rel="stylesheet" href="css/complaint.css">
</head>
<body>
    <div class="container">
        <h1>Register Your Complaint</h1>
        <p>Please fill in the details below to help us resolve your issue.</p>

        <form action="RegisterComplaintServlet" method="post" class="complaint-form">

            <label for="name">Full Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter your full name" required>

            <label for="email">Email Address:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="location">Location:</label>
            <input type="text" id="location" name="location" placeholder="Enter complaint location" required>

            <label for="issue">Issue Description:</label>
            <textarea id="issue" name="issue" rows="4" placeholder="Describe the issue in detail..." required></textarea>

            <input type="submit" value="Submit Complaint">
        </form>
    </div>
</body>
</html>
