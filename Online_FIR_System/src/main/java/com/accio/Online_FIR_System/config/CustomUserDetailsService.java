package com.accio.Online_FIR_System.config;

import com.accio.Online_FIR_System.entity.User;
import com.accio.Online_FIR_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if(user==null){
            throw  new RuntimeException("User Not Found");
        }

        return new CustomUserDetails(user);

    }
}
