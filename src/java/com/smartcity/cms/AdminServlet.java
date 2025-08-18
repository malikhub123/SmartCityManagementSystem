package com.smartcity.cms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/smartcitycms";
    private static final String JDBC_USER = "root"; // replace with your DB username
    private static final String JDBC_PASSWORD = "root"; // replace with your DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Handle fetching all complaints
        List<Complaint> complaints = getAllComplaints();
        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("admin_home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Handle status update
        String complaintId = request.getParameter("complaintId");
        String newStatus = request.getParameter("status");
        
        if (complaintId != null && newStatus != null) {
            updateComplaintStatus(complaintId, newStatus);
        }
        
        // Redirect back to admin home to refresh the list
        response.sendRedirect("AdminServlet");
    }
    
    private List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM complaints ORDER BY created_at DESC");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(rs.getString("complaint_id"));
                complaint.setName(rs.getString("name"));
                complaint.setEmail(rs.getString("email"));
                complaint.setLocation(rs.getString("location"));
                complaint.setIssueDescription(rs.getString("issue_description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setCreatedAt(rs.getTimestamp("created_at"));
                complaint.setCustomComplaintId(rs.getString("custom_complaint_id"));
                
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // In a production environment, you would want to handle this more gracefully
        }
        
        return complaints;
    }
    
    private void updateComplaintStatus(String complaintId, String newStatus) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE complaints SET status = ? WHERE complaint_id = ?")) {
            
            stmt.setString(1, newStatus);
            stmt.setString(2, complaintId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // In a production environment, you would want to handle this more gracefully
        }
    }
    
    // Inner class to represent a complaint
    public static class Complaint {
        private String complaintId;
        private String name;
        private String email;
        private String location;
        private String issueDescription;
        private String status;
        private java.sql.Timestamp createdAt;
        private String customComplaintId;
        
        // Getters and setters
        public String getComplaintId() { return complaintId; }
        public void setComplaintId(String complaintId) { this.complaintId = complaintId; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        
        public String getIssueDescription() { return issueDescription; }
        public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public java.sql.Timestamp getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
        
        public String getCustomComplaintId() { return customComplaintId; }
        public void setCustomComplaintId(String customComplaintId) { this.customComplaintId = customComplaintId; }
    }
}