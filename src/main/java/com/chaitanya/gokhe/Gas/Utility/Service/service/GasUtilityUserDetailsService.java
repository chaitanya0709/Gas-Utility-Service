package com.chaitanya.gokhe.Gas.Utility.Service.service;

import com.chaitanya.gokhe.Gas.Utility.Service.model.UserPrinciple;
import com.chaitanya.gokhe.Gas.Utility.Service.model.Users;
import com.chaitanya.gokhe.Gas.Utility.Service.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GasUtilityUserDetailsService implements UserDetailsService {
    @Autowired
    UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        } else {
            return new UserPrinciple(user);
        }
    }
}
