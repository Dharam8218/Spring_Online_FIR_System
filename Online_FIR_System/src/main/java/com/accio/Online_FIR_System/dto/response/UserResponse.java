package com.accio.Online_FIR_System.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserResponse {

    private String userName;
    private String email;
    private long mobileNo;
}
