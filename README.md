API Documentation :-
https://documenter.getpostman.com/view/36189347/2sAYBbcoRv

Crop-Monitoring system frontend :-
https://github.com/loshaniramsha/crop-monitoring-system-frontend.git

Description:

The Crop Monitoring System is developed using Spring Boot and secured with JWT authentication. This application aims to enhance agricultural management by providing real-time monitoring and efficient data handling. The backend is powered by Spring MVC, leveraging MySQL as the database and Hibernate for object-relational mapping (ORM). Asynchronous communication between the client and server is handled through AJAX (or Fetch API), ensuring a smooth user experience.
To optimize database interactions, the system uses Hibernate JPQL for precise and efficient query handling. Robust logging mechanisms are implemented throughout the application, adhering to best practices in logging levels to ensure maintainability, traceability, and error diagnosis. This system is designed to improve crop management, enabling farmers and stakeholders to make informed decisions based on accurate and timely data

Tech Stack :

Java-Spring Boot:
A robust framework for building scalable and maintainable enterprise-level applications, used for creating the backend with REST APIs and integrating JWT-based authentication for secure access.
Hibernate:
An ORM (Object-Relational Mapping) framework that simplifies database operations by mapping Java objects to relational database tables, enabling efficient query execution using JPQL.
MySQL:
A reliable and feature-rich relational database management system for storing and managing agricultural data, including crop details, monitoring metrics, and user information.
AJAX/Fetch API:
Used for asynchronous communication between the client and server, providing a seamless user experience by dynamically updating parts of the web application without reloading the entire page.
JWT Security:
Implements JSON Web Token (JWT) for secure authentication and authorization, ensuring data integrity and user confidentiality.
Spring MVC:
Provides a structured Model-View-Controller architecture for efficiently handling requests and responses, making the system scalable and maintainable.

Features :
1. User Authentication and Authorization
JWT Security: Ensures secure login and session management using JSON Web Tokens.
Role-based access control for different types of users (e.g., Admins, Farmers, Agronomists).
2. Crop Data Management
Add, update, and delete crop details.
Monitor real-time growth parameters like soil moisture, temperature, and humidity (if integrated with sensors).
Historical data storage for crop performance analysis.
3. Dashboard and Reporting
Visual dashboards showing key metrics (e.g., crop health, environmental conditions).
Generate reports for past and current crop monitoring data.
4. Notifications and Alerts
Real-time notifications for critical issues such as drought, pest infestations, or anomalies in crop conditions.
Automated alerts for scheduled irrigation or other crop care activities.
5. Data Visualization
Charts and graphs for tracking crop performance over time.
Integration of GIS (Geographic Information Systems) to display fields and crop layouts.
6. Database Management
Efficient handling of large datasets using MySQL.
Data backup and restore functionalities for disaster recovery.
7. Role-Based Dashboards
Farmer Dashboard: Overview of crop health, tips, and weather forecasts.
Admin Dashboard: Manage users, crops, and system configurations.
Agronomist Dashboard: Access to analytics and advanced crop recommendations.
8. Integration Capabilities
Support for IoT sensors to collect real-time data (optional integration).
SMS/email integration for notifications and alerts to users.
9. Search and Filtering
Advanced search and filtering options to quickly locate crops based on type, health status, or location.
10. Responsive UI
Mobile and desktop-friendly interface for ease of access anywhere, anytime.
11. Logging and Analytics
Detailed logging of user actions for system audits.
Analytics for understanding usage patterns and improving system performance.
12. Multi-Language Support (Optional)
Support for multiple languages to cater to diverse user bases.


