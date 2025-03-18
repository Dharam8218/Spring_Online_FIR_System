package com.accio.Online_FIR_System.dto.response;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ComplainSummary {

    private String status;
    private long count;
}
