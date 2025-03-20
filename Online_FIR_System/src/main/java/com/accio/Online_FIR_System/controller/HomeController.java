package com.accio.Online_FIR_System.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/register-complaint")
    public String registerComplaint() {
        return "register-complaint";
    }

    @GetMapping("/add-officer")
    public String addOfficer() {
        return "add-officer";
    }


    @GetMapping("/add-police-station")
    public String addPoliceStation() {
        return "add-police-station";
    }

    @GetMapping("/manage-complaint")
    public String manageComplaint() {
        return "manage-complaint";
    }

    @GetMapping("/officer-home")
    public String officerDashboard() {
        return "officer-home";
    }

    @GetMapping("/admin-home")
    public String adminDashboard() {
        return "admin-home";
    }

    @GetMapping("/update-complaint-status")
    public String updateComplainStatus() {
        return "update-complaint-status";
    }

    @GetMapping("/update-complain")
    public String updateComplainInfo() {
        return "update-complain";
    }

    @GetMapping("/complain-response")
    public String getComplainResponse(){
        return "complain-response";
    }
}
