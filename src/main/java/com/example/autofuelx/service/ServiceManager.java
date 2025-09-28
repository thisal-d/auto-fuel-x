package com.example.autofuelx.service;

import com.example.autofuelx.dao.ServiceDAO;
import com.example.autofuelx.model.Service;

import java.util.List;

public class ServiceManager {
    ServiceDAO serviceDAO;

    public ServiceManager(){
        serviceDAO = new ServiceDAO();
    }

    public List<Service> getAllServices() {
        return serviceDAO.getAllServices();
    }

    public Service getServiceByID(int serviceID) {
        return serviceDAO.getServiceById(serviceID);
    }

    public boolean addService(Service service) {
        return serviceDAO.insertService(service);
    }

    public boolean updateService(Service service) {
        return serviceDAO.updateService(service);
    }

    public boolean deleteService(int serviceID) {
        return serviceDAO.deleteService(serviceID);
    }
}
