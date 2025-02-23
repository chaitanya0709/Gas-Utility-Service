package com.chaitanya.gokhe.Gas.Utility.Service.repository;

import com.chaitanya.gokhe.Gas.Utility.Service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
