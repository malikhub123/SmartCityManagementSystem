package com.smartcity.cms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TrackComplaintServlet")
public class TrackComplaintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String DB_URL = "jdbc:mysql://localhost:3306/smartcitycms";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String complaintIdStr = request.getParameter("complaintId");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

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
        out.println("    max-width: 900px;");
        out.println("    width: 90%;");
        out.println("}");
        out.println("h2 {");
        out.println("    font-size: 3rem;");
        out.println("    font-weight: 700;");
        out.println("    margin-bottom: 2rem;");
        out.println("    text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.7);");
        out.println("}");
        out.println("p {");
        out.println("    font-size: 1.6rem;");
        out.println("    margin-bottom: 1rem;");
        out.println("    text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.5);");
        out.println("}");
        out.println(".field-label { font-weight: 600; }");
        out.println(".field-value { color: #f39c12; }");
        out.println(".status-pending { color: #f1c40f; }");
        out.println(".status-inprogress { color: #3498db; }");
        out.println(".status-resolved { color: #2ecc71; }");
        out.println(".not-found { color: #e74c3c; }");
        out.println(".button-container { margin-top: 2rem; }");
        out.println("a {");
        out.println("    display: inline-block;");
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
        out.println(".error-message {");
        out.println("    color: #e74c3c;");
        out.println("    font-weight: bold;");
        out.println("    text-shadow: none;");
        out.println("    font-size: 2.2rem;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='hero'></div>");
        out.println("<div class='container'>");

        if (complaintIdStr == null || complaintIdStr.trim().isEmpty()) {
            out.println("<div class='error-message'>Complaint ID is required!</div>");
            out.println("<div class='button-container'><a href='track.html'>Go back</a></div>");
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                String sql = "SELECT * FROM complaints WHERE custom_complaint_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, complaintIdStr.trim());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String status = rs.getString("status");
                    String statusClass = "";
                    if (status != null) {
                        switch (status.toLowerCase()) {
                            case "pending":
                                statusClass = "status-pending";
                                break;
                            case "in progress":
                                statusClass = "status-inprogress";
                                break;
                            case "resolved":
                                statusClass = "status-resolved";
                                break;
                            default:
                                statusClass = "";
                                break;
                        }
                    }

                    out.println("<h2>Complaint Details</h2>");
                    out.println("<p><span class='field-label'>Complaint ID:</span> <span class='field-value'>" + escapeHtml(rs.getString("custom_complaint_id")) + "</span></p>");
                    out.println("<p><span class='field-label'>Name:</span> <span class='field-value'>" + escapeHtml(rs.getString("name")) + "</span></p>");
                    out.println("<p><span class='field-label'>Email:</span> <span class='field-value'>" + escapeHtml(rs.getString("email")) + "</span></p>");
                    out.println("<p><span class='field-label'>Location:</span> <span class='field-value'>" + escapeHtml(rs.getString("location")) + "</span></p>");
                    out.println("<p><span class='field-label'>Description:</span> <span class='field-value'>" + escapeHtml(rs.getString("issue_description")) + "</span></p>");
                    out.println("<p><span class='field-label'>Status:</span> <span class='field-value " + statusClass + "'>" + escapeHtml(status) + "</span></p>");
                    out.println("<p><span class='field-label'>Date Submitted:</span> <span class='field-value'>" + rs.getTimestamp("created_at") + "</span></p>");
                } else {
                    out.println("<h2>Complaint Not Found</h2>");
                    out.println("<p class='not-found'>No complaint found with ID: " + escapeHtml(complaintIdStr) + "</p>");
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                out.println("<h2>Error Occurred</h2>");
                out.println("<p class='error-message'>There was an issue processing your request.</p>");
                out.println("<pre>" + escapeHtml(e.getMessage()) + "</pre>");
            }

            out.println("<div class='button-container'><a href='track.html'>Track Another Complaint</a></div>");
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
