package com.chaitanya.gokhe.Gas.Utility.Service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @JsonIgnore
    private Users user; // Link request to customer

    private String requestType;
    private String description;
    private String status; // PENDING, IN_PROGRESS, RESOLVED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

