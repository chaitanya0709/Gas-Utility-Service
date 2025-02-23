package com.chaitanya.gokhe.Gas.Utility.Service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String role;        //Example: "ROLE_ADMIN", "ROLE_USER"
}
