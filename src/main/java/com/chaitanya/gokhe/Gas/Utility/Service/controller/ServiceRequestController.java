package com.chaitanya.gokhe.Gas.Utility.Service.controller;

import com.chaitanya.gokhe.Gas.Utility.Service.model.ServiceRequest;
import com.chaitanya.gokhe.Gas.Utility.Service.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService requestService;

    @PostMapping("/user/create")
    public ResponseEntity<Object> createRequest(@RequestBody ServiceRequest serviceRequest, Principal principal){
        try {
            return requestService.createRequest(serviceRequest, principal.getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid request data", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Something went wrong", "message", e.getMessage()));
        }
    }

    @GetMapping("/user/my-requests")
    public ResponseEntity<Object> getUserRequests(Principal principal){
        return requestService.getUserRequests(principal.getName());
    }

    @GetMapping("/user/my-requests/{id}")
    public ResponseEntity<Object> getRequestByIdAndUser(@PathVariable int id, Principal principal){
        return requestService.getRequestByIdAndUser(id, principal.getName());
    }

    @GetMapping("admin/requests")
    public ResponseEntity<Object> getAllRequests(){
        return requestService.getAllRequests();
    }

    @GetMapping("admin/requests/{id}")
    public ResponseEntity<Object> getRequestById(@PathVariable int id){
        return requestService.getRequestById(id);
    }

    @PutMapping("/admin/requests/update/{id}")
    public ResponseEntity<Object> updateRequestStatus(@PathVariable int id, @RequestParam String status){
        try {
            return requestService.updateRequestStatus(id, status);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid request data", "message", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Request not found", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Something went wrong", "message", e.getMessage()));
        }
    }
}
