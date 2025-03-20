package com.accio.Online_FIR_System.dto.response;

import com.accio.Online_FIR_System.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ComplainResponse {

    private UserResponse userResponse;
    private int complainID;
    private String nearestPoliceStation;
    private String complainType;
    private String placeOfIncident;
    private String description;
    private String statusOfComplaint;
    private String evidence;
}
