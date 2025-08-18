<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Success</title>
    <script>
        // Redirect after 3 seconds
        setTimeout(function () {
            window.location.href = "login.jsp";
        }, 3000);
    </script>
</head>
<body style="text-align: center; margin-top: 100px;">
    <h2 style="color: green;">${message}</h2>
    <p>You will be redirected to the login page shortly...</p>
</body>
</html>

