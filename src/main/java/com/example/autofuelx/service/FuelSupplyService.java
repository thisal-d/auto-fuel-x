package com.example.autofuelx.service;

import com.example.autofuelx.dao.FuelSupplyDAO;
import com.example.autofuelx.model.FuelSupply;

import java.util.List;

public class FuelSupplyService {

    private final FuelSupplyDAO fuelSupplyDAO;

    public FuelSupplyService() {
        fuelSupplyDAO  = new FuelSupplyDAO();
    }

    public boolean addFuelSupply(FuelSupply fs) {
        return fuelSupplyDAO.addFuelSupply(fs);
    }

    public List<FuelSupply> getAllFuelSupplies() {
        return fuelSupplyDAO.getAllFuelSupplies();
    }
}
