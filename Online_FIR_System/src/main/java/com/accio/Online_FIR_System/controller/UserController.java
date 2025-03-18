package com.accio.Online_FIR_System.controller;

import com.accio.Online_FIR_System.dto.request.UserRequest;
import com.accio.Online_FIR_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OnlineFirSystem")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@ModelAttribute UserRequest userRequest) {
        userService.addUser(userRequest);
        return new ResponseEntity<>("User added Successfully!", HttpStatus.CREATED);
    }


}
