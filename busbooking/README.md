# 🚌 Bus Ticket Booking System

Welcome to the **Bus Ticket Booking System**, a comprehensive web application built with Spring Boot (Java) and a vanilla JavaScript frontend. This system allows passengers to browse and book bus tickets seamlessly while giving administrators full control over bus schedules and routes.

---

## ✨ Key Features

### 🏠 Home Page (Login Portal)
The starting point of the application where users authenticate to access their respective dashboards:
- **Admin Login:** Admins can log in using dedicated credentials (`admin` / `pasword123`) or via Google OAuth2.
- **User Login:** Users log in securely with a single click using **Continue with Google** (OAuth2 Integration).

### 👤 User Dashboard
A dedicated space for regular passengers:
- **Browse Buses:** View all available buses, complete with routes, departure times, and travel dates.
- **Search Functionality:** Filter buses by specifying a `Source` and `Destination`.
- **Book Tickets:** Reserve seats on a selected bus by providing passenger details and mobile number.
- **Ticket Receipts:** Instantly receive a beautifully formatted ticket receipt upon successful booking.
- **My Tickets History:** A dedicated tab where users can view all their past and upcoming bookings, permanently linked to their account.

### 👨‍💼 Admin Dashboard
A robust management console for system administrators:
- **Add Buses:** Create new bus schedules by entering the Bus Name, Source, Destination, Seat Capacity, Departure Time, and Departure Date.
- **Edit Buses:** Update the details of existing buses (e.g., change the departure time or increase seats).
- **Delete Buses:** Remove canceled or outdated bus routes from the system.
- **Real-time View:** Instantly see all active buses currently available to users.

---

## 🏗️ MVC Architecture

This project strictly follows the **Model-View-Controller (MVC)** design pattern to separate business logic from the user interface:

### 1. View (Frontend)
Located in `src/main/resources/static/`, the presentation layer is built with HTML, CSS, and Vanilla JS.
- `index.html`: The central login portal.
- `user.html`: The dashboard for passengers to book and view tickets.
- `admin.html`: The control panel for administrators.

### 2. Controller (API Routing)
The REST Controllers act as the bridge between the frontend View and the backend Model/Services.
- `AuthController`: Handles admin credential verification and issues JWT tokens.
- `BusController`: Manages endpoints for fetching, creating, updating, and deleting buses.
- `BookingController`: Handles booking requests and retrieves the "My Tickets" history based on the logged-in user.

### 3. Model (Database & Entities)
Spring Data JPA Entities map Java objects directly to the MySQL database tables, while Service classes (`BusService`, `BookingService`) handle the core business logic, such as decrementing available seats when a booking is made.

---

## 🗄️ Database Tables (MySQL)

The database schema is designed to track users, available buses, and ticketing history efficiently.

### 1. `users` Table
Stores all authenticated personnel in the system.
- **`id`**: Primary Key (Auto-incremented)
- **`email`**: The user's Google Email or Admin Email (Unique)
- **`password`**: Stored securely (or in plain text for the demo admin)
- **`role`**: Defines permissions (`ADMIN` or `USER`)
- **`token`**: Stores the JWT token for session validation

### 2. `bus` Table
Stores all active bus routes and schedules.
- **`id`**: Primary Key (Auto-incremented)
- **`bus_name`**: Name of the travels (e.g., SETC, KPN Travels)
- **`source`**: Starting city
- **`destination`**: Arrival city
- **`seats`**: Number of currently available seats
- **`departure_time`**: Time of departure (e.g., 08:00 PM)
- **`departure_date`**: Date of travel (e.g., 2023-11-20)

### 3. `booking` Table
Stores all generated tickets. It contains snapshots of the bus data so tickets remain valid even if the original bus is deleted.
- **`id`**: Primary Key (Booking/Ticket ID)
- **`user_id`**: Foreign Key linking to the `users` table
- **`bus_id`**: Reference to the booked bus
- **`bus_name`**: Snapshot of the bus name
- **`source`**: Snapshot of the starting city
- **`destination`**: Snapshot of the arrival city
- **`departure_time`**: Snapshot of the departure time
- **`departure_date`**: Snapshot of the departure date
- **`passenger_name`**: Name of the person travelling
- **`mobile_number`**: Contact number for the ticket
- **`seats_booked`**: How many seats were reserved in this transaction
