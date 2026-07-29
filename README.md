<div align="center">
  <h1>Bus Ticket Booking System</h1>
  <p><i>A comprehensive, modern web application for managing and booking bus tickets effortlessly.</i></p>

  <!-- Badges -->
  <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E" alt="JavaScript" />
  <img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5" />
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3" />
</div>

<br/>

## Overview

The **Bus Ticket Booking System** is a full-stack web application designed to bridge the gap between passengers and bus operators. With a clean UI and robust backend architecture, it provides an intuitive booking experience for users and powerful management tools for administrators.

---

## Features at a Glance

### For Users (Passengers)
- **One-Click Login:** Secure authentication using Google OAuth2.
- **Search & Filter:** Find buses seamlessly by entering your `Source` and `Destination`.
- **Instant Booking:** Reserve seats in real-time with comprehensive passenger details.
- **Digital Receipts:** Get an aesthetically pleasing, detailed ticket receipt immediately after booking.
- **My Tickets:** A dedicated dashboard to track all your past and upcoming journeys.

### For Administrators
- **Secure Access:** Dedicated login portal (Demo: `admin` / `pasword123`).
- **Route Management:** Add new buses with complete details: Name, Route, Capacity, **Time**, and **Date**.
- **Live Editing:** Update bus schedules or modify seating capacities dynamically.
- **Fleet Control:** Remove inactive or canceled bus routes from the system instantly.

---

## Visual Showcase

<div align="center">
  <table>
    <tr>
      <td align="center" width="50%">
        <b>Login Portal</b><br/>
        <img src="images/Login%20page.png" style="border-radius: 8px; margin-top: 10px; width: 100%; max-width: 450px;"/>
      </td>
      <td align="center" width="50%">
        <b>Admin Fleet Overview</b><br/>
        <img src="images/Admin%20Dashboard.png" style="border-radius: 8px; margin-top: 10px; width: 100%; max-width: 450px;"/>
      </td>
    </tr>
    <tr>
      <td align="center" width="50%">
        <b>Admin Operations</b><br/>
        <img src="images/Admin%20Operation.png" style="border-radius: 8px; margin-top: 10px; width: 100%; max-width: 450px;"/>
      </td>
      <td align="center" width="50%">
        <b>User Dashboard</b><br/>
        <img src="images/User%20Page.png" style="border-radius: 8px; margin-top: 10px; width: 100%; max-width: 450px;"/>
      </td>
    </tr>
    <tr>
      <td align="center" width="50%">
        <b>My Tickets Dashboard</b><br/>
        <img src="images/My%20Tickets.png" style="border-radius: 8px; margin-top: 10px; width: 100%; max-width: 300px;"/>
      </td>
      <td align="center" width="50%">
        <b>Ticket Receipt</b><br/>
        <img src="images/Ticket.png" style="border-radius: 8px; margin-top: 10px; width: 100%; max-width: 300px;"/>
      </td>
    </tr>
  </table>
</div>

---

## Architecture

This project strictly adheres to the **Model-View-Controller (MVC)** design pattern. 

```mermaid
graph TD
    %% Frontend / View Layer
    subgraph View ["🖥️ VIEW (HTML/CSS/JS)"]
        UI_Home[Login Portal]
        UI_User[User Dashboard]
        UI_Admin[Admin Dashboard]
    end

    %% Controller Layer
    subgraph Controllers ["⚙️ CONTROLLER (Spring Boot)"]
        Ctrl_Auth[AuthController]
        Ctrl_Bus[BusController]
        Ctrl_Booking[BookingController]
    end

    %% Service Layer
    subgraph Services ["🛠️ SERVICE (Java)"]
        Svc_Bus[Bus Service]
        Svc_Book[Booking Service]
    end

    %% Model and Database Layer
    subgraph ModelDatabase ["🗄️ MODEL & DATABASE (MySQL/JPA)"]
        Entity[JPA Entities]
        DB_Users[(Users Table)]
        DB_Bus[(Bus Table)]
        DB_Booking[(Booking Table)]
    end

    %% Connections
    UI_Home -->|OAuth2 / Creds| Ctrl_Auth
    UI_User -->|Search & Book| Ctrl_Bus
    UI_User -->|My Tickets| Ctrl_Booking
    UI_Admin -->|CRUD Operations| Ctrl_Bus

    Ctrl_Auth --> Entity
    Ctrl_Bus --> Svc_Bus
    Ctrl_Booking --> Svc_Book

    Svc_Bus --> Entity
    Svc_Book --> Entity

    Entity --> DB_Users
    Entity --> DB_Bus
    Entity --> DB_Booking
```

---

## Database Schema

The core of the system is structured around three robust database tables to ensure data integrity and permanent record keeping.

### `users` Table
Manages authentication and user roles.

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | Integer | Primary Key (Auto-incremented) |
| `email` | String | Unique user email (Google or Admin) |
| `password` | String | Secured access password |
| `role` | Enum | Access level (`ADMIN` or `USER`) |
| `token` | String | JWT session token |

### `bus` Table
Contains the active fleet and scheduling data.

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | Integer | Primary Key (Auto-incremented) |
| `bus_name` | String | Travels/Operator Name |
| `source` | String | Starting location |
| `destination` | String | Arrival location |
| `seats` | Integer | Available seating capacity |
| `departure_time` | String | Scheduled time (e.g., 08:00 PM) |
| `departure_date` | String | Scheduled date (e.g., 2023-11-20) |

### `booking` Table
A permanent ledger of all tickets generated.

> [!NOTE]
> The booking table stores **snapshots** of the bus data (like time, date, and route). This ensures that even if an administrator deletes a bus from the active schedule, the user's ticket receipt remains completely intact.

| Column | Type | Description |
| :--- | :--- | :--- |
| `id` | Integer | Primary Key (Booking/Ticket ID) |
| `user_id` | Integer | Foreign Key to the `users` table |
| `bus_name` | String | Snapshot of the Operator Name |
| `source` | String | Snapshot of the Origin |
| `destination`| String | Snapshot of the Destination |
| `departure_time`| String | Snapshot of Departure Time |
| `departure_date`| String | Snapshot of Departure Date |
| `passenger_name`| String | Name of the travelling passenger |
| `mobile_number` | String | Passenger's contact number |
| `seats_booked`| Integer | Total seats reserved |

---


