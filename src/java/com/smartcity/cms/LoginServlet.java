package com.smartcity.cms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("LoginServlet doPost() invoked");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartcitycms", "root", "root");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                String name = rs.getString("name");

                HttpSession session = request.getSession();
                session.setAttribute("name", name);
                session.setAttribute("email", email);
                session.setAttribute("role", role);

                // Debug
                System.out.println("Login successful. Role: " + role);

                // Role-based redirection
                if ("admin".equalsIgnoreCase(role)) {
                    response.sendRedirect("admin_home.jsp");  // create this page
                } else if ("user".equalsIgnoreCase(role)) {
                    response.sendRedirect("user_home.jsp");   // create this page
                } else {
                    // Default fallback
                    response.sendRedirect("home.jsp");
                }

            } else {
                request.setAttribute("error", "Invalid credentials. Please try again.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Server error. Try again later.");
        }
    }
}
