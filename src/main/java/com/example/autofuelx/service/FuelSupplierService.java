package com.example.autofuelx.service;

import com.example.autofuelx.dao.FuelSupplierDAO;
import com.example.autofuelx.model.FuelSupplier;

import java.util.List;

public class FuelSupplierService {

    private final FuelSupplierDAO fuelSupplierDAO;

    // Constructor to initialize the DAO
    public FuelSupplierService() {
        this.fuelSupplierDAO = new FuelSupplierDAO();
    }


    public boolean addSupplier(FuelSupplier supplier) {
        return fuelSupplierDAO.addSupplier(supplier);
    }


    public List<FuelSupplier> getAllSuppliers() {
        return fuelSupplierDAO.getAllSuppliers();
    }

    public FuelSupplier getSupplierById(int supplierId) {
        return fuelSupplierDAO.getSupplierById(supplierId);
    }


    public boolean updateSupplier(FuelSupplier supplier) {
        return fuelSupplierDAO.updateSupplier(supplier);
    }


    public boolean deleteSupplier(int supplierId) {
        return fuelSupplierDAO.deleteSupplier(supplierId);
    }
}