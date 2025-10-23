---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------
-- Employee
-----------------------------------------------------
CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY IDENTITY(1,1),
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Salary DECIMAL(10,2) CHECK (Salary >= 0),
    Status VARCHAR(50) CHECK (Status IN ('Active', 'Inactive')),
    HireDate DATE NOT NULL,
    AddressNo VARCHAR(50),
    AddressLane VARCHAR(100),
    AddressArea VARCHAR(100),
    Email VARCHAR(100) UNIQUE CHECK (Email LIKE '%_@_%._%'),
    SkillSet VARCHAR(255),
    Role VARCHAR(100),
    Shift VARCHAR(50),
    Password VARCHAR(255) NOT NULL,
    Type VARCHAR(50) CHECK (Type IN ('Admin', 'Refuel Cashier', 'Service Center Staff', 'Customer Care Officer'))
);

-----------------------------------------------------
-- Customer
-----------------------------------------------------
CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY IDENTITY(1,1),
    Email VARCHAR(100) UNIQUE CHECK (Email LIKE '%_@_%._%'),
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    AddressNo VARCHAR(50),
    AddressLane VARCHAR(100),
    AddressArea VARCHAR(100)
);

-----------------------------------------------------
-- Vehicle
-----------------------------------------------------
CREATE TABLE Vehicle (
    VehicleID INT PRIMARY KEY IDENTITY(1,1),
    PlateNumber VARCHAR(15) UNIQUE NOT NULL,
    Type VARCHAR(15),
    Model VARCHAR(20),
    Color VARCHAR(20),
    CustomerID INT NULL,
    RegistrationDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-----------------------------------------------------
-- Service
-----------------------------------------------------
CREATE TABLE Service (
    ServiceID INT PRIMARY KEY IDENTITY(1,1),
    Type VARCHAR(100) NOT NULL,
    Description VARCHAR(200),
    Cost DECIMAL(10,2) CHECK (Cost >= 0)
);

-----------------------------------------------------
-- Fuel
-----------------------------------------------------
CREATE TABLE Fuel (
    FuelID INT PRIMARY KEY IDENTITY(1,1),
    Type VARCHAR(50) UNIQUE NOT NULL,
    Quantity DECIMAL(10,2) CHECK (Quantity >= 0),
    CostPerLiter DECIMAL(10,2) CHECK (CostPerLiter > 0)
);

-----------------------------------------------------
-- Fuel Supplier
-----------------------------------------------------
CREATE TABLE FuelSupplier (
    SupplierID INT PRIMARY KEY IDENTITY(1,1),
    Name VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL
);

-----------------------------------------------------
-- Complaint
-----------------------------------------------------
CREATE TABLE Complaint (
    ComplaintID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT NULL,
    Title VARCHAR(150) NOT NULL,
    Description TEXT,
    Status VARCHAR(50) CHECK (Status IN ('Sent', 'Seen')),
    CreatedDate DATE,
    CreatedTime TIME,
    UpdatedDate DATE,
    UpdateTime TIME,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-----------------------------------------------------
-- Reply Complaint
-----------------------------------------------------
CREATE TABLE ReplyComplaint (
    ReplyComplaintID INT PRIMARY KEY IDENTITY(1,1),
    StaffID INT NULL,
    ComplaintID INT NULL,
    Title VARCHAR(150),
    Description TEXT,
    CreatedDate DATE,
    CreatedTime TIME,
    UpdatedDate DATE,
    UpdateTime TIME,
    Status VARCHAR(50) CHECK (Status IN ('Sent', 'Seen')),
    FOREIGN KEY (ComplaintID) REFERENCES Complaint(ComplaintID),
    FOREIGN KEY (StaffID) REFERENCES Employee(EmployeeID)
);

-----------------------------------------------------
-- Feedback
-----------------------------------------------------
CREATE TABLE Feedback (
    FeedbackID INT PRIMARY KEY IDENTITY(1,1),
    Rate INT CHECK (Rate BETWEEN 1 AND 5),
    Message TEXT,
    CreatedDate DATE,
    CreatedTime TIME,
    CustomerID INT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-----------------------------------------------------
-- Employee Phone Numbers
-----------------------------------------------------
CREATE TABLE EmployeePhoneNumber (
    EmployeeID INT,
    PhoneNumber VARCHAR(20),
    PRIMARY KEY (EmployeeID, PhoneNumber),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-----------------------------------------------------
-- Customer Phone Numbers
-----------------------------------------------------
CREATE TABLE CustomerPhoneNumber (
    CustomerID INT,
    PhoneNumber VARCHAR(20),
    PRIMARY KEY (CustomerID, PhoneNumber),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-----------------------------------------------------
-- Fuel Purchase (Customer–Fuel–Vehicle)
-----------------------------------------------------
CREATE TABLE FuelPurchase (
    FuelPurchaseID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT NULL,
    FuelID INT NULL,
    VehicleID INT NULL,
    Quantity DECIMAL(10,2) CHECK (Quantity > 0),
    TotalCost DECIMAL(10,2) CHECK (TotalCost >= 0),
    PurchaseDate DATE,
    PurchaseTime TIME,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (FuelID) REFERENCES Fuel(FuelID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID)
);

-----------------------------------------------------
-- Fuel Supply (Supplier–Fuel)
-----------------------------------------------------
CREATE TABLE FuelSupply (
    SupplyID INT PRIMARY KEY IDENTITY(1,1),
    SupplierID INT,
    FuelID INT,
    Quantity DECIMAL(10,2) CHECK (Quantity > 0),
    SupplyDate DATE,
    SupplyTime TIME,
    FOREIGN KEY (SupplierID) REFERENCES FuelSupplier(SupplierID),
    FOREIGN KEY (FuelID) REFERENCES Fuel(FuelID)
);

-----------------------------------------------------
-- Service Booking (Customer–Service–Vehicle–Employee)
-----------------------------------------------------
CREATE TABLE ServiceBooking (
    BookingID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT NULL,
    VehicleID INT NULL,
    ServiceID INT NULL,
    StaffID INT NULL,
    BookingDate DATE,
    BookingTime TIME,
    TotalCost DECIMAL(10,2) CHECK (TotalCost >= 0),
    Status VARCHAR(50) CHECK (Status IN ('Awaiting Confirmation', 'Confirmed', 'In Progress', 'Missed Appointment', 'Awaiting Pickup', 'Completed', 'Reschedule Required', 'Cancelled')),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID),
    FOREIGN KEY (ServiceID) REFERENCES Service(ServiceID),
    FOREIGN KEY (StaffID) REFERENCES Employee(EmployeeID)
);



---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------
-- Employee
-----------------------------------------------------
INSERT INTO Employee (FirstName, LastName, DateOfBirth, Salary, Status, HireDate, AddressNo, AddressLane, AddressArea, Email, Password, Type, SkillSet, Role, Shift)
VALUES
('John', 'Smith', '1985-05-15', 50000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Colombo 03', 'john.smith@example.com', 'password123', 'Admin', NULL, 'Super', NULL),
('Kevin', 'Smith', '1985-05-15', 50000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Colombo 03', 'admin@station.com', 'admin123', 'Admin', NULL, 'Super', NULL),
('Abishak', 'Kumar', '1985-05-15', 50000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Colombo 03', 'kumar@gmail.com', 'admin123', 'Admin', NULL, 'Normal', NULL),
('Emily', 'Johnson', '1990-08-22', 45000.00, 'Active', '2021-03-15', '45/A', 'Galle Road', 'Mount Lavinia', 'emily.j@example.com', 'securepass456', 'Refuel Cashier', NULL, NULL, 'Day'),
('Arwin', 'Johnson', '1990-08-22', 45000.00, 'Active', '2021-03-15', '45/A', 'Galle Road', 'Mount Lavinia', 'r.cashier@station.com', 'rcashier123', 'Refuel Cashier', NULL, NULL, 'Day'),
('Michael', 'Williams', '1988-11-03', 48000.00, 'Active', '2019-07-22', '789', 'Kandy Road', 'Kadawatha', 'm.williams@example.com', 'mypass789', 'Service Center Staff', 'Engine Diagnostics, Brake Repair', NULL, NULL),
('Mark', 'Adams', '1988-11-03', 48000.00, 'Active', '2019-07-22', '789', 'Kandy Road', 'Kadawatha', 's.center@station.com', 'scenter123', 'Service Center Staff', 'Engine Diagnostics, Brake Repair', NULL, NULL),
('Sarah', 'Brown', '1992-02-28', 42000.00, 'Inactive', '2022-05-10', '101', 'Marine Drive', 'Negombo', 'sarah.b@example.com', 'sarahpass321', 'Customer Care Officer', NULL, NULL, NULL),
('Savin', 'Prabod', '1992-02-28', 42000.00, 'Inactive', '2022-05-10', '101', 'Marine Drive', 'Negombo', 'c.care@station.com', 'ccare123', 'Customer Care Officer', NULL, NULL, NULL),
('David', 'Jones', '1987-09-14', 52000.00, 'Active', '2018-11-05', '202', 'High Level Road', 'Maharagama', 'david.jones@example.com', 'djpass654', 'Service Center Staff', 'Transmission Service, Electrical Systems', NULL, NULL);

-----------------------------------------------------
-- Customer
-----------------------------------------------------
INSERT INTO Customer (Email, FirstName, LastName, Password)
VALUES
('alice@example.com', 'Alice', 'Anderson', 'alicepass123'),
('bob@example.com', 'Bob', 'Baker', 'bobpass456'),
('charlie@example.com', 'Charlie', 'Clark', 'charliepass789'),
('diana@example.com', 'Diana', 'Davis', 'dianapass321'),
('edward@example.com', 'Edward', 'Evans', 'edwardpass654'),
('customer@station.com', 'Customer', 'Station', 'customer123');

-----------------------------------------------------
-- Vehicle
-----------------------------------------------------
INSERT INTO Vehicle (PlateNumber, Type, Model, Color, CustomerID, RegistrationDate)
VALUES 
('ABC123', 'Car', 'Toyota Camry', 'Blue', 1, '2020-01-15'),
('XYZ789', 'SUV', 'Honda CR-V', 'Red', 1, '2021-03-20'),
('DEF456', 'Car', 'Ford Focus', 'Black', 2, '2019-07-10'),
('GHI012', 'Motorcycle', 'Yamaha R6', 'Green', 3, '2022-05-25'),
('JKL345', 'Truck', 'Ford F-150', 'White', 4, '2020-11-08');

-----------------------------------------------------
-- Service
-----------------------------------------------------
INSERT INTO Service (Type, Description, Cost)
VALUES 
('Oil Change', 'Standard oil change service', 45.00),
('Tire Rotation', 'Rotate and balance all four tires', 35.00),
('Brake Inspection', 'Complete brake system inspection', 65.00),
('Engine Diagnostic', 'Full engine diagnostic test', 85.00),
('Transmission Service', 'Transmission fluid change and filter', 120.00);

-----------------------------------------------------
-- Fuel
-----------------------------------------------------
INSERT INTO Fuel (Type, Quantity, CostPerLiter)
VALUES
('Petrol-95', 5000.00, 370),
('Petrol-92', 5000.00, 350),
('Super Diesel', 3000.00, 255),
('Diesel', 4000.00, 245);

-----------------------------------------------------
-- Fuel Supplier
-----------------------------------------------------
INSERT INTO FuelSupplier (Name, PhoneNumber)
VALUES 
('Shell Oil Company', '555-0101'),
('Exxon Mobil', '555-0102'),
('Chevron Corporation', '555-0103'),
('BP Oil', '555-0104'),
('Sunoco', '555-0105');

-----------------------------------------------------
-- Complaint
-----------------------------------------------------
INSERT INTO Complaint (CustomerID, Title, Description, Status, CreatedDate, CreatedTime)
VALUES 
(1, 'Poor Service Quality', 'The service was not up to the expected standard', 'Sent', '2023-01-15', '10:30:00'),
(2, 'Long Waiting Time', 'Had to wait for more than 2 hours for a simple service', 'Seen', '2023-02-20', '14:45:00'),
(3, 'Incorrect Billing', 'Was charged for services I did not receive', 'Sent', '2023-03-10', '09:15:00'),
(4, 'Rude Staff Behavior', 'The staff member was very unprofessional', 'Seen', '2023-04-05', '16:20:00'),
(5, 'Facility Cleanliness', 'The service center was not clean and organized', 'Sent', '2023-05-12', '11:30:00');

-----------------------------------------------------
-- Reply Complaint
-----------------------------------------------------
INSERT INTO ReplyComplaint (StaffID, ComplaintID, Title, Description, Status, CreatedDate, CreatedTime)
VALUES 
(1, 1, 'Re: Poor Service Quality', 'We apologize for the inconvenience. We will improve our service quality.', 'Sent', '2023-01-16', '09:00:00'),
(2, 2, 'Re: Long Waiting Time', 'We are working on reducing waiting times. Thank you for your patience.', 'Seen', '2023-02-21', '11:30:00'),
(3, 3, 'Re: Incorrect Billing', 'We have corrected the billing error. Please check your updated invoice.', 'Sent', '2023-03-11', '14:15:00'),
(4, 4, 'Re: Rude Staff Behavior', 'We apologize for the behavior of our staff. We will take appropriate action.', 'Seen', '2023-04-06', '10:45:00'),
(5, 5, 'Re: Facility Cleanliness', 'We have addressed the cleanliness issues. Thank you for bringing this to our attention.', 'Sent', '2023-05-13', '13:20:00');

-----------------------------------------------------
-- Feedback
-----------------------------------------------------
INSERT INTO Feedback (Rate, Message, CreatedDate, CreatedTime, CustomerID)
VALUES 
(5, 'Excellent service! Very satisfied with the work done.', '2023-01-20', '15:30:00', 1),
(4, 'Good service overall, but the waiting time was a bit long.', '2023-02-25', '11:45:00', 2),
(3, 'Average service. Nothing exceptional but nothing terrible either.', '2023-03-15', '09:20:00', 3),
(5, 'Outstanding service! The staff was very professional and helpful.', '2023-04-10', '14:10:00', 4),
(2, 'Not satisfied with the service. The issue was not resolved properly.', '2023-05-18', '16:35:00', 5);

-----------------------------------------------------
-- Employee Phone Numbers
-----------------------------------------------------
INSERT INTO EmployeePhoneNumber (EmployeeID, PhoneNumber)
VALUES 
(1, '555-1001'),
(1, '555-1002'),
(2, '555-2001'),
(3, '555-3001'),
(3, '555-3002'),
(4, '555-4001'),
(5, '555-5001'),
(5, '555-5002');

-----------------------------------------------------
-- Customer Phone Numbers
-----------------------------------------------------
INSERT INTO CustomerPhoneNumber (CustomerID, PhoneNumber)
VALUES 
(1, '555-1111'),
(1, '555-1112'),
(2, '555-2221'),
(3, '555-3331'),
(3, '555-3332'),
(4, '555-4441'),
(5, '555-5551'),
(5, '555-5552');

-----------------------------------------------------
-- Fuel Purchase
-----------------------------------------------------
INSERT INTO FuelPurchase (CustomerID, FuelID, VehicleID, Quantity, TotalCost, PurchaseDate, PurchaseTime)
VALUES 
(1, 1, 1, 20.00, 27.00, '2023-01-15', '10:30:00'),
(1, 2, 2, 15.00, 23.25, '2023-02-20', '14:45:00'),
(2, 1, 3, 25.00, 33.75, '2023-03-10', '09:15:00'),
(3, 3, 4, 30.00, 43.50, '2023-04-05', '16:20:00'),
(4, 2, 5, 18.00, 27.90, '2023-05-12', '11:30:00');

-----------------------------------------------------
-- Fuel Supply
-----------------------------------------------------
INSERT INTO FuelSupply (SupplierID, FuelID, Quantity, SupplyDate, SupplyTime)
VALUES 
(1, 1, 1000.00, '2023-01-01', '08:00:00'),
(1, 2, 800.00, '2023-01-01', '08:30:00'),
(2, 1, 1200.00, '2023-02-01', '09:00:00'),
(2, 3, 900.00, '2023-02-01', '09:30:00'),
(3, 2, 700.00, '2023-03-01', '10:00:00'),
(3, 4, 500.00, '2023-03-01', '10:30:00'),
(4, 3, 1100.00, '2023-04-01', '11:00:00'),
(5, 4, 400.00, '2023-05-01', '11:30:00');

-----------------------------------------------------
-- Service Booking
-----------------------------------------------------
INSERT INTO ServiceBooking (CustomerID, VehicleID, ServiceID, StaffID, BookingDate, BookingTime, TotalCost, Status)
VALUES 
(1, 1, 1, 3, '2023-06-15', '10:00:00', 45.00, 'Confirmed'),
(2, 3, 2, 3, '2023-06-16', '11:30:00', 35.00, 'Awaiting Confirmation'),
(3, 4, 3, 4, '2023-06-17', '09:00:00', 65.00, 'In Progress'),
(4, 5, 4, 5, '2023-06-18', '14:00:00', 85.00, 'Completed'),
(5, 2, 5, 3, '2023-06-19', '15:30:00', 120.00, 'Awaiting Pickup');



---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------
-- Employee
-----------------------------------------------------
SELECT * FROM Employee;

-----------------------------------------------------
-- Customer
-----------------------------------------------------
SELECT * FROM Customer;

-----------------------------------------------------
-- Vehicle
-----------------------------------------------------
SELECT * FROM Vehicle;

-----------------------------------------------------
-- Service
-----------------------------------------------------
SELECT * FROM Service;

-----------------------------------------------------
-- Fuel
-----------------------------------------------------
SELECT * FROM Fuel;

-----------------------------------------------------
-- Fuel Supplier
-----------------------------------------------------
SELECT * FROM FuelSupplier;

-----------------------------------------------------
-- Complaint
-----------------------------------------------------
SELECT * FROM Complaint;

-----------------------------------------------------
-- Reply Complaint
-----------------------------------------------------
SELECT * FROM ReplyComplaint;

-----------------------------------------------------
-- Feedback
-----------------------------------------------------
SELECT * FROM Feedback;

-----------------------------------------------------
-- Employee Phone Numbers
-----------------------------------------------------
SELECT * FROM EmployeePhoneNumber;

-----------------------------------------------------
-- Customer Phone Numbers
-----------------------------------------------------
SELECT * FROM CustomerPhoneNumber;

-----------------------------------------------------
-- Fuel Purchase
-----------------------------------------------------
SELECT * FROM FuelPurchase;

-----------------------------------------------------
-- Fuel Supply
-----------------------------------------------------
SELECT * FROM FuelSupply;

-----------------------------------------------------
-- Service Booking
-----------------------------------------------------
SELECT * FROM ServiceBooking;



---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------


-----------------------------------------------------
-- Fuel Purchase Trigger
-----------------------------------------------------
GO
CREATE TRIGGER AfterFuelPurchaseUpdateStock
    ON FuelPurchase
    AFTER INSERT
    AS
BEGIN
    SET NOCOUNT ON;

    -- Update the fuel stock in the Fuel table
    UPDATE f
    SET f.Quantity = f.Quantity - i.Quantity
    FROM Fuel AS f
             INNER JOIN inserted AS i ON f.FuelID = i.FuelID;

    PRINT 'Fuel Level Updated...!';

    -- Show updated fuel details
    SELECT f.*
    FROM Fuel AS f
             INNER JOIN inserted AS i ON f.FuelID = i.FuelID;
END;
GO

-----------------------------------------------------
-- Fuel Supply Trigger
-----------------------------------------------------
GO
CREATE TRIGGER update_fuel_after_supply
    ON FuelSupply
    AFTER INSERT
    AS
BEGIN
    UPDATE Fuel
    SET Quantity = Fuel.Quantity + inserted.Quantity
    FROM Fuel
             JOIN inserted ON Fuel.FuelID = inserted.FuelID;
END;
GO

-----------------------------------------------------
-- Customer Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_customer_details
    ON Customer
    INSTEAD OF DELETE
    AS
BEGIN
    -- Delete phone numbers
    DELETE FROM CustomerPhoneNumber
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set fuel purchase customer id to NULL
    UPDATE FuelPurchase
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set Feedback customer id to NULL
    UPDATE Feedback
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set Complaint customer id to NULL
    UPDATE Complaint
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set Service Booking customer id to NULL
    UPDATE ServiceBooking
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Delete vehicles belonging to customer
    DELETE FROM Vehicle
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Finally delete the customer
    DELETE FROM Customer
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Employee Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_employee_details
    ON Employee
    INSTEAD OF DELETE
    AS
BEGIN
    -- Update reply complaint employee id
    UPDATE ReplyComplaint
    SET StaffID = NULL
    WHERE StaffID IN (SELECT EmployeeID FROM DELETED);

    -- Update Service Booking employee id
    UPDATE ServiceBooking
    SET StaffID = NULL
    WHERE StaffID IN (SELECT EmployeeID FROM DELETED);

    -- Delete phone numbers
    DELETE FROM EmployeePhoneNumber
    WHERE EmployeeID IN (SELECT EmployeeID FROM DELETED);

    -- Finally delete the employee
    DELETE FROM Employee
    WHERE EmployeeID IN (SELECT EmployeeID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Fuel Supplier Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_fuel_supply_details
    ON FuelSupplier
    INSTEAD OF DELETE
    AS
BEGIN
    -- Update fuel supply supplier id
    UPDATE FuelSupply
    SET SupplierID = NULL
    WHERE SupplierID IN (SELECT SupplierID FROM DELETED);

    -- Finally delete the fuel supplier
    DELETE FROM FuelSupplier
    WHERE SupplierID IN (SELECT SupplierID FROM DELETED);
END;
GO
-----------------------------------------------------
-- Vehicle Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_vehicle_details
    ON Vehicle
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set fuel purchase vehicle id to NULL
    UPDATE FuelPurchase
    SET VehicleID = NULL
    WHERE VehicleID IN (SELECT VehicleID FROM DELETED);

    -- Set Service Booking vehicle id to NULL
    UPDATE ServiceBooking
    SET VehicleID = NULL
    WHERE VehicleID IN (SELECT VehicleID FROM DELETED);

    -- Finally delete the vehicle
    DELETE FROM Vehicle
    WHERE VehicleID IN (SELECT VehicleID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Service Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_service_details
    ON Service
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set Service Booking service id to NULL
    UPDATE ServiceBooking
    SET ServiceID = NULL
    WHERE ServiceID IN (SELECT ServiceID FROM DELETED);

    -- Finally delete the service
    DELETE FROM Service
    WHERE ServiceID IN (SELECT ServiceID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Fuel Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_fuel_details
    ON Fuel
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set fuel purchase fuel id to NULL
    UPDATE FuelPurchase
    SET FuelID = NULL
    WHERE FuelID IN (SELECT FuelID FROM DELETED);

    -- Set fuel supply fuel id to NULL
    UPDATE FuelSupply
    SET FuelID = NULL
    WHERE FuelID IN (SELECT FuelID FROM DELETED);

    -- Finally delete the fuel
    DELETE FROM Fuel
    WHERE FuelID IN (SELECT FuelID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Complaint Delete Trigger
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_complaint_details
    ON Complaint
    INSTEAD OF DELETE
    AS
BEGIN
    -- Delete related replies
    DELETE FROM ReplyComplaint
    WHERE ComplaintID IN (SELECT ComplaintID FROM DELETED);

    -- Finally delete the complaint
    DELETE FROM Complaint
    WHERE ComplaintID IN (SELECT ComplaintID FROM DELETED);
END;
GO