package com.example.autofuelx.service;



import com.example.autofuelx.dao.ComplaintDAO;
import com.example.autofuelx.model.Complaint;

import java.util.List;


public class ComplaintService {
    ComplaintDAO complaintDAO;

    public ComplaintService(){
        complaintDAO = new ComplaintDAO();
    }

    public void submitComplaint(Complaint complaint) {
        complaintDAO.saveComplaint(complaint);
    }

    public void updateComplaint(Complaint complaint) {
        complaintDAO.updateComplaint(complaint);
    }

    public void deleteComplaint(int complaintID) {
        complaintDAO.deleteComplaint(complaintID);
    }

    public List<Complaint> getComplaintsByCustomerID(int customerID) {
        return complaintDAO.getComplaintsByCustomerID(customerID);
    }

    public Complaint getComplaintByID(int complaintID) {
        return complaintDAO.getComplaintByID(complaintID);
    }
}
