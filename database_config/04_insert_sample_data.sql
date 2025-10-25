-----------------------------------------------------
-- Insert sample employees
-- Adds multiple employees with various roles, statuses, and skills
-----------------------------------------------------
INSERT INTO Employee (FirstName, LastName, DateOfBirth, Salary, Status, HireDate, AddressNo, AddressLane, AddressArea,
                      Email, Password, Type, SkillSet, Role, Shift)
VALUES ('John', 'Smith', '1985-05-15', 50000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Colombo 03',
        'john.smith@example.com', 'password123', 'Admin', NULL, 'Super', NULL),
       ('Abishak', 'Kumar', '1985-05-15', 50000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Colombo 03',
        'kumar@gmail.com', 'admin123', 'Admin', NULL, 'Normal', NULL),
       ('Emily', 'Johnson', '1990-08-22', 45000.00, 'Active', '2021-03-15', '45/A', 'Galle Road', 'Mount Lavinia',
        'emily.j@example.com', 'securepass456', 'Refuel Cashier', NULL, NULL, 'Day'),
       ('Michael', 'Williams', '1988-11-03', 48000.00, 'Active', '2019-07-22', '789', 'Kandy Road', 'Kadawatha',
        'm.williams@example.com', 'mypass789', 'Service Center Staff', 'Engine Diagnostics, Brake Repair', NULL, NULL),
       ('Sarah', 'Brown', '1992-02-28', 42000.00, 'Inactive', '2022-05-10', '101', 'Marine Drive', 'Negombo',
        'sarah.b@example.com', 'sarahpass321', 'Customer Care Officer', NULL, NULL, NULL),
       ('David', 'Jones', '1987-09-14', 52000.00, 'Active', '2018-11-05', '202', 'High Level Road', 'Maharagama',
        'david.jones@example.com', 'djpass654', 'Service Center Staff', 'Transmission Service, Electrical Systems',
        NULL, NULL);

-----------------------------------------------------
-- Insert sample customers
-- Adds multiple customers with login credentials
-----------------------------------------------------
INSERT INTO Customer (Email, FirstName, LastName, Password)
VALUES ('alice@example.com', 'Alice', 'Anderson', 'alicepass123'),
       ('bob@example.com', 'Bob', 'Baker', 'bobpass456'),
       ('charlie@example.com', 'Charlie', 'Clark', 'charliepass789'),
       ('diana@example.com', 'Diana', 'Davis', 'dianapass321'),
       ('edward@example.com', 'Edward', 'Evans', 'edwardpass654'),
       ('customer@station.com', 'Customer', 'Station', 'customer123');

-----------------------------------------------------
-- Insert sample vehicles
-- Links vehicles to customers with plate numbers, type, model, color, and registration dates
-----------------------------------------------------
INSERT INTO Vehicle (PlateNumber, Type, Model, Color, CustomerID, RegistrationDate)
VALUES ('ABC123', 'Car', 'Toyota Camry', 'Blue', 1, '2020-01-15'),
       ('XYZ789', 'SUV', 'Honda CR-V', 'Red', 1, '2021-03-20'),
       ('DEF456', 'Car', 'Ford Focus', 'Black', 2, '2019-07-10'),
       ('GHI012', 'Motorcycle', 'Yamaha R6', 'Green', 3, '2022-05-25'),
       ('JKL345', 'Truck', 'Ford F-150', 'White', 4, '2020-11-08');

-----------------------------------------------------
-- Insert sample services
-- Adds common services offered with descriptions and costs
-----------------------------------------------------
INSERT INTO Service (Type, Description, Cost)
VALUES ('Oil Change', 'Standard oil change service', 45.00),
       ('Tire Rotation', 'Rotate and balance all four tires', 35.00),
       ('Brake Inspection', 'Complete brake system inspection', 65.00),
       ('Engine Diagnostic', 'Full engine diagnostic test', 85.00),
       ('Transmission Service', 'Transmission fluid change and filter', 120.00);

-----------------------------------------------------
-- Insert sample fuels
-- Adds fuel types with initial quantities and cost per liter
-----------------------------------------------------
INSERT INTO Fuel (Type, Quantity, CostPerLiter)
VALUES ('Petrol-95', 5000.00, 370),
       ('Petrol-92', 5000.00, 350),
       ('Super Diesel', 3000.00, 255),
       ('Diesel', 4000.00, 245);

-----------------------------------------------------
-- Insert sample fuel suppliers
-- Adds fuel supplier information with contact numbers
-----------------------------------------------------
INSERT INTO FuelSupplier (Name, PhoneNumber)
VALUES ('Shell Oil Company', '555-0101'),
       ('Exxon Mobil', '555-0102'),
       ('Chevron Corporation', '555-0103'),
       ('BP Oil', '555-0104'),
       ('Sunoco', '555-0105');

-----------------------------------------------------
-- Insert sample complaints
-- Stores complaints made by customers with title, description, status, and timestamps
-----------------------------------------------------
INSERT INTO Complaint (CustomerID, Title, Description, Status, CreatedDate, CreatedTime)
VALUES (1, 'Poor Service Quality', 'The service was not up to the expected standard', 'Sent', '2023-01-15', '10:30:00'),
       (2, 'Long Waiting Time', 'Had to wait for more than 2 hours for a simple service', 'Seen', '2023-02-20',
        '14:45:00'),
       (3, 'Incorrect Billing', 'Was charged for services I did not receive', 'Sent', '2023-03-10', '09:15:00'),
       (4, 'Rude Staff Behavior', 'The staff member was very unprofessional', 'Seen', '2023-04-05', '16:20:00'),
       (5, 'Facility Cleanliness', 'The service center was not clean and organized', 'Sent', '2023-05-12', '11:30:00');

-----------------------------------------------------
-- Insert sample complaint replies
-- Stores staff responses to complaints with status and timestamps
-----------------------------------------------------
INSERT INTO ReplyComplaint (StaffID, ComplaintID, Title, Description, Status, CreatedDate, CreatedTime)
VALUES (1, 1, 'Re: Poor Service Quality', 'We apologize for the inconvenience. We will improve our service quality.',
        'Sent', '2023-01-16', '09:00:00'),
       (2, 2, 'Re: Long Waiting Time', 'We are working on reducing waiting times. Thank you for your patience.', 'Seen',
        '2023-02-21', '11:30:00'),
       (3, 3, 'Re: Incorrect Billing', 'We have corrected the billing error. Please check your updated invoice.',
        'Sent', '2023-03-11', '14:15:00'),
       (4, 4, 'Re: Rude Staff Behavior', 'We apologize for the behavior of our staff. We will take appropriate action.',
        'Seen', '2023-04-06', '10:45:00'),
       (5, 5, 'Re: Facility Cleanliness',
        'We have addressed the cleanliness issues. Thank you for bringing this to our attention.', 'Sent', '2023-05-13',
        '13:20:00');

-----------------------------------------------------
-- Insert sample feedback
-- Stores ratings and comments from customers
-----------------------------------------------------
INSERT INTO Feedback (Rate, Message, CreatedDate, CreatedTime, CustomerID)
VALUES (5, 'Excellent service! Very satisfied with the work done.', '2023-01-20', '15:30:00', 1),
       (4, 'Good service overall, but the waiting time was a bit long.', '2023-02-25', '11:45:00', 2),
       (3, 'Average service. Nothing exceptional but nothing terrible either.', '2023-03-15', '09:20:00', 3),
       (5, 'Outstanding service! The staff was very professional and helpful.', '2023-04-10', '14:10:00', 4),
       (2, 'Not satisfied with the service. The issue was not resolved properly.', '2023-05-18', '16:35:00', 5);

-----------------------------------------------------
-- Insert sample employee phone numbers
-- Allows multiple phone numbers per employee
-----------------------------------------------------
INSERT INTO EmployeePhoneNumber (EmployeeID, PhoneNumber)
VALUES (1, '555-1001'),
       (1, '555-1002'),
       (2, '555-2001'),
       (3, '555-3001'),
       (3, '555-3002'),
       (4, '555-4001'),
       (5, '555-5001'),
       (5, '555-5002');

-----------------------------------------------------
-- Insert sample customer phone numbers
-- Allows multiple phone numbers per customer
-----------------------------------------------------
INSERT INTO CustomerPhoneNumber (CustomerID, PhoneNumber)
VALUES (1, '555-1111'),
       (1, '555-1112'),
       (2, '555-2221'),
       (3, '555-3331'),
       (3, '555-3332'),
       (4, '555-4441'),
       (5, '555-5551'),
       (5, '555-5552');

-----------------------------------------------------
-- Insert sample fuel purchases
-- Records fuel purchased by customers for their vehicles
-----------------------------------------------------
INSERT INTO FuelPurchase (CustomerID, FuelID, VehicleID, Quantity, TotalCost, PurchaseDate, PurchaseTime)
VALUES (1, 1, 1, 20.00, 27.00, '2023-01-15', '10:30:00'),
       (1, 2, 2, 15.00, 23.25, '2023-02-20', '14:45:00'),
       (2, 1, 3, 25.00, 33.75, '2023-03-10', '09:15:00'),
       (3, 3, 4, 30.00, 43.50, '2023-04-05', '16:20:00'),
       (4, 2, 5, 18.00, 27.90, '2023-05-12', '11:30:00');

-----------------------------------------------------
-- Insert sample fuel supplies
-- Records fuel supplied by suppliers to update stock
-----------------------------------------------------
INSERT INTO FuelSupply (SupplierID, FuelID, Quantity, SupplyDate, SupplyTime)
VALUES (1, 1, 1000.00, '2023-01-01', '08:00:00'),
       (1, 2, 800.00, '2023-01-01', '08:30:00'),
       (2, 1, 1200.00, '2023-02-01', '09:00:00'),
       (2, 3, 900.00, '2023-02-01', '09:30:00'),
       (3, 2, 700.00, '2023-03-01', '10:00:00'),
       (3, 4, 500.00, '2023-03-01', '10:30:00'),
       (4, 3, 1100.00, '2023-04-01', '11:00:00'),
       (5, 4, 400.00, '2023-05-01', '11:30:00');

-----------------------------------------------------
-- Insert sample service bookings
-- Records bookings by customers with linked services, staff, and status
-----------------------------------------------------
INSERT INTO ServiceBooking (CustomerID, VehicleID, ServiceID, StaffID, BookingDate, BookingTime, TotalCost, Status)
VALUES (1, 1, 1, 3, '2023-06-15', '10:00:00', 45.00, 'Confirmed'),
       (2, 3, 2, 3, '2023-06-16', '11:30:00', 35.00, 'Awaiting Confirmation'),
       (3, 4, 3, 4, '2023-06-17', '09:00:00', 65.00, 'In Progress'),
       (4, 5, 4, 5, '2023-06-18', '14:00:00', 85.00, 'Completed'),
       (5, 2, 5, 3, '2023-06-19', '15:30:00', 120.00, 'Awaiting Pickup');
