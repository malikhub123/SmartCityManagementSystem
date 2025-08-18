<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% 
    String logoutParam = request.getParameter("logout");
    if ("true".equals(logoutParam)) {
%>
    <div class="alert alert-success" style="background-color: #d4edda; color: #155724; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
        You have been successfully logged out.
    </div>
<%
    }
%>
<html>
<head>
    <title>User Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

    <div class="container">
        <h2>Login</h2>

        <%-- Show error if exists --%>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p class="error"><%= error %></p>
        <%
            }
        %>

        <form action="LoginServlet" method="post">
            <input type="email" name="email" required placeholder="Enter email">
            <input type="password" name="password" required placeholder="Enter password">
            <button type="submit">Login</button>
        </form>

        <p>Not registered? <a href="register.jsp">Register here</a></p>
    </div>

</body>
</html>
