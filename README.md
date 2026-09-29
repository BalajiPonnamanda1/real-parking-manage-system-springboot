# 🚗 GoPark - Smart Parking Management System

GoPark is a web-based parking management system built using Spring Boot and MySQL.
It allows customers to find and book available parking spaces and allows parking
owners to manage their parking locations, slots, prices, and bookings.

## 📌 Problem Statement

Finding a proper parking space in busy urban areas is difficult. Vehicles are
often parked in unauthorized or no-parking areas, which can cause traffic
congestion and parking fines.

GoPark provides a platform where parking owners can list their available
parking spaces and customers can search and book them.

## ✨ Features

### 👤 Customer

- Customer registration and login
- Search parking locations
- Search based on vehicle type
- View available parking slots
- View parking price
- Book a parking slot
- Vehicle number validation
- OTP-based parking entry validation
- Cancel booking
- View booking details
- Submit ratings and reviews

### 🏢 Parking Partner

- Partner registration and login
- Add parking locations
- Manage parking slots
- Add different vehicle types
- Set parking price per hour
- View available slots
- Manage bookings
- Manage profile

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- MySQL
- Thymeleaf
- HTML
- CSS
- JavaScript
- Maven
- Git & GitHub

## 🏗️ Project Architecture

The project follows a layered architecture:

Controller
   ↓
Service
   ↓
Repository
   ↓
Database

### Layers

- **Controller** - Handles HTTP requests and responses
- **Service** - Contains business logic
- **Repository** - Handles database operations
- **Entity** - Represents database tables
- **Thymeleaf** - Used for server-side HTML rendering

## 🗄️ Database

The application uses MySQL as the database.

Main entities include:

- Customer
- Partner
- Land
- Vehicle
- Booking
- Review

## 🚀 How to Run

### 1. Clone the repository

git clone <your-github-repository-url>

### 2. Open the project

Open the project in:

- Eclipse
- Spring Tool Suite
- IntelliJ IDEA

### 3. Configure MySQL

Create a MySQL database and update the database configuration in:

src/main/resources/application.properties

### 4. Build the project

mvn clean install

### 5. Run the application

mvn spring-boot:run

Or run the main Spring Boot application class from your IDE.

## 🔐 Security

The application includes authentication and validation mechanisms for
customer and parking partner operations.

## 📷 Screenshots

Screenshots of the application can be added here.

## 🔮 Future Enhancements

- Google Maps integration
- Online payment integration
- Email/SMS notifications
- JWT-based authentication
- Microservices architecture
- Cloud deployment
- Real-time parking availability

## 👨‍💻 Author

**Balaji Ponnamanda**

Java Developer | Spring Boot | MySQL
