 package com.example.autofuelx.service;

import com.example.autofuelx.dao.FuelDAO;
import com.example.autofuelx.dao.FuelPurchaseDAO;
import com.example.autofuelx.model.Fuel;
import com.example.autofuelx.model.FuelPurchase;
import com.example.autofuelx.dto.FuelPurchaseDetailDTO;

import java.util.List;

public class FuelPurchaseService {
    private FuelPurchaseDAO fuelPurchaseDAO;
    private FuelDAO fuelDAO;

    public FuelPurchaseService() {
        fuelPurchaseDAO = new FuelPurchaseDAO();
        fuelDAO = new FuelDAO();
    }

    public void makePurchase(FuelPurchase purchase) {
        // Update fuel level
        Fuel fuel = fuelDAO.getFuelById(purchase.getFuelID());
        double fuelAmount = fuel.getQuantity() - purchase.getQuantity();
        fuel.setQuantity(fuelAmount);
        fuelDAO.updateFuel(fuel);

        fuelPurchaseDAO.insertFuelPurchase(purchase);
    }

    public List<FuelPurchaseDetailDTO> getFuelPurchaseDetailsByCustomerID(int customerID,
                                                                          String durationFilterStart,
                                                                          String durationFilterEnd,
                                                                          String vehicleTypeFilter,
                                                                          String vehicleFilter,
                                                                          String fuelTypeFilter) {
        return fuelPurchaseDAO.getFuelPurchaseDetailByCustomer(customerID, durationFilterStart, durationFilterEnd, vehicleTypeFilter, vehicleFilter, fuelTypeFilter );
    }

    public double getPurchaseCost(int fuelID, double quantity) {
        Fuel fuel = fuelDAO.getFuelById(fuelID); // DAO just returns price

        fuelDAO.updateFuel(fuel);
        return quantity * fuel.getCostPerLiter(); // business logic here
    }

}
