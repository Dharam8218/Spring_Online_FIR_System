package com.accio.Online_FIR_System.controller;

import com.accio.Online_FIR_System.dto.response.ComplainResponse;
import com.accio.Online_FIR_System.dto.response.ComplainSummary;
import com.accio.Online_FIR_System.entity.Complain;
import com.accio.Online_FIR_System.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/OnlineFirSystem")
public class ComplainController {

    @Autowired
    ComplainService complainService;

    @PostMapping("/register-complain")
    public ResponseEntity<String> registerComplain(@ModelAttribute Complain complain) {
        String uniqueID = complainService.registerComplain(complain);
        String message = "Complain registered successfully!! \n Your unique id is " + uniqueID + ". Keep it future reference or track your complaint.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("delete-complain/{uniqueID}")
    public ResponseEntity<String> deleteComplain(@PathVariable("uniqueID") String uniqueID) {
        boolean isDeleted = complainService.deleteComplain(uniqueID);
        String message = isDeleted ? "Complain deleted successfully!!" : "Complain not found with this uniqueID: " + uniqueID;
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update-complain")
    public ResponseEntity<String> updateComplain(@RequestParam String uniqueID, @ModelAttribute Complain complain) {
        boolean isUpdated = complainService.updateComplain(uniqueID, complain);
        String message = isUpdated ? "Complain updated successfully" : "Complain not found with this uniqueID: " + uniqueID;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/check-status/{uniqueID}")
    public ResponseEntity<String> checkStatus(@PathVariable("uniqueID") String uniqueID) {
        String status = complainService.checkStatus(uniqueID);
        String message = status != null ? status : "Complain not found with this uniqueID: " + uniqueID;
        return new ResponseEntity<>(message, HttpStatus.FOUND);
    }

    @GetMapping("/get-complain-details/{uniqueID}")
    public ResponseEntity<Object> getComplainDetails(@PathVariable("uniqueID") String uniqueID) {
        ComplainResponse complainResponse = complainService.getComplainDetails(uniqueID);
        return complainResponse != null ? new ResponseEntity<>(complainResponse, HttpStatus.FOUND)
                : new ResponseEntity<>("Complain not found with this uniqueID: " + uniqueID, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-all-complains")
    public ResponseEntity<List<ComplainResponse>> getAllComplains() {
        List<ComplainResponse> allComplains = complainService.getAllComplains();
        return new ResponseEntity<>(allComplains, HttpStatus.FOUND);
    }

    @PostMapping("update-Complain-Status/{complainID}/{newStatus}")
    public ResponseEntity<String> updateStatusOfComplain(@PathVariable("complainID") int complainID, @PathVariable("newStatus") String newStatus) {
        String message = complainService.updateStatusOfComplain(complainID, newStatus);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }


    @GetMapping("complain-summary")
    ResponseEntity<List<ComplainSummary>> getComplainSummary() {
        List<ComplainSummary> allComplains = complainService.getComplainSummary();
        return new ResponseEntity<>(allComplains, HttpStatus.FOUND);
    }

}
