package com.accio.Online_FIR_System.controller;


import com.accio.Online_FIR_System.dto.request.OfficerRequest;
import com.accio.Online_FIR_System.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/OnlineFirSystem")
public class OfficerController {

    @Autowired
    OfficerService officerService;

    /*
     @PostMapping("/add-officer")
     public ResponseEntity<List<String>> addOfficer(@ModelAttribute OfficerRequest officerRequest) {
         List<String> list = officerService.addOfficer(officerRequest);
         return new ResponseEntity<>(list, HttpStatus.CREATED);
     }
     */
    @PostMapping("/add-officer")
    public ModelAndView addOfficer(@ModelAttribute OfficerRequest officerRequest) {
        List<String> list = officerService.addOfficer(officerRequest);
        ModelAndView modelAndView = new ModelAndView("officer-message");
        modelAndView.addObject("list", list);
        return modelAndView;
    }


}

