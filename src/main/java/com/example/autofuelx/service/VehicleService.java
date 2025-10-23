package com.example.autofuelx.service;

import com.example.autofuelx.dao.VehicleDAO;
import com.example.autofuelx.dto.VehicleSummaryDTO;
import com.example.autofuelx.model.Vehicle;

import java.util.List;

public class VehicleService {

    private final VehicleDAO vehicleDAO;

    public VehicleService() {
        vehicleDAO = new VehicleDAO();
    }

    public void addVehicle(Vehicle vehicle) {
        boolean success = vehicleDAO.addVehicle(vehicle);
        System.out.println("Vehicle added: " + vehicle.getVehicleID() + " : " + success);
    }

    public List<Vehicle> getVehiclesByCustomerID(int customerID) {
        return vehicleDAO.getVehiclesByCustomerID(customerID);
    }

    public void updateVehicle(Vehicle vehicle) {
        boolean success = vehicleDAO.updateVehicle(vehicle);
        System.out.println();
    }

    public void deleteVehicle(int vehicleID, int customerID) {
        boolean success = vehicleDAO.deleteVehicle(vehicleID, customerID);
        System.out.println("Vehicle deleted: " + vehicleID + " : " + success);
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
