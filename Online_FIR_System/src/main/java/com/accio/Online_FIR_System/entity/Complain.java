package com.accio.Online_FIR_System.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complainID;

    @Column(unique = true, nullable = false)
    private String uniqueID;

    @ManyToOne
    private User filedBy;

    // This field stores the police station code
    @Column(name = "nearestPoliceStationCode", nullable = false)
    private String nearestPoliceStationCode;

    // This maps the code to the actual PoliceStation entity using its uniqueStationCode
    @ManyToOne
    @JoinColumn(name = "nearestPoliceStationCode", referencedColumnName = "uniqueStationCode", insertable = false, updatable = false)
    private PoliceStation policeStation;

    private String complainType;
    private String placeOfIncident;
    private String description;
    private String statusOfComplaint;


    private LocalDate filedDate;




}
