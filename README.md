# Auto Fuel X

Auto Fuel X is a comprehensive web-based fuel station and vehicle service management system that helps manage fuel
dispensing, vehicle services, customer complaints, and employee operations.

## Features

- Customer Management
    - Registration and profile management
    - Vehicle registration
    - Service booking
    - Complaint management

- Fuel Management
    - Fuel stock tracking
    - Fuel dispensing records
    - Real-time fuel availability

- Service Center Management
    - Service appointments
    - Service tracking

- Employee Management
    - Multiple role support (Admin, Customer Care, Refuel Cashier, Service Center Staff)
    - Employee profiles
    - Task assignment

## Prerequisites

- JDK 8 or higher
- Apache Maven 3.6 or higher
- Microsoft SQL Server 2019 or higher
- IntelliJ IDEA (Ultimate Edition recommended)
- Apache Tomcat 10.1.x

## Setup Instructions

### 1. Database Setup

1. Open Microsoft SQL Server Management Studio
2. Connect to your SQL Server instance
3. Navigate to the `configuration/` folder in the project
4. Execute the following SQL scripts in order:
   ```
   data_base_configuration.sql   # Creates the database and tables, Sets up necessary triggers,  Loads sample data
   ```

### 2. Project Setup in IntelliJ IDEA

1. Clone the repository:
   ```powershell
   git clone https://github.com/thisal-d/auto-fuel-x.git
   cd auto-fuel-x
   ```

2. Open IntelliJ IDEA
3. Go to `File` → `Open` and select the project folder
4. Wait for IntelliJ to import the Maven project and download dependencies
5. Configure Tomcat Server:
    - Go to `File` → `Project Structure` → `Artifacts`
    - Create new artifact: `Web Application: Exploded` from modules
    - Select `auto-fuel-x` module

### 3. Database Connection Configuration

1. Open `src/main/java/com/example/autofuelx/util/DatabaseConnection.java`
2. Update the following properties with your database configuration:
   ```java
    static String URL = "jdbc:sqlserver://localhost:1433;databaseName=your_db_name;encrypt=false;";
    static String USER = "your_user_name";
    static String PASSWORD = "your_password";
   ```

### 4. Running the Application

1. Configure Tomcat Run Configuration:
    - Click `Edit Configurations` in the toolbar
    - Add New Configuration (`+`) → `Tomcat Server` → `Local`
    - Set `Name` to "Auto Fuel X"
    - Under `Deployment` tab, add the artifact created earlier
    - Set `Application context` to `/auto-fuel-x`

2. Build and Run:
    - Click the Run button (green play button)
    - The application will be deployed to Tomcat

### 5. Default Users

After running the sample data script, you can use these default users:

```
Admin:
- Username: admin@station.com
- Password: admin123

Customer Care:
- Username: c.care@station.com
- Password: ccare123

Refuel Cashier:
- Username: r.cashier@station.com
- Password: rcashier123

Service Center Staff:
- Username: s.center@station.com
- Password: scenter123
```

## Project Structure

```
auto-fuel-x/
├── src/
│   ├── main/
│   │   ├── java/com/example/autofuelx/
│   │   │   ├── controller/   # Servlet controllers
│   │   │   ├── dao/          # Data Access Objects
│   │   │   ├── dto/          # Data Transfer Objects
│   │   │   ├── model/        # Business models
│   │   │   ├── service/      # Business logic
│   │   │   └── util/         # Utilities
│   │   ├── webapp/
│   │   │   ├── assets/       # CSS, images
│   │   │   ├── views/        # JSP files
│   │   │   └── WEB-INF/      # Web configuration
│   └── test/                 # Test files
├── configuration/            # SQL scripts
└── pom.xml                   # Maven configuration
```

## Development Guidelines

- All database operations should use the ConnectionManager utility
- Follow MVC pattern:
    - Models: Data structures in `model` package
    - Views: JSP files in `webapp/views`
    - Controllers: Servlets in `controller` package
- Use DTO objects for data transfer between layers
- Implement proper error handling and logging

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and queries, please open an issue in the GitHub repository.