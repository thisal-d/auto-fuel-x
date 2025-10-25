-----------------------------------------------------
-- Insert initial employees
-- Adds default employees including admin, refuel cashier, service center staff, and customer care officer
-----------------------------------------------------
INSERT INTO Employee (FirstName, LastName, DateOfBirth, Salary, Status, HireDate, AddressNo, AddressLane, AddressArea,
                      Email, Password, Type, SkillSet, Role, Shift)
VALUES ('Kevin', 'Smith', '1985-05-15', 50000.00, 'Active', '2020-01-10', '123', 'Main Street', 'Colombo 03',
        'admin@station.com', 'admin123', 'Admin', NULL, 'Super', NULL),
       ('Arwin', 'Johnson', '1990-08-22', 45000.00, 'Active', '2021-03-15', '45/A', 'Galle Road', 'Mount Lavinia',
        'r.cashier@station.com', 'rcashier123', 'Refuel Cashier', NULL, NULL, 'Day'),
       ('Mark', 'Adams', '1988-11-03', 48000.00, 'Active', '2019-07-22', '789', 'Kandy Road', 'Kadawatha',
        's.center@station.com', 'scenter123', 'Service Center Staff', 'Engine Diagnostics, Brake Repair', NULL, NULL),
       ('Savin', 'Prabod', '1992-02-28', 42000.00, 'Inactive', '2022-05-10', '101', 'Marine Drive', 'Negombo',
        'c.care@station.com', 'ccare123', 'Customer Care Officer', NULL, NULL, NULL);

-----------------------------------------------------
-- Insert initial services
-- Adds sample services available at the station with descriptions and costs
-----------------------------------------------------
INSERT INTO Service (Type, Description, Cost)
VALUES ('Oil Change', 'Standard oil change service', 45.00),
       ('Tire Rotation', 'Rotate and balance all four tires', 35.00),
       ('Brake Inspection', 'Complete brake system inspection', 65.00),
       ('Engine Diagnostic', 'Full engine diagnostic test', 85.00),
       ('Transmission Service', 'Transmission fluid change and filter', 120.00);
