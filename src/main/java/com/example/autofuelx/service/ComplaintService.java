package com.example.autofuelx.service;



import com.example.autofuelx.dao.ComplaintDAO;
import com.example.autofuelx.dto.ComplaintReplyDTO;
import com.example.autofuelx.dto.ComplaintSummaryDTO;
import com.example.autofuelx.model.Complaint;

import java.util.List;


public class ComplaintService {
    ComplaintDAO complaintDAO;

    public ComplaintService(){
        complaintDAO = new ComplaintDAO();
    }

    public void submitComplaint(Complaint complaint) {
        complaintDAO.addComplaint(complaint);
    }

    public void updateComplaint(Complaint complaint) {
        complaintDAO.updateComplaint(complaint);
    }

    public void deleteComplaint(int complaintID) {
        complaintDAO.deleteComplaint(complaintID);
    }

    public void updateComplaintStatus(int complaintID, String status) {
        boolean success = complaintDAO.updateComplaintStatus(complaintID, status);
        if (success) {
            System.out.println("Complaint ID " + complaintID + " updated to status: " + status);
        } else {
            System.err.println("Failed to update complaint ID " + complaintID);
        }
    }

    public List<Complaint> getComplaintsByCustomerID(int customerID) {
        return complaintDAO.getComplaintsByCustomerId(customerID);
    }

    public Complaint getComplaintByComplaintID(int complaintID) {
        return complaintDAO.getComplaintById(complaintID);
    }

    public List<ComplaintReplyDTO> getComplaintsWithReplyByCustomerId(int customerID) {
        return complaintDAO.getComplaintReplyDTOsByCustomerID(customerID);
    }

    public List<ComplaintReplyDTO> getComplaintsWithReplyByCustomerId(int customerID, String status) {
        return complaintDAO.getComplaintReplyDTOsByCustomerID(customerID, status);
    }

    public List<ComplaintReplyDTO> getComplaintsWithReplyByStatus(String status) {
        return complaintDAO.getComplaintReplyDTOsByStatus(status);
    }

    public ComplaintReplyDTO getComplaintReplyDTOByComplaintID(int complaintID) {
        return complaintDAO.getComplaintReplyDTOByComplaintID(complaintID);
    }

    public List<ComplaintReplyDTO> getComplaintReplyDTOsFiltered(String keyword,
                                                                String lastUpdateDate,
                                                                String customerEmail,
                                                                String status) {
        return complaintDAO.getComplaintReplyDTOsFiltered(keyword, lastUpdateDate, customerEmail, status);
    }


    public ComplaintSummaryDTO getComplaintSummaryByCustomerID(int customerID) {
        return  complaintDAO.getComplaintSummaryByCustomerId(customerID);
    }

}
