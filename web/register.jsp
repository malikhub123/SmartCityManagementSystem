<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="hero"></div>
    <div class="container">
        <h2>Register</h2>
        <form action="RegisterServlet" method="post">
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" name="name" required placeholder="Enter name">
            </div>
            <div class="input-group">
                <i class="fas fa-envelope"></i>
                <input type="email" name="email" required placeholder="Enter email">
            </div>
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" name="password" required placeholder="Enter password">
            </div>
            <div class="input-group">
                <i class="fas fa-user-tag"></i>
                <select name="role">
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>
