package com.chaitanya.gokhe.Gas.Utility.Service.repository;

import com.chaitanya.gokhe.Gas.Utility.Service.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRequestRepo extends JpaRepository<ServiceRequest, Integer> {
    List<ServiceRequest> findByUserUsername(String username);

    Optional<ServiceRequest> findByIdAndUserUsername(int id, String username);
}
