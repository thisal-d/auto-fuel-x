

# Auto Fuel X

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-10-purple.svg)](https://jakarta.ee/)
[![Maven](https://img.shields.io/badge/Maven-3.8%2B-red.svg)](https://maven.apache.org/)
![LOC](https://tokei.rs/b1/github/Thisal-D/Auto-Fuel-X?category=lines)

Auto Fuel X is a comprehensive web-based fuel station and vehicle service management system built with Jakarta EE (Servlet + JSP). This application streamlines operations for fuel stations by providing integrated tools for managing customers, vehicles, service bookings, fuel inventory, complaints, and employee roles.

## Table of Contents
- [Auto Fuel X](#auto-fuel-x)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
    - [Customer Management](#customer-management)
    - [Operations Management](#operations-management)
    - [Employee Roles](#employee-roles)
  - [Technology Stack](#technology-stack)
  - [Quick Start](#quick-start)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Project Structure](#project-structure)
  - [Configuration](#configuration)
    - [Database Configuration](#database-configuration)
    - [Database Setup Scripts](#database-setup-scripts)
  - [Default Credentials](#default-credentials)
  - [License](#license)

## Features

### Customer Management
- Customer registration and profile management
- Vehicle registration and tracking
- Service booking system
- Complaint submission and tracking

### Operations Management
- Fuel stock monitoring and dispensing records
- Service center workflow management
- Role-based access control for employees

### Employee Roles
- **Admin**: Manage service bookings, fuel suppliers, employees, and fuel levels
- **Fuel Cashier**: Process vehicle refueling transactions
- **Service Center**: Check and manage assigned service jobs
- **Customer Care Officer**: Handle and respond to customer complaints

## Technology Stack

- **Backend**: Jakarta EE 10 (Servlets, JSP)
- **Database**: Microsoft SQL Server
- **Build Tool**: Maven
- **Server**: Tomcat 11+ or any Jakarta EE 10 compatible server
- **Frontend**: JSP, HTML, CSS, JavaScript

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- Microsoft SQL Server
- Tomcat 11+ or Jakarta EE 10 compatible server

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/thisal-d/auto-fuel-x.git
   cd auto-fuel-x
   ```

2. **Database Setup**
   
   The project includes SQL scripts in the `database_config/` directory:
   - `01_create_tables.sql` - Creates all necessary tables
   - `02_create_triggers.sql` - Defines database triggers
   - `03_insert_defaults.sql` - Inserts required default data
   - `04_insert_sample_data.sql` - Inserts sample/test data
   - `05_select_data.sql` - Example select queries
   - `full_database_setup.sql` - Convenience script to run all scripts in order
   
   Execute these scripts on your SQL Server instance using SQL Server Management Studio or sqlcmd. The default database name referenced in the application is `AutoFuelX`.

3. **Configure Database Connection**
   
   Edit `src/main/java/com/example/autofuelx/util/DatabaseConnection.java` and update the connection constants with your database credentials:
   ```java
   static String URL = "jdbc:sqlserver://localhost:1433;databaseName=AutoFuelX;encrypt=false;";
   static String USER = "your_username";
   static String PASSWORD = "your_password";
   ```

4. **Build the Application**
   ```bash
   mvn clean package
   # To skip tests: mvn clean package -DskipTests
   ```
   
   The built WAR file will be located at `target/auto-fuel-x-1.0-SNAPSHOT.war`.

5. **Deploy the Application**
   
   Deploy the WAR file to a servlet container (Tomcat 11+ or any Jakarta EE 10 compatible server). If you prefer using a server-managed datasource, configure the server's datasource/credentials accordingly.
   
   For development, you can create an exploded artifact in your IDE (IntelliJ/Eclipse) and run the server directly from the IDE.

6. **Access the Application**
   
   Once deployed, access the application at the appropriate URL (e.g., `http://localhost:8080/auto-fuel-x/` if deployed to the `/auto-fuel-x` context).
   
   The main page provides links to:
   - Customer login/registration: `views/customer/login.jsp` and `views/customer/register.jsp`
   - Employee login: `views/employee/login.jsp`

## Project Structure

```
auto-fuel-x/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/autofuelx/
│   │   │       ├── controller/     # Servlets/controllers
│   │   │       ├── dao/           # Database access code
│   │   │       ├── dto/           # Data transfer objects
│   │   │       ├── model/         # Domain models
│   │   │       ├── service/       # Business logic
│   │   │       └── util/          # Utility classes
│   │   └── webapp/
│   │       ├── assets/            # CSS, JS, images
│   │       ├── views/            # JSP pages organized by role
│   │       └── index.jsp         # Entry point
├── database_config/               # SQL scripts for database setup
├── pom.xml                        # Maven configuration
└── README.md                      # This file
```

## Configuration

### Database Configuration
The primary configuration file for database connection is:
- `src/main/java/com/example/autofuelx/util/DatabaseConnection.java`

### Database Setup Scripts
All database setup scripts are located in the [`database_config/`](database_config/) directory. For a fresh installation, run the [`full_database_setup.sql`](database_config/full_database_setup.sql) script to create all tables, triggers, and insert default and sample data.

## Default Credentials

The system comes with pre-configured user accounts for different roles:

| Role | Email | Password | Permissions |
|------|-------|----------|-------------|
| Admin | admin@station.com | admin123 | Manage service bookings, fuel suppliers, employees, update fuel levels, add new fuel types |
| Fuel Cashier | r.cashier@station.com | rcashier123 | Process vehicle refueling |
| Service Center | s.center@station.com | scenter123 | Check and manage assigned service jobs |
| Customer Care Officer| c.care@station.com | ccare123 | Handle and respond to customer complaints |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
