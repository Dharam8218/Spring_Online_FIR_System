package com.accio.Online_FIR_System.transformer;

import com.accio.Online_FIR_System.dto.request.UserRequest;
import com.accio.Online_FIR_System.dto.response.UserResponse;
import com.accio.Online_FIR_System.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTransformer {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static User userRequestToUser(UserRequest userRequest){
        return User
                .builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .gender(userRequest.getGender())
                .password(passwordEncoder().encode(userRequest.getPassword()))
                .mobileNo(userRequest.getMobileNo())
                .roles("ROLE_NORMAL")
                .build();
    }

    public static UserResponse userToUserResponse(User user){
        return  UserResponse
                .builder()
                .userName(user.getUserName())
                .mobileNo(user.getMobileNo())
                .email(user.getEmail())
                .build();
    }
}
