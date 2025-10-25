-----------------------------------------------------
-- Trigger: AfterFuelPurchaseUpdateStock
-- Updates the fuel quantity in Fuel table whenever a new fuel purchase is made.
-- Ensures stock is decremented correctly and shows updated fuel details.
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
-- Trigger: update_fuel_after_supply
-- Increments the fuel stock in Fuel table whenever a new fuel supply is added.
-----------------------------------------------------
GO
CREATE TRIGGER update_fuel_after_supply
    ON FuelSupply
    AFTER INSERT
    AS
BEGIN
    -- Update Fuel quantity by adding supplied amount
    UPDATE Fuel
    SET Quantity = Fuel.Quantity + inserted.Quantity
    FROM Fuel
             JOIN inserted ON Fuel.FuelID = inserted.FuelID;
END;
GO

-----------------------------------------------------
-- Trigger: delete_customer_details
-- Handles deletion of a customer.
-- Deletes related phone numbers and vehicles, sets related foreign keys to NULL,
-- and finally deletes the customer.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_customer_details
    ON Customer
    INSTEAD OF DELETE
    AS
BEGIN
    -- Delete customer phone numbers
    DELETE
    FROM CustomerPhoneNumber
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set related FuelPurchase CustomerID to NULL
    UPDATE FuelPurchase
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set related Feedback CustomerID to NULL
    UPDATE Feedback
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set related Complaint CustomerID to NULL
    UPDATE Complaint
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Set related ServiceBooking CustomerID to NULL
    UPDATE ServiceBooking
    SET CustomerID = NULL
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Delete vehicles belonging to the customer
    DELETE
    FROM Vehicle
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);

    -- Finally delete the customer record
    DELETE
    FROM Customer
    WHERE CustomerID IN (SELECT CustomerID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Trigger: delete_employee_details
-- Handles deletion of an employee.
-- Updates related ReplyComplaint and ServiceBooking, deletes phone numbers,
-- and finally deletes the employee.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_employee_details
    ON Employee
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set StaffID to NULL in ReplyComplaint
    UPDATE ReplyComplaint
    SET StaffID = NULL
    WHERE StaffID IN (SELECT EmployeeID FROM DELETED);

    -- Set StaffID to NULL in ServiceBooking
    UPDATE ServiceBooking
    SET StaffID = NULL
    WHERE StaffID IN (SELECT EmployeeID FROM DELETED);

    -- Delete employee phone numbers
    DELETE
    FROM EmployeePhoneNumber
    WHERE EmployeeID IN (SELECT EmployeeID FROM DELETED);

    -- Finally delete the employee record
    DELETE
    FROM Employee
    WHERE EmployeeID IN (SELECT EmployeeID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Trigger: delete_fuel_supply_details
-- Handles deletion of a fuel supplier.
-- Sets SupplierID to NULL in FuelSupply and deletes the supplier record.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_fuel_supply_details
    ON FuelSupplier
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set SupplierID to NULL in FuelSupply
    UPDATE FuelSupply
    SET SupplierID = NULL
    WHERE SupplierID IN (SELECT SupplierID FROM DELETED);

    -- Finally delete the supplier record
    DELETE
    FROM FuelSupplier
    WHERE SupplierID IN (SELECT SupplierID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Trigger: delete_vehicle_details
-- Handles deletion of a vehicle.
-- Sets VehicleID to NULL in FuelPurchase and ServiceBooking, then deletes the vehicle.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_vehicle_details
    ON Vehicle
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set VehicleID to NULL in FuelPurchase
    UPDATE FuelPurchase
    SET VehicleID = NULL
    WHERE VehicleID IN (SELECT VehicleID FROM DELETED);

    -- Set VehicleID to NULL in ServiceBooking
    UPDATE ServiceBooking
    SET VehicleID = NULL
    WHERE VehicleID IN (SELECT VehicleID FROM DELETED);

    -- Finally delete the vehicle record
    DELETE
    FROM Vehicle
    WHERE VehicleID IN (SELECT VehicleID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Trigger: delete_service_details
-- Handles deletion of a service.
-- Sets ServiceID to NULL in ServiceBooking, then deletes the service record.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_service_details
    ON Service
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set ServiceID to NULL in ServiceBooking
    UPDATE ServiceBooking
    SET ServiceID = NULL
    WHERE ServiceID IN (SELECT ServiceID FROM DELETED);

    -- Finally delete the service record
    DELETE
    FROM Service
    WHERE ServiceID IN (SELECT ServiceID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Trigger: delete_fuel_details
-- Handles deletion of a fuel type.
-- Sets FuelID to NULL in FuelPurchase and FuelSupply, then deletes the fuel record.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_fuel_details
    ON Fuel
    INSTEAD OF DELETE
    AS
BEGIN
    -- Set FuelID to NULL in FuelPurchase
    UPDATE FuelPurchase
    SET FuelID = NULL
    WHERE FuelID IN (SELECT FuelID FROM DELETED);

    -- Set FuelID to NULL in FuelSupply
    UPDATE FuelSupply
    SET FuelID = NULL
    WHERE FuelID IN (SELECT FuelID FROM DELETED);

    -- Finally delete the fuel record
    DELETE
    FROM Fuel
    WHERE FuelID IN (SELECT FuelID FROM DELETED);
END;
GO

-----------------------------------------------------
-- Trigger: delete_complaint_details
-- Handles deletion of a complaint.
-- Deletes all related replies, then deletes the complaint itself.
-----------------------------------------------------
GO
CREATE OR ALTER TRIGGER delete_complaint_details
    ON Complaint
    INSTEAD OF DELETE
    AS
BEGIN
    -- Delete all related reply complaints
    DELETE
    FROM ReplyComplaint
    WHERE ComplaintID IN (SELECT ComplaintID FROM DELETED);

    -- Finally delete the complaint record
    DELETE
    FROM Complaint
    WHERE ComplaintID IN (SELECT ComplaintID FROM DELETED);
END;
GO
