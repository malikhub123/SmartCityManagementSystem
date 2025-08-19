https://github.com/user-attachments/assets/b0a499f0-f9ee-43ea-aac0-42968a76d7ec

# Smart City Complaint Management System

## 📌 Project Overview
```
The Smart City Complaint Management System is a web-based application developed using **Java (J2EE: JSP, Servlets)**, **MySQL**, **HTML/CSS/JavaScript**, and **JDBC**. This system aims to provide a centralized and efficient platform for citizens to **register**, **track**, and **monitor** public grievances related to infrastructure and civic issues in their city. It ensures transparency, accountability, and quick resolution of complaints.
```


## 🚀 Key Features
```
### 👤 User Module
- User Registration and Login
- Register complaints with category, location, and description
- Track complaint status using unique complaint ID
- View complaint history

### 🔧 Admin Module
- Admin login
- View all registered complaints
- Update the status of complaints (Pending, In Progress, Resolved)
```


## 🛠️ Technologies Used
```
| Technology      | Usage                            |
|----------------|----------------------------------|
| HTML/CSS        | Frontend Layout                  |
| JavaScript      | Client-side validation, interactivity |
| Java (JSP/Servlet) | Backend Logic                |
| MySQL           | Database                         |
| JDBC            | Database Connectivity            |
| Apache Tomcat   | Web Server Deployment            |
| NetBeans        | Development Environment          |

```


### 🔽 Prerequisites

```
- Java JDK 8 or above
- Apache Tomcat 9+
- MySQL Server
- NetBeans IDE (recommended)
```



 🔧 Setup Instructions

1. **Clone the Repository** bash
   git clone https://github.com/your-username/smart-city-complaint-system.git


2. **Open in NetBeans**

   * Open NetBeans IDE
   * File > Open Project > Navigate to the cloned folder

3. **Configure Database**

   * Create a database named `smartcity`
   * Import `smartcity.sql` file located in the project directory
   * Update DB credentials in `DBConnection.java`

4. **Deploy Project**

   * Right-click on the project > Run
   * Ensure Tomcat server is configured in NetBeans

## 🧩 Folder Structure

```
SmartCityCMS/
├── Web Pages/
│   ├── META-INF/
│   ├── WEB-INF/
│   ├── css/
│   │   ├── admin.css
│   │   ├── complaint.css
│   │   ├── login.css
│   │   ├── register.css
│   │   ├── style.css
│   │   └── track.css
│   ├── js/
│   ├── admin_home.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── register_complaint.jsp
│   ├── register_success.jsp
│   ├── track.html
│   ├── track_result.jsp
│   ├── userDashboard.jsp
│   └── user_home.jsp
├── Source Packages/
│   └── com.smartcity.cms/
│       ├── AdminServlet.java
│       ├── LoginServlet.java
│       ├── RegisterComplaintServlet.java
│       ├── RegisterServlet.java
│       └── TrackComplaintServlet.java
├── Remote Files/
├── Test Packages/
├── Libraries/
├── Test Libraries/
└── Configuration Files/
```



## 🎯 Future Enhancements
```
* Email/SMS Notification to users on status update
* OTP-based authentication
* Graphical dashboard for admin
* Integration with GIS/Map for location tagging
```


## 📩 Contact
```
Created by **Aditi Malik**
📧 Email: [malik2002.aditi@gmail.com](mailto:malik2002.aditi@gmail.com)
🔗 LinkedIn: [aditi-malik-43880a222](https://linkedin.com/in/aditi-malik-43880a222)
```
