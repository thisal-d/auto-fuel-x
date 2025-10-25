package com.example.autofuelx.service;

import com.example.autofuelx.dao.ServiceDAO;
import com.example.autofuelx.model.Service;

import java.util.List;

public class ServiceManager {
    ServiceDAO serviceDAO;

    public ServiceManager() {
        serviceDAO = new ServiceDAO();
    }

    public List<Service> getAllServices() {
        return serviceDAO.getAllServices();
    }

    public Service getServiceByID(int serviceID) {
        return serviceDAO.getServiceById(serviceID);
    }

    public void addService(Service service) {
        boolean success = serviceDAO.insertService(service);
        System.out.println("Service" + service.getServiceID() + " Inserted : " + success);
    }

    public void updateService(Service service) {
        boolean success = serviceDAO.updateService(service);
        System.out.println("Service" + service.getServiceID() + " Updating : " + success);
    }

    public void deleteService(int serviceID) {
        boolean success = serviceDAO.deleteService(serviceID);
        System.out.println("Service" + serviceID + " Delete : " + success);
    }

    public List<Service> getFilteredServices(Double minCost, Double maxCost, String keyword) {
        return serviceDAO.getFilteredServices(minCost, maxCost, keyword);
    }

}
