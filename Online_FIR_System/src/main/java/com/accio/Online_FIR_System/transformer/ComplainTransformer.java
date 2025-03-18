package com.accio.Online_FIR_System.transformer;

import com.accio.Online_FIR_System.dto.response.ComplainResponse;
import com.accio.Online_FIR_System.dto.response.ComplainSummary;
import com.accio.Online_FIR_System.entity.Complain;

public class ComplainTransformer {

    public static ComplainResponse complainToComplainResponse(Complain complain) {
        return ComplainResponse.builder()
                .userResponse(UserTransformer.userToUserResponse(complain.getFiledBy()))
                .complainType(complain.getComplainType())
                .nearestPoliceStation(complain.getNearestPoliceStationCode())
                .placeOfIncident(complain.getPlaceOfIncident())
                .description(complain.getDescription())
                .statusOfComplaint(complain.getStatusOfComplaint())
                .build();

    }

    public static ComplainSummary ObjectArrayToComplainSummary(Object[] objects) {
        return ComplainSummary.builder()
                .count((long) objects[1])
                .status((String) objects[0])
                .build();
    }
}
