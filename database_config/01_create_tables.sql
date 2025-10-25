-----------------------------------------------------
-- Table: Employee
-- Stores information about all employees including personal details,
-- contact info, role, login credentials, and work shift details.
-----------------------------------------------------
CREATE TABLE Employee
(
    EmployeeID  INT PRIMARY KEY IDENTITY (1,1),
    FirstName   VARCHAR(100) NOT NULL,
    LastName    VARCHAR(100) NOT NULL,
    DateOfBirth DATE         NOT NULL,
    Salary      DECIMAL(10, 2) CHECK (Salary >= 0),
    Status      VARCHAR(50) CHECK (Status IN ('Active', 'Inactive')),
    HireDate    DATE         NOT NULL,
    AddressNo   VARCHAR(50),
    AddressLane VARCHAR(100),
    AddressArea VARCHAR(100),
    Email       VARCHAR(100) UNIQUE CHECK (Email LIKE '%_@_%._%'),
    SkillSet    VARCHAR(255),
    Role        VARCHAR(100),
    Shift       VARCHAR(50),
    Password    VARCHAR(255) NOT NULL,
    Type        VARCHAR(50) CHECK (Type IN ('Admin', 'Refuel Cashier', 'Service Center Staff', 'Customer Care Officer'))
);

-----------------------------------------------------
-- Table: Customer
-- Stores customer information including personal details, login credentials,
-- and address information.
-----------------------------------------------------
CREATE TABLE Customer
(
    CustomerID  INT PRIMARY KEY IDENTITY (1,1),
    Email       VARCHAR(100) UNIQUE CHECK (Email LIKE '%_@_%._%'),
    FirstName   VARCHAR(100) NOT NULL,
    LastName    VARCHAR(100) NOT NULL,
    Password    VARCHAR(255) NOT NULL,
    AddressNo   VARCHAR(50),
    AddressLane VARCHAR(100),
    AddressArea VARCHAR(100)
);

-----------------------------------------------------
-- Table: Vehicle
-- Stores vehicle details including type, model, color, plate number,
-- registration date, and linked customer.
-----------------------------------------------------
CREATE TABLE Vehicle
(
    VehicleID        INT PRIMARY KEY IDENTITY (1,1),
    PlateNumber      VARCHAR(15) UNIQUE NOT NULL,
    Type             VARCHAR(15),
    Model            VARCHAR(20),
    Color            VARCHAR(20),
    CustomerID       INT                NULL,
    RegistrationDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

-----------------------------------------------------
-- Table: Service
-- Stores available service types, their description, and cost.
-----------------------------------------------------
CREATE TABLE Service
(
    ServiceID   INT PRIMARY KEY IDENTITY (1,1),
    Type        VARCHAR(100) NOT NULL,
    Description VARCHAR(200),
    Cost        DECIMAL(10, 2) CHECK (Cost >= 0)
);

-----------------------------------------------------
-- Table: Fuel
-- Stores fuel types, quantity in stock, and cost per liter.
-----------------------------------------------------
CREATE TABLE Fuel
(
    FuelID       INT PRIMARY KEY IDENTITY (1,1),
    Type         VARCHAR(50) UNIQUE NOT NULL,
    Quantity     DECIMAL(10, 2) CHECK (Quantity >= 0),
    CostPerLiter DECIMAL(10, 2) CHECK (CostPerLiter > 0)
);

-----------------------------------------------------
-- Table: Fuel Supplier
-- Stores information about fuel suppliers including name and contact number.
-----------------------------------------------------
CREATE TABLE FuelSupplier
(
    SupplierID  INT PRIMARY KEY IDENTITY (1,1),
    Name        VARCHAR(100)       NOT NULL,
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL
);

-----------------------------------------------------
-- Table: Complaint
-- Stores customer complaints including title, description, status, and timestamps.
-----------------------------------------------------
CREATE TABLE Complaint
(
    ComplaintID INT PRIMARY KEY IDENTITY (1,1),
    CustomerID  INT          NULL,
    Title       VARCHAR(150) NOT NULL,
    Description TEXT,
    Status      VARCHAR(50) CHECK (Status IN ('Sent', 'Seen')),
    CreatedDate DATE,
    CreatedTime TIME,
    UpdatedDate DATE,
    UpdateTime  TIME,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

-----------------------------------------------------
-- Table: Reply Complaint
-- Stores staff replies to customer complaints with timestamps and status.
-----------------------------------------------------
CREATE TABLE ReplyComplaint
(
    ReplyComplaintID INT PRIMARY KEY IDENTITY (1,1),
    StaffID          INT NULL,
    ComplaintID      INT NULL,
    Title            VARCHAR(150),
    Description      TEXT,
    CreatedDate      DATE,
    CreatedTime      TIME,
    UpdatedDate      DATE,
    UpdateTime       TIME,
    Status           VARCHAR(50) CHECK (Status IN ('Sent', 'Seen')),
    FOREIGN KEY (ComplaintID) REFERENCES Complaint (ComplaintID),
    FOREIGN KEY (StaffID) REFERENCES Employee (EmployeeID)
);

-----------------------------------------------------
-- Table: Feedback
-- Stores customer feedback including rating, message, timestamps, and linked customer.
-----------------------------------------------------
CREATE TABLE Feedback
(
    FeedbackID  INT PRIMARY KEY IDENTITY (1,1),
    Rate        INT CHECK (Rate BETWEEN 1 AND 5),
    Message     TEXT,
    CreatedDate DATE,
    CreatedTime TIME,
    CustomerID  INT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

-----------------------------------------------------
-- Table: Employee Phone Numbers
-- Stores multiple phone numbers for employees.
-----------------------------------------------------
CREATE TABLE EmployeePhoneNumber
(
    EmployeeID  INT,
    PhoneNumber VARCHAR(20),
    PRIMARY KEY (EmployeeID, PhoneNumber),
    FOREIGN KEY (EmployeeID) REFERENCES Employee (EmployeeID)
);

-----------------------------------------------------
-- Table: Customer Phone Numbers
-- Stores multiple phone numbers for customers.
-----------------------------------------------------
CREATE TABLE CustomerPhoneNumber
(
    CustomerID  INT,
    PhoneNumber VARCHAR(20),
    PRIMARY KEY (CustomerID, PhoneNumber),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

-----------------------------------------------------
-- Table: Fuel Purchase
-- Records fuel purchases by customers for specific vehicles with total cost and timestamps.
-----------------------------------------------------
CREATE TABLE FuelPurchase
(
    FuelPurchaseID INT PRIMARY KEY IDENTITY (1,1),
    CustomerID     INT NULL,
    FuelID         INT NULL,
    VehicleID      INT NULL,
    Quantity       DECIMAL(10, 2) CHECK (Quantity > 0),
    TotalCost      DECIMAL(10, 2) CHECK (TotalCost >= 0),
    PurchaseDate   DATE,
    PurchaseTime   TIME,
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID),
    FOREIGN KEY (FuelID) REFERENCES Fuel (FuelID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicle (VehicleID)
);

-----------------------------------------------------
-- Table: Fuel Supply
-- Records fuel supplied by suppliers including quantity and timestamps.
-----------------------------------------------------
CREATE TABLE FuelSupply
(
    SupplyID   INT PRIMARY KEY IDENTITY (1,1),
    SupplierID INT,
    FuelID     INT,
    Quantity   DECIMAL(10, 2) CHECK (Quantity > 0),
    SupplyDate DATE,
    SupplyTime TIME,
    FOREIGN KEY (SupplierID) REFERENCES FuelSupplier (SupplierID),
    FOREIGN KEY (FuelID) REFERENCES Fuel (FuelID)
);

-----------------------------------------------------
-- Table: Service Booking
-- Records bookings of services by customers, linked to vehicles, services, and staff,
-- including total cost, status, and timestamps.
-----------------------------------------------------
CREATE TABLE ServiceBooking
(
    BookingID   INT PRIMARY KEY IDENTITY (1,1),
    CustomerID  INT NULL,
    VehicleID   INT NULL,
    ServiceID   INT NULL,
    StaffID     INT NULL,
    BookingDate DATE,
    BookingTime TIME,
    TotalCost   DECIMAL(10, 2) CHECK (TotalCost >= 0),
    Status      VARCHAR(50) CHECK (Status IN ('Awaiting Confirmation', 'Confirmed', 'In Progress', 'Missed Appointment',
                                              'Awaiting Pickup', 'Completed', 'Reschedule Required', 'Cancelled')),
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicle (VehicleID),
    FOREIGN KEY (ServiceID) REFERENCES Service (ServiceID),
    FOREIGN KEY (StaffID) REFERENCES Employee (EmployeeID)
);
