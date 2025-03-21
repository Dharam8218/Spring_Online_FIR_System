package com.accio.Online_FIR_System.transformer;

import com.accio.Online_FIR_System.dto.request.OfficerRequest;
import com.accio.Online_FIR_System.entity.Officer;
import com.accio.Online_FIR_System.entity.PoliceStation;
import com.accio.Online_FIR_System.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficerTransformer {

    @Autowired
    PoliceStationRepository policeStationRepository;

    public Officer officerRequestToOfficer(OfficerRequest officerRequest) {


        PoliceStation station = policeStationRepository.findByUniqueStationCode(officerRequest.getUniqueStationCode())
                .orElseThrow(() -> new RuntimeException("Invalid Police Station Code"));

        return Officer
                .builder()
                .userName(officerRequest.getUserName())
                .email(officerRequest.getEmail())
                .mobileNo(officerRequest.getMobileNo())
                .gender(officerRequest.getGender())
                .policeStation(station)
                .roles("ROLE_OFFICER")
                .build();

    }


}
