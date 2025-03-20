package com.accio.Online_FIR_System.service;


import com.accio.Online_FIR_System.Exception.ComplainNotFoundException;
import com.accio.Online_FIR_System.Exception.OfficerNotFoundException;
import com.accio.Online_FIR_System.dto.response.ComplainResponse;
import com.accio.Online_FIR_System.dto.response.ComplainSummary;
import com.accio.Online_FIR_System.entity.Complain;
import com.accio.Online_FIR_System.entity.Officer;
import com.accio.Online_FIR_System.entity.User;
import com.accio.Online_FIR_System.repository.ComplainRepository;
import com.accio.Online_FIR_System.repository.OfficerRepository;
import com.accio.Online_FIR_System.repository.UserRepository;
import com.accio.Online_FIR_System.transformer.ComplainTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
public class ComplainService {

    @Autowired
    ComplainRepository complainRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OfficerRepository officerRepository;
    @Autowired
    FileService fileService;


    String uniqueId = generateUniqueId();

    public String registerComplain(Complain complain, MultipartFile file) {
        while (complainRepository.findByUniqueID(uniqueId).isPresent()) {
            uniqueId = generateUniqueId();
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedUser = userRepository.findByUserName(username);
        complain.setFiledBy(loggedUser);
        complain.setUniqueID(uniqueId);
        complain.setStatusOfComplaint("UNDER_REVIEW");
        complain.setFiledDate(LocalDate.now());
        if (!file.isEmpty()) {
            String filePath = fileService.uploadFile(file);
            complain.setFilePath(filePath);
        }
        complainRepository.save(complain);
        return uniqueId;
    }

    private String generateUniqueId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }

        return id.toString();
    }


    public boolean deleteComplain(String uniqueID) {
        Optional<Complain> optional = complainRepository.findByUniqueID(uniqueID);
        if (optional.isPresent()) {
            Complain complain = optional.get();
            complainRepository.deleteById(complain.getComplainID());
            return true;
        } else {
            return false;
        }
    }

    public boolean updateComplain(String uniqueID, Complain complain) {
        Optional<Complain> optional = complainRepository.findByUniqueID(uniqueID);
        if (optional.isPresent()) {
            Complain previusComplain = optional.get();
            previusComplain.setComplainType(complain.getComplainType());
            previusComplain.setNearestPoliceStationCode(complain.getNearestPoliceStationCode());
            previusComplain.setPlaceOfIncident(complain.getPlaceOfIncident());
            previusComplain.setDescription(complain.getDescription());
            previusComplain.setFiledDate(LocalDate.now());
            complainRepository.save(previusComplain);
            return true;
        } else {
            return false;
        }
    }

    public String checkStatus(String uniqueID) {
        Optional<Complain> optional = complainRepository.findByUniqueID(uniqueID);
        if (optional.isPresent()) {
            Complain complain = optional.get();
            return complain.getStatusOfComplaint();
        } else {
            return null;
        }
    }

    public ComplainResponse getComplainDetails(String uniqueID) {
        Optional<Complain> optional = complainRepository.findByUniqueID(uniqueID);
        if (optional.isPresent()) {
            Complain complain = optional.get();
            return ComplainTransformer.complainToComplainResponse(complain);
        } else {
            return null;
        }
    }

    public List<ComplainResponse> getAllComplains() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Officer officer = officerRepository.findByUserName(userName);
        if (officer == null) {
            throw new OfficerNotFoundException("Officer Not Found");
        }
        List<Complain> complains = complainRepository.findByPoliceStation(officer.getPoliceStation());
        if (complains.isEmpty()) {
            throw new ComplainNotFoundException("Complain Not Found");
        }
        List<ComplainResponse> complainResponses = new ArrayList<>();
        for (Complain complain : complains) {
            complainResponses.add(ComplainTransformer.complainToComplainResponse(complain));
        }
        return complainResponses;
    }

    public String updateStatusOfComplain(int complainID, String status) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Officer officer = officerRepository.findByUserName(userName);
        if (officer == null) {
            throw new OfficerNotFoundException("Officer Not Found");
        }
        Complain complain = complainRepository.findByComplainID(complainID);
        if (complain == null) {
            throw new ComplainNotFoundException("Complain Not Found");
        }
        if (!complain.getPoliceStation().equals(officer.getPoliceStation())) {
            return "You can't update the status of this complaint.";
        }
        complain.setStatusOfComplaint(status);
        complainRepository.save(complain);

        return "Complain Status Updated Successfully!";
    }

    public List<ComplainSummary> getComplainSummary() {
        List<Object[]> results = complainRepository.getComplainSummary(LocalDate.now());
        List<ComplainSummary> list = new ArrayList<>();
        if (!results.isEmpty()) {
            for (Object[] result : results) {
                list.add(ComplainTransformer.ObjectArrayToComplainSummary(result));
            }
        }

        return list;
    }




    public ModelAndView getComplainResponse(@PathVariable("uniqueID") String uniqueID) {
        Optional<Complain> optionalComplain = complainRepository.findByUniqueID(uniqueID);
        if (optionalComplain.isPresent()) {
            Complain complain = optionalComplain.get();
            ComplainResponse complainResponse = ComplainTransformer.complainToComplainResponse(complain);
            ModelAndView modelAndView = new ModelAndView("complain-response");
            modelAndView.addObject("complainResponse", complainResponse);
            return modelAndView;
        } else {
            return new ModelAndView("error-404");
        }

    }

    public ModelAndView viewEvidence(@PathVariable("uniqueID") String uniqueID) {
        Optional<Complain> optionalComplain = complainRepository.findByUniqueID(uniqueID);

        if (optionalComplain.isPresent()) {
            Complain complain = optionalComplain.get();
            String evidenceUrl = complain.getFilePath(); // Assuming evidence URL is stored

            ModelAndView modelAndView = new ModelAndView("view-evidence");
            modelAndView.addObject("evidenceUrl", evidenceUrl);
            return modelAndView;
        } else {
            return new ModelAndView("error-404");
        }
    }


    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        Path filePath = Paths.get("uploads").resolve(fileName);
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);

                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
