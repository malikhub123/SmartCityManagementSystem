package com.smartcity.cms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterComplaintServlet")
public class RegisterComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String DB_URL = "jdbc:mysql://localhost:3306/smartcitycms";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String issue = request.getParameter("issue");

        // ✅ Generate unique complaint ID on server side
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        String customComplaintId = "CMP-" + timestamp;

        // Start of the HTML page with embedded CSS for styling
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Complaint Status</title>");
        out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
        out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("/* Common styles for a modern, attractive look */");
        out.println("body {");
        out.println("    font-family: 'Poppins', sans-serif;");
        out.println("    line-height: 1.6;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("    color: #fff;");
        out.println("    background-color: #1a1a2e;");
        out.println("    min-height: 100vh;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    text-align: center;");
        out.println("    position: relative;");
        out.println("    overflow: hidden;");
        out.println("}");
        out.println("@keyframes pan-background {");
        out.println("    0% { background-position: 0% 50%; }");
        out.println("    50% { background-position: 100% 50%; }");
        out.println("    100% { background-position: 0% 50%; }");
        out.println("}");
        out.println(".hero {");
        out.println("    position: absolute;");
        out.println("    top: 0;");
        out.println("    left: 0;");
        out.println("    height: 100%;");
        out.println("    width: 100%;");
        out.println("    background-image: linear-gradient(270deg, #1f4068, #162447);");
        out.println("    background-size: 200% 200%;");
        out.println("    animation: pan-background 30s ease infinite;");
        out.println("    z-index: -1;");
        out.println("}");
        out.println(".container {");
        out.println("    position: relative;");
        out.println("    background: rgba(255, 255, 255, 0.1);");
        out.println("    backdrop-filter: blur(10px);");
        out.println("    border-radius: 20px;");
        out.println("    border: 1px solid rgba(255, 255, 255, 0.3);");
        out.println("    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);");
        out.println("    padding: 5rem;");
        out.println("    max-width: 750px;");
        out.println("    width: 90%;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("h2 {");
        out.println("    font-size: 3rem;");
        out.println("    font-weight: 700;");
        out.println("    margin-bottom: 1.5rem;");
        out.println("    text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.7);");
        out.println("}");
        out.println("p {");
        out.println("    font-size: 1.6rem;");
        out.println("    font-weight: 400;");
        out.println("    margin-bottom: 2rem;");
        out.println("    text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.5);");
        out.println("}");
        out.println(".success-icon { font-size: 5rem; color: #2ecc71; text-shadow: none; margin-bottom: 1rem; }");
        out.println(".error-icon { font-size: 5rem; color: #e74c3c; text-shadow: none; margin-bottom: 1rem; }");
        out.println(".highlight { color: #f39c12; font-weight: 600; }");
        out.println("a {");
        out.println("    display: inline-block;");
        out.println("    margin-top: 2rem;");
        out.println("    background-color: #3498db;");
        out.println("    color: #fff;");
        out.println("    border: none;");
        out.println("    padding: 1.2rem 3rem;");
        out.println("    font-size: 1.2rem;");
        out.println("    font-weight: 600;");
        out.println("    border-radius: 50px;");
        out.println("    text-decoration: none;");
        out.println("    transition: all 0.3s ease;");
        out.println("}");
        out.println("a:hover {");
        out.println("    background-color: #2980b9;");
        out.println("    transform: translateY(-2px);");
        out.println("}");
        out.println("pre {");
        out.println("    background: rgba(0, 0, 0, 0.2);");
        out.println("    padding: 1rem;");
        out.println("    border-radius: 10px;");
        out.println("    white-space: pre-wrap;");
        out.println("    text-align: left;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='hero'></div>");
        out.println("<div class='container'>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO complaints (name, email, location, issue_description, status, created_at, custom_complaint_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
                 )) {

                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, location);
                stmt.setString(4, issue);
                stmt.setString(5, "Pending");
                stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                stmt.setString(7, customComplaintId);

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    out.println("<div class='success-icon'>&#10004;</div>"); // Unicode checkmark
                    out.println("<h2>Complaint Registered Successfully!</h2>");
                    out.printf("<p>Thank you, <span class='highlight'>%s</span>, for raising your concern.</p>%n", escapeHtml(name));
                    out.printf("<p>Your Complaint ID is: <span class='highlight'>%s</span></p>%n", customComplaintId);
                    out.println("<a href='track.html'>Track Your Complaint</a>");
                } else {
                    out.println("<div class='error-icon'>&#10006;</div>"); // Unicode X mark
                    out.println("<h2>Complaint Registration Failed</h2>");
                    out.println("<p>An unexpected error occurred. Please try again later.</p>");
                }
            }

        } catch (Exception e) {
            out.println("<div class='error-icon'>&#10006;</div>"); // Unicode X mark
            out.println("<h2>Error Occurred</h2>");
            out.println("<p>There was an issue processing your request.</p>");
            out.println("<pre>" + escapeHtml(e.getMessage()) + "</pre>");
            e.printStackTrace();
        }

        out.println("</div></body></html>");
        out.close();
    }

    // Escape HTML to prevent XSS attacks
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;");
    }
}
