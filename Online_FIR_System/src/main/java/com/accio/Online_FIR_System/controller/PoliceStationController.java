package com.accio.Online_FIR_System.controller;


import com.accio.Online_FIR_System.entity.PoliceStation;
import com.accio.Online_FIR_System.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/OnlineFirSystem")
public class PoliceStationController {

    @Autowired
    PoliceStationService policeStationService;

    /*
    @PostMapping("/add-police-station")
    public ResponseEntity<String> addPoliceStation(@ModelAttribute PoliceStation policeStation) {
        policeStationService.addPoliceStation(policeStation);
        return new ResponseEntity<>("Police Station added Successfully!!", HttpStatus.CREATED);
    }
     */

    @PostMapping("/add-police-station")
    public ModelAndView addPoliceStation(@ModelAttribute PoliceStation policeStation) {
        policeStationService.addPoliceStation(policeStation);
        String message = "Police Station added Successfully!!";
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("statusMessage", message);
        return modelAndView;
    }

    @GetMapping("/highComplaints")
    public List<PoliceStation> getStationsWithMoreThan100Complaints() {
        return policeStationService.getStationsWithMoreThan100Complaints();
    }

}
