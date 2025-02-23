package com.chaitanya.gokhe.Gas.Utility.Service.service;

import com.chaitanya.gokhe.Gas.Utility.Service.model.ServiceRequest;
import com.chaitanya.gokhe.Gas.Utility.Service.model.Users;
import com.chaitanya.gokhe.Gas.Utility.Service.repository.ServiceRequestRepo;
import com.chaitanya.gokhe.Gas.Utility.Service.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepo serviceRequestRepo;

    @Autowired
    private UsersRepo usersRepo;

    public ResponseEntity<Object> createRequest(ServiceRequest serviceRequest, String username) {
        if (serviceRequest.getRequestType() == null || serviceRequest.getRequestType().trim().isEmpty()) {
            throw new IllegalArgumentException("RequestType is required");
        }
        if (serviceRequest.getDescription() == null || serviceRequest.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }

        Users user = usersRepo.findByUsername(username);
        serviceRequest.setUser(user);
        serviceRequest.setStatus("PENDING");
        serviceRequest.setCreatedAt(LocalDateTime.now());
        serviceRequest.setUpdatedAt(null);
        ServiceRequest newServiceRequest = serviceRequestRepo.save(serviceRequest);
        return new ResponseEntity<>(newServiceRequest, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> getUserRequests(String username) {
        return new ResponseEntity<>(serviceRequestRepo.findByUserUsername(username),HttpStatus.OK);
    }

    public ResponseEntity<Object> getRequestByIdAndUser(int id, String username) {
        Optional<ServiceRequest> serviceRequest = serviceRequestRepo.findByIdAndUserUsername(id, username);
        if (serviceRequest.isPresent()){
            return new ResponseEntity<>(serviceRequest,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getAllRequests() {
        return new ResponseEntity<>(serviceRequestRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Object> getRequestById(int id) {
        Optional<ServiceRequest> serviceRequest = serviceRequestRepo.findById(id);
        if (serviceRequest.isPresent()){
            return new ResponseEntity<>(serviceRequest,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> updateRequestStatus(int id, String status) {
        Optional<ServiceRequest> optionalRequest = serviceRequestRepo.findById(id);
        if (optionalRequest.isEmpty()) {
            throw new NoSuchElementException("Request with ID " + id + " not found");
        }

        List<String> validStatuses = Arrays.asList("PENDING", "IN_PROGRESS", "RESOLVED");
        String normalizedStatus = status.trim().toUpperCase();

        if (!validStatuses.contains(normalizedStatus)) {
            throw new IllegalArgumentException("Invalid status. Allowed values: Pending, In_Progress, Resolved");
        }

        ServiceRequest serviceRequest = optionalRequest.get();
        serviceRequest.setStatus(status);
        serviceRequest.setUpdatedAt(LocalDateTime.now());

        return new ResponseEntity<>(serviceRequestRepo.save(serviceRequest),HttpStatus.OK);
    }
}
