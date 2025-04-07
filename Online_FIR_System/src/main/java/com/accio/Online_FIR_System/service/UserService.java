package com.accio.Online_FIR_System.service;

import com.accio.Online_FIR_System.dto.request.UserRequest;
import com.accio.Online_FIR_System.entity.Complain;
import com.accio.Online_FIR_System.entity.User;
import com.accio.Online_FIR_System.repository.UserRepository;
import com.accio.Online_FIR_System.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public void addUser(UserRequest userRequest) {
        User user = UserTransformer.userRequestToUser(userRequest);
        userRepository.save(user);
    }

}
