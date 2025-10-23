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

    public void addSupplier(FuelSupplier supplier) {
        boolean success = fuelSupplierDAO.addSupplier(supplier);
        System.out.println("Fuel Supplier Added : " + success);
    }

    public List<FuelSupplier> getAllSuppliers() {
        return fuelSupplierDAO.getAllSuppliers();
    }

    public FuelSupplier getSupplierById(int supplierId) {
        return fuelSupplierDAO.getSupplierById(supplierId);
    }

    public void updateSupplier(FuelSupplier supplier) {
        boolean success = fuelSupplierDAO.updateSupplier(supplier);
        System.out.println("Fuel Supplier Updated : " + success);
    }

    public void deleteSupplier(int supplierId) {
        boolean success = fuelSupplierDAO.deleteSupplier(supplierId);
        System.out.println("Fuel Supplier Deleted : " + success);
    }
}