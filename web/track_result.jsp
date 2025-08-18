<%-- 
    Document   : track_result
    Created on : 14 Aug 2025, 7:24:17 pm
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3;
            padding: 20px;
        }
        .container {
            background: #fff;
            border-radius: 8px;
            padding: 25px;
            width: 600px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
            color: #004466;
        }
        .detail {
            margin: 10px 0;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .label {
            font-weight: bold;
            color: #333;
        }
        .value {
            margin-left: 10px;
            color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Complaint Details</h2>
        <div class="detail"><span class="label">ID:</span><span class="value">${id}</span></div>
        <div class="detail"><span class="label">Name:</span><span class="value">${name}</span></div>
        <div class="detail"><span class="label">Email:</span><span class="value">${email}</span></div>
        <div class="detail"><span class="label">Location:</span><span class="value">${location}</span></div>
        <div class="detail"><span class="label">Issue:</span><span class="value">${issue_description}</span></div>
        <div class="detail"><span class="label">Status:</span><span class="value">${status}</span></div>
        <div class="detail"><span class="label">Date Submitted:</span><span class="value">${date_submitted}</span></div>
    </div>
</body>
</html>

