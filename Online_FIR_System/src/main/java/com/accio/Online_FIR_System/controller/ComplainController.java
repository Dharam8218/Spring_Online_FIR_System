package com.accio.Online_FIR_System.controller;

import com.accio.Online_FIR_System.Exception.ComplainNotFoundException;
import com.accio.Online_FIR_System.Exception.OfficerNotFoundException;
import com.accio.Online_FIR_System.dto.response.ComplainResponse;
import com.accio.Online_FIR_System.dto.response.ComplainSummary;
import com.accio.Online_FIR_System.entity.Complain;
import com.accio.Online_FIR_System.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/OnlineFirSystem")
public class ComplainController {

    @Autowired
    ComplainService complainService;

    /*
    @PostMapping("/register-complain")
    public ResponseEntity<String> registerComplain(@ModelAttribute Complain complain, @RequestParam("file") MultipartFile file) {
        String uniqueID = complainService.registerComplain(complain,file);
        String message = "Complain registered successfully!! \n Your unique id is " + uniqueID + ". Keep it future reference or track your complaint.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    */

    @PostMapping("/register-complain")
    public ModelAndView registerComplain(@ModelAttribute Complain complain, @RequestParam("file") MultipartFile file) {
        String uniqueID = complainService.registerComplain(complain, file);
        String message = "Complain registered successfully!! \n Your unique id is " + uniqueID + ". Keep it future reference or track your complaint.";
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("statusMessage", message);
        return modelAndView;
    }

    /*
    @DeleteMapping("delete-complain/{uniqueID}")
    public ResponseEntity<String> deleteComplain(@PathVariable("uniqueID") String uniqueID) {
        boolean isDeleted = complainService.deleteComplain(uniqueID);
        String message = isDeleted ? "Complain deleted successfully!!" : "Complain not found with this uniqueID: " + uniqueID;
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
     */

    @DeleteMapping("delete-complain/{uniqueID}")
    public ModelAndView deleteComplain(@PathVariable("uniqueID") String uniqueID) {
        boolean isDeleted = complainService.deleteComplain(uniqueID);
        String message = isDeleted ? "Complain deleted successfully!!" : "Complain not found with this uniqueID: " + uniqueID;
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("statusMessage", message);
        return modelAndView;
    }

    /*
    @PutMapping("/update-complain")
    public ResponseEntity<String> updateComplain(@RequestParam String uniqueID, @ModelAttribute Complain complain) {
        boolean isUpdated = complainService.updateComplain(uniqueID, complain);
        String message = isUpdated ? "Complain updated successfully" : "Complain not found with this uniqueID: " + uniqueID;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    */

    @PutMapping("/update-complain")
    public ModelAndView updateComplain(@RequestParam String uniqueID, @ModelAttribute Complain complain) {
        boolean isUpdated = complainService.updateComplain(uniqueID, complain);
        String message = isUpdated ? "Complain updated successfully" : "Complain not found with this uniqueID: " + uniqueID;
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("statusMessage", message);
        return modelAndView;
    }

    /*
    @GetMapping("/check-status/{uniqueID}")
    public ResponseEntity<String> checkStatus(@PathVariable("uniqueID") String uniqueID) {
        String status = complainService.checkStatus(uniqueID);
        String message = status != null ? status : "Complain not found with this uniqueID: " + uniqueID;
        return new ResponseEntity<>(message, HttpStatus.FOUND);
    }
     */

    @GetMapping("/check-status/{uniqueID}")
    public ModelAndView checkStatus(@PathVariable("uniqueID") String uniqueID) {
        String status = complainService.checkStatus(uniqueID);
        String message = status != null ? status : "Complain not found with this uniqueID: " + uniqueID;
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("statusMessage", message);
        return modelAndView;
    }

    @GetMapping("/get-complain-details/{uniqueID}")
    public ResponseEntity<Object> getComplainDetails(@PathVariable("uniqueID") String uniqueID) {
        ComplainResponse complainResponse = complainService.getComplainDetails(uniqueID);
        return complainResponse != null ? new ResponseEntity<>(complainResponse, HttpStatus.FOUND)
                : new ResponseEntity<>("Complain not found with this uniqueID: " + uniqueID, HttpStatus.NOT_FOUND);
    }


    /*
    @GetMapping("/get-all-complains")
    public ResponseEntity<List<ComplainResponse>> getAllComplains() {
        List<ComplainResponse> allComplains = complainService.getAllComplains();
        return new ResponseEntity<>(allComplains, HttpStatus.FOUND);
    }
     */
    @GetMapping("/get-all-complains")
    public ModelAndView getAllComplains() {
        try {
            List<ComplainResponse> allComplains = complainService.getAllComplains();
            ModelAndView modelAndView = new ModelAndView("complaint-list");
            modelAndView.addObject("complainList", allComplains);
            return modelAndView;
        } catch (OfficerNotFoundException | ComplainNotFoundException e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    /*
    @PostMapping("/update-Complain-Status/{complainID}/{newStatus}")
    public ResponseEntity<String> updateStatusOfComplain(@PathVariable("complainID") int complainID, @PathVariable("newStatus") String newStatus) {
        String message = complainService.updateStatusOfComplain(complainID, newStatus);
        return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
    }
     */

    @PostMapping("/update-Complain-Status/{complainID}/{newStatus}")
    public ModelAndView updateStatusOfComplain(@PathVariable("complainID") int complainID, @PathVariable("newStatus") String newStatus) {
        try {
            String message = complainService.updateStatusOfComplain(complainID, newStatus);
            ModelAndView modelAndView = new ModelAndView("message");
            modelAndView.addObject("statusMessage", message);
            return modelAndView;
        } catch (OfficerNotFoundException | ComplainNotFoundException e) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        }
    }

    /*
    @GetMapping("complain-summary")
    ResponseEntity<List<ComplainSummary>> getComplainSummary() {
        List<ComplainSummary> allComplains = complainService.getComplainSummary();
        return new ResponseEntity<>(allComplains, HttpStatus.FOUND);
    }
     */
    @GetMapping("/complain-summary")
    ModelAndView getComplainSummary() {
        List<ComplainSummary> allComplains = complainService.getComplainSummary();
        ModelAndView modelAndView = new ModelAndView("complain-summary");
        modelAndView.addObject("complaintSummary",allComplains);
        return modelAndView;
    }


    /*
     * Just to check complain response on frontend
     * */

    @GetMapping("/get-complain-response/{uniqueID}")
    public ModelAndView getComplainResponse(@PathVariable("uniqueID") String uniqueID) {
        return complainService.getComplainResponse(uniqueID);
    }

    @GetMapping("/view-evidence/{uniqueID}")
    public ModelAndView viewEvidence(@PathVariable("uniqueID") String uniqueID) {
        return complainService.viewEvidence(uniqueID);
    }

    @GetMapping("/view-evidence/uploads/{fileName}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        return complainService.serveFile(fileName);
    }


}
