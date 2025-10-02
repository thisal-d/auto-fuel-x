-----------------------------------------------------
CREATE TABLE Employee (
                          EmployeeID INT PRIMARY KEY IDENTITY(1,1),
                          FirstName VARCHAR(100),
                          LastName VARCHAR(100),
                          DateOfBirth DATE,
                          Salary DECIMAL(10,2),
                          Status VARCHAR(50),
                          HireDate DATE,
                          AddressNo VARCHAR(50),
                          AddressLane VARCHAR(100),
                          AddressArea VARCHAR(100),
                          Email VARCHAR(100) UNIQUE,
                          SkillSet VARCHAR(255),
                          Role VARCHAR(100),
                          Shift VARCHAR(50),
                          Password VARCHAR(255),
                          Type VARCHAR(50) -- Admin | Refuel Cashier | Service Center Staff
);


-----------------------------------------------------
-- Customer
-----------------------------------------------------
CREATE TABLE Customer (
                          CustomerID INT PRIMARY KEY IDENTITY(1,1),
                          Email VARCHAR(50),
                          FirstName VARCHAR(100),
                          LastName VARCHAR(100),
                          Password VARCHAR(255),
                          AddressNo VARCHAR(50),
                          AddressLane VARCHAR(100),
                          AddressArea VARCHAR(100)
);


-----------------------------------------------------
-- Vehicle
-----------------------------------------------------
CREATE TABLE Vehicle (
                         VehicleID INT PRIMARY KEY IDENTITY(1,1),
                         PlateNumber VARCHAR(15) UNIQUE,
                         Type VARCHAR(15),
                         Model VARCHAR(20),
                         Color VARCHAR(20),
                         CustomerID INT,
                         RegistrationDate DATE
);


-----------------------------------------------------
-- Service
-----------------------------------------------------
CREATE TABLE Service (
                         ServiceID INT PRIMARY KEY IDENTITY(1,1),
                         Type VARCHAR(100),
                         Description VARCHAR(200),
                         Cost DECIMAL(10,2)
);


-----------------------------------------------------
-- Fuel
-----------------------------------------------------
CREATE TABLE Fuel (
                      FuelID INT PRIMARY KEY IDENTITY(1,1),
                      Type VARCHAR(50),
                      Quantity DECIMAL(10,2),
                      CostPerLiter DECIMAL(10,2)
);


-----------------------------------------------------
-- Fuel Supplier
-----------------------------------------------------
CREATE TABLE FuelSupplier (
                              SupplierID INT PRIMARY KEY IDENTITY(1,1),
                              Name VARCHAR(100),
                              PhoneNumber VARCHAR(20)
);


-----------------------------------------------------
-- Complaint
-----------------------------------------------------
CREATE TABLE Complaint (
                           ComplaintID INT PRIMARY KEY IDENTITY(1,1),
                           CustomerID INT,
                           Title VARCHAR(150),
                           Description TEXT,
                           Status VARCHAR(50),
                           CreatedDate DATE,
                           CreatedTime TIME,
                           UpdatedDate DATE,
                           UpdateTime Time
);



-----------------------------------------------------
-- Reply Complaint
-----------------------------------------------------
CREATE TABLE ReplyComplaint (
                                ReplyComplaintID INT PRIMARY KEY IDENTITY(1,1),
                                StaffID INT,
                                ComplaintID INT,
                                Title VARCHAR(150),
                                Description TEXT,
                                CreatedDate DATE,
                                CreatedTime TIME,
                                UpdatedDate DATE,
                                UpdateTime Time,
                                Status VARCHAR(50)
);

-----------------------------------------------------
-- Feedback
-----------------------------------------------------
CREATE TABLE Feedback (
                          FeedbackID INT PRIMARY KEY IDENTITY(1,1),
                          Rate INT CHECK (Rate BETWEEN 1 AND 5),
                          Message TEXT,
                          CreatedDate DATE,
                          CreatedTime DATE,
                          CustomerID INT
);


-----------------------------------------------------
-- Employee Phone Numbers
-----------------------------------------------------
CREATE TABLE EmployeePhoneNumber (
                                     EmployeeID INT,
                                     PhoneNumber VARCHAR(20),
                                     PRIMARY KEY (EmployeeID, PhoneNumber)
);


-----------------------------------------------------
-- Customer Phone Numbers
-----------------------------------------------------
CREATE TABLE CustomerPhoneNumber (
                                     CustomerID INT,
                                     PhoneNumber VARCHAR(20),
                                     PRIMARY KEY (CustomerID, PhoneNumber)
);


-----------------------------------------------------
-- Fuel Purchase (Customer–Fuel)
-----------------------------------------------------
CREATE TABLE FuelPurchase (
                              FuelPurchaseID INT PRIMARY KEY IDENTITY(1,1),
                              CustomerID INT,
                              FuelID INT,
                              VehicleID INT,
                              Quantity DECIMAL(10,2),
                              TotalCost DECIMAL(10,2),
                              PurchaseDate DATE,
                              PurchaseTime TIME
);


-----------------------------------------------------
-- Fuel Supply (Supplier–Fuel)
-----------------------------------------------------
CREATE TABLE FuelSupply (
                            SupplierID INT,
                            FuelID INT,
                            Quantity DECIMAL(10, 2),
                            SupplyDate DATE,
                            SupplyTime TIME,
                            PRIMARY KEY (SupplierID, FuelID)
);


-----------------------------------------------------
-- Booking (Customer–Service–Vehicle–Employee)
-----------------------------------------------------
CREATE TABLE ServiceBooking (
                                BookingID INT PRIMARY KEY IDENTITY(1,1),
                                CustomerID INT,
                                VehicleID INT,
                                ServiceID INT,
                                StaffID INT NULL,
                                BookingDate DATE,
                                BookingTime TIME,
                                TotalCost DECIMAL(10, 2),
                                Status VARCHAR(50)
);



-- Insert Employees with appropriate NULL values for irrelevant attributes
INSERT INTO Employee (FirstName, LastName, DateOfBirth, Salary, Status, HireDate, AddressNo, AddressLane, AddressArea, Email, SkillSet, Role, Shift, Password, Type)
VALUES
-- Admin (has Role, others are NULL)
('John', 'Doe', '1980-01-15', 5000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Downtown', 'admin@station.com', NULL, 'Admin', NULL, 'admin123', 'Admin'),

-- Service Center Staff (has SkillSet, others are NULL)
('Alice', 'Smith', '1985-05-20', 3500.00, 'Active', '2021-03-15', '456', 'Oak Avenue', 'Westside', 'service@station.com', 'Engine Repair, Diagnostics, Tire Change', NULL, NULL, 'service123', 'Service Center Staff'),

-- Refuel Cashier (has Shift, others are NULL)
('Bob', 'Johnson', '1990-08-30', 4000.00, 'Active', '2019-11-20', '789', 'Pine Road', 'Eastside', 'cashier@station.com', NULL, NULL, 'Night', 'cashier123', 'Refuel Cashier'),

-- Customer Care Officer (all specific attributes are NULL)
('Emma', 'Wilson', '1992-03-25', 3800.00, 'Active', '2022-02-10', '321', 'Elm Street', 'Northside', 'care@station.com', NULL, NULL, NULL, 'care123', 'Customer Care Officer');

-- Insert Employee Phone Numbers
INSERT INTO EmployeePhoneNumber (EmployeeID, PhoneNumber)
VALUES
    (1, '555-0101'),
    (1, '555-0102'),
    (2, '555-0201'),
    (3, '555-0301');

-- Insert Customers
INSERT INTO Customer (Email, FirstName, LastName, Password, AddressNo, AddressLane, AddressArea)
VALUES
    ('customer1@email.com', 'Michael', 'Brown', 'pass123', '101', 'Elm Street', 'Northside'),
    ('customer2@email.com', 'Sarah', 'Wilson', 'pass123', '202', 'Maple Drive', 'Southside'),
    ('customer3@email.com', 'David', 'Taylor', 'pass123', '303', 'Cedar Lane', 'Eastside');

-- Insert Customer Phone Numbers
INSERT INTO CustomerPhoneNumber (CustomerID, PhoneNumber)
VALUES
    (1, '555-1001'),
    (1, '555-1002'),
    (2, '555-2001'),
    (3, '555-3001');

-- Insert Vehicles
INSERT INTO Vehicle (PlateNumber, Type, Model, Color, CustomerID, RegistrationDate)
VALUES
    ('ABC123', 'Car', 'Toyota', 'Blue', 1, '2020-01-15'),
    ('XYZ789', 'Motorcycle', 'Honda', 'Red', 2, '2021-05-20'),
    ('DEF456', 'Truck', 'Ford', 'Black', 3, '2019-11-10');

-- Insert Services
INSERT INTO Service (Type, Description, Cost)
VALUES
    ('Oil Change', 'Standard oil change with filter replacement', 29.99),
    ('Tire Rotation', 'Rotating all four tires for even wear', 19.99),
    ('Brake Inspection', 'Complete brake system inspection', 39.99),
    ('Engine Tune-up', 'Full engine diagnostic and tune-up', 89.99),
    ('Car Wash', 'Exterior and interior cleaning', 15.99);

-- Insert Fuel
INSERT INTO Fuel (Type, Quantity, CostPerLiter)
VALUES
    ('Petrol 95', 1000.00, 1.25),
    ('Petrol 92', 800.00, 1.35),
    ('Super Diesel', 600.00, 1.45),
    ('Diesel', 500.00, 1.30);

-- Insert Fuel Suppliers
INSERT INTO FuelSupplier (Name, PhoneNumber)
VALUES
    ('Fuel Corp', '555-5001'),
    ('Oil Inc', '555-5002'),
    ('Gas Ltd', '555-5003');

-- Insert Fuel Supply
INSERT INTO FuelSupply (SupplierID, FuelID, Quantity, SupplyDate, SupplyTime)
VALUES
    (1, 1, 500.00, '2023-01-10', '10:00:00'),
    (1, 2, 400.00, '2023-01-10', '10:30:00'),
    (2, 3, 300.00, '2023-01-15', '14:00:00'),
    (3, 4, 250.00, '2023-01-20', '11:00:00');

-- Insert Complaints
INSERT INTO Complaint (CustomerID, Title, Description, Status, CreatedDate, CreatedTime, UpdatedDate, UpdateTime)
VALUES
    (1, 'Poor Service', 'The service was very slow and unprofessional.', 'Open', '2023-02-01', '09:30:00', '2023-02-01', '09:30:00'),
    (2, 'Fuel Quality', 'The fuel quality seems to be poor, affecting my vehicle performance.', 'In Progress', '2023-02-05', '14:15:00', '2023-02-06', '10:20:00'),
    (3, 'Billing Issue', 'I was overcharged for my last fuel purchase.', 'Resolved', '2023-01-20', '16:45:00', '2023-01-25', '11:30:00');

-- Insert Reply Complaints
INSERT INTO ReplyComplaint (StaffID, ComplaintID, Title, Description, CreatedDate, CreatedTime, UpdatedDate, UpdateTime, Status)
VALUES
    (3, 1, 'Regarding Poor Service', 'We apologize for the inconvenience. We will address this issue with our staff.', '2023-02-02', '10:00:00', '2023-02-02', '10:00:00', 'In Progress'),
    (2, 2, 'Regarding Fuel Quality', 'We are investigating this matter. Our fuel is regularly tested for quality.', '2023-02-06', '11:00:00', '2023-02-06', '11:00:00', 'In Progress'),
    (1, 3, 'Regarding Billing Issue', 'We have reviewed your account and issued a refund for the overcharged amount.', '2023-01-25', '11:30:00', '2023-01-25', '11:30:00', 'Resolved');

-- Insert Feedback
INSERT INTO Feedback (Rate, Message, CreatedDate, CreatedTime, CustomerID)
VALUES
    (4, 'Overall good service, but could be faster.', '2023-01-15', '10:30:00', 1),
    (5, 'Excellent service and very professional staff.', '2023-01-20', '14:20:00', 2),
    (3, 'Average experience, nothing special.', '2023-02-01', '09:15:00', 3);

-- Insert Fuel Purchases
INSERT INTO FuelPurchase (CustomerID, FuelID, VehicleID, Quantity, TotalCost, PurchaseDate, PurchaseTime)
VALUES
    (1, 1, 1, 20.00, 25.00, '2023-01-10', '08:30:00'),
    (2, 3, 2, 15.00, 21.75, '2023-01-15', '12:45:00'),
    (3, 4, 3, 30.00, 39.00, '2023-01-20', '17:20:00'),
    (1, 2, 1, 25.00, 33.75, '2023-02-01', '09:15:00');

-- Insert Service Bookings
INSERT INTO ServiceBooking (CustomerID, VehicleID, ServiceID, StaffID, BookingDate, BookingTime, TotalCost, Status)
VALUES
    (1, 1, 1, 3, '2023-02-10', '10:00:00', 29.99, 'Completed'),
    (2, 2, 3, NULL, '2023-02-15', '14:00:00', 39.99, 'Completed'),
    (3, 3, 2, 3, '2023-02-20', '09:00:00', 19.99, 'Completed'),
    (1, 1, 5, NULL, '2023-02-25', '11:00:00', 15.99, 'Completed');