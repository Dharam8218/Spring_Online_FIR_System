package com.accio.Online_FIR_System.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
public class PoliceStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int policeStationID;

    @Column(unique = true, nullable = false)
    private String uniqueStationCode;

    private String stationName;
    private String stationAddress;


    @OneToMany(mappedBy = "policeStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Officer> officers;

    @OneToMany(mappedBy = "policeStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complain> complaints;
}
