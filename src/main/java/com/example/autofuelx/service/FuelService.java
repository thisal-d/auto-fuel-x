package com.example.autofuelx.service;

import com.example.autofuelx.dao.FuelDAO;
import com.example.autofuelx.model.Fuel;

import java.util.List;

public class FuelService {
    private final FuelDAO fuelDAO;

    public FuelService() {
        fuelDAO = new FuelDAO();
    }

    // Add fuel
    public void addFuel(Fuel fuel) {
        fuelDAO.addFuel(fuel);
    }

    //    // Get fuel by ID
    //    public Fuel getFuelById(int fuelID) {
    //        return fuelDAO.getFuelById(fuelID);
    //    }

    // Get all fuels
    public List<Fuel> getAllFuels() {
        return fuelDAO.getAllFuels();
    }

    //    // Update fuel
    //    public void updateFuel(Fuel fuel) {
    //        fuelDAO.updateFuel(fuel);
    //    }

    //    // Delete fuel
    //    public void deleteFuel(int fuelID) {
    //        fuelDAO.deleteFuel(fuelID);
    //    }

}
