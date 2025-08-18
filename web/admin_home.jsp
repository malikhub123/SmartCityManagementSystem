<%@page import="com.smartcity.cms.AdminServlet.Complaint"%>
<%@page import="java.util.List"%>
<%
// If accessed directly without going through servlet, redirect
if (request.getAttribute("complaints") == null) {
    response.sendRedirect("AdminServlet");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard - SmartCityCMS</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/admin.css"> <!-- Add this line if you created separate admin.css -->
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>SmartCityCMS Admin Dashboard</h1>
            <a href="login.jsp" class="logout-btn">Logout</a>
        </div>
        
        <h2 style="color: var(--primary-blue); margin-bottom: 20px;">Complaints Management</h2>
        
        <table class="complaints-table">
            <thead>
                <tr>
                    <th>Complaint ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Location</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
                    if (complaints != null && !complaints.isEmpty()) {
                        for (Complaint complaint : complaints) {
                            String statusClass = "";
                            switch(complaint.getStatus().toLowerCase()) {
                                case "pending": statusClass = "status-pending"; break;
                                case "in progress": statusClass = "status-in-progress"; break;
                                case "resolved": statusClass = "status-resolved"; break;
                            }
                %>
                <tr>
                    <td><%= complaint.getCustomComplaintId() %></td>
                    <td><%= complaint.getName() %></td>
                    <td><%= complaint.getEmail() %></td>
                    <td><%= complaint.getLocation() %></td>
                    <td><%= complaint.getIssueDescription() %></td>
                    <td><span class="status-badge <%= statusClass %>"><%= complaint.getStatus() %></span></td>
                    <td><%= complaint.getCreatedAt() %></td>
                    <td>
                        <form class="status-form" action="AdminServlet" method="POST">
                            <input type="hidden" name="complaintId" value="<%= complaint.getComplaintId() %>">
                            <select class="status-select" name="status">
                                <option value="Pending" <%= "Pending".equals(complaint.getStatus()) ? "selected" : "" %>>Pending</option>
                                <option value="In Progress" <%= "In Progress".equals(complaint.getStatus()) ? "selected" : "" %>>In Progress</option>
                                <option value="Resolved" <%= "Resolved".equals(complaint.getStatus()) ? "selected" : "" %>>Resolved</option>
                            </select>
                            <button type="submit" class="update-btn">Update</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" style="text-align: center; padding: 20px;">No complaints found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>