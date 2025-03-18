package com.accio.Online_FIR_System.dto.request;


import com.accio.Online_FIR_System.Enum.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OfficerRequest {

    private String userName;
    private String email;
    private long mobileNo;
    private Gender gender;
    private String uniqueStationCode;
}
