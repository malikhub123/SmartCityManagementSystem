<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%
    if (session == null || !"user".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head><title>User Dashboard</title></head>
<body>
<h2>Welcome, <%= session.getAttribute("name") %>!</h2>

<ul>
    <li><a href="registerComplaint.jsp">Register Complaint</a></li>
    <li><a href="trackComplaint.jsp">Track Your Complaint</a></li>
    <li><a href="logout.jsp">Logout</a></li>
</ul>

</body>
</html>
