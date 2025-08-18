<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Smart City Complaint Management System</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Header with logout button */
        .user-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #1976D2;
            color: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .user-header h2 {
            margin: 0;
            font-size: 22px;
        }
        
        .logout-btn {
            padding: 8px 16px;
            background-color: #E53935;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
            transition: background-color 0.3s;
        }
        
        .logout-btn:hover {
            background-color: #C62828;
        }
        
        /* Hero section adjustments */
        .hero {
            margin-top: 0;
        }
    </style>
</head>
<body>
    <!-- User Header with Logout -->
    <div class="user-header">
        <h2>Welcome, <%= session.getAttribute("name") %></h2>
        <a href="login.jsp" class="logout-btn">Logout</a>
    </div>
    
    <!-- Main Content -->
    <div class="hero">
        <div class="overlay">
            <div class="content">
                <h1>Smart City Complaint Portal</h1>
                <p>Report. Track. Resolve.<br>Your voice matters in building a smarter city.</p>
                <div class="buttons">
                    <a href="register_complaint.jsp" class="btn">Register Complaint</a>
                    <a href="track.html" class="btn btn-outline">Track Complaint</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>