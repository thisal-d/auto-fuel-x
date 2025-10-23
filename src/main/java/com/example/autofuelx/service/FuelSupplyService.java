package com.example.autofuelx.service;

import com.example.autofuelx.dao.FuelSupplyDAO;
import com.example.autofuelx.model.FuelSupply;


public class FuelSupplyService {

    private final FuelSupplyDAO fuelSupplyDAO;

    public FuelSupplyService() {
        fuelSupplyDAO  = new FuelSupplyDAO();
    }

    public void addFuelSupply(FuelSupply fs) {
        boolean success = fuelSupplyDAO.addFuelSupply(fs);
        System.out.println("Fuel Supply Adding : " + success);
    }

    //    public List<FuelSupply> getAllFuelSupplies() {
    //        return fuelSupplyDAO.getAllFuelSupplies();
    //    }
}
