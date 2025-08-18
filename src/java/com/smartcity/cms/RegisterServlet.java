package com.smartcity.cms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println("Received - Name: " + name + ", Email: " + email + ", Role: " + role);

        if(name == null || email == null || password == null || role == null ||
           name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty()) {
            out.println("<h3 style='color:red;'>All fields are required!</h3>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/smartcitycms", "root", "root");

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password); // Optional: hash in real apps
            ps.setString(4, role);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Registration successful for: " + email);
                // Show success popup and then redirect using JavaScript
                out.println("<script type='text/javascript'>");
                out.println("alert('User registered successfully!');");
                out.println("window.location = 'login.jsp';");
                out.println("</script>");
            } else {
                System.out.println("Registration failed.");
                out.println("<h3 style='color:red;'>Registration failed. Please try again.</h3>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>An error occurred: " + e.getMessage() + "</h3>");
        }
    }
}
