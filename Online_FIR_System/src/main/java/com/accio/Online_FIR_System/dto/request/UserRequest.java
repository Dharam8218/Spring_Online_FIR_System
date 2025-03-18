package com.accio.Online_FIR_System.dto.request;

import com.accio.Online_FIR_System.Enum.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserRequest {

    private String userName;
    private String password;
    private String email;
    private long mobileNo;
    private Gender gender;
}
