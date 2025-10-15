package com.example.autofuelx.service;

import com.example.autofuelx.dao.VehicleDAO;
import com.example.autofuelx.dto.VehicleSummaryDTO;
import com.example.autofuelx.model.Vehicle;

import java.util.List;

public class VehicleService {

    private VehicleDAO vehicleDAO;

    public VehicleService() {
        vehicleDAO = new VehicleDAO();
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    public List<Vehicle> getVehiclesByCustomerID(int customerID) {
        return vehicleDAO.getVehiclesByCustomerID(customerID);
    }

    public boolean updateVehicle(Vehicle vehicle) {
        return vehicleDAO.updateVehicle(vehicle);
    }

    public boolean deleteVehicle(int vehicleID, int customerID) {
        return vehicleDAO.deleteVehicle(vehicleID, customerID);
    }

    public Vehicle getVehicleByID(int vehicleID) {
        return vehicleDAO.getVehicleByID(vehicleID);
    }

    public Vehicle getVehicleByPlateNo(String plateNo) {
        return vehicleDAO.getVehicleByPlateNo(plateNo);
    }

    public List<String> getVehicleNamesByCustomerID(int customerID) {
        return vehicleDAO.getVehicleNamesByCustomerID(customerID);
    }

    public List<String> getVehicleTypesByCustomerID(int customerID) {
        return vehicleDAO.getVehicleTypesByCustomerID(customerID);
    }

    public List<VehicleSummaryDTO> getVehicleSummaryByCustomerID(int customerID) {
        return vehicleDAO.getVehicleSummaryByCustomerID(customerID);
    }

}
