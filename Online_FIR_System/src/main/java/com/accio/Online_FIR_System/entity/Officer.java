package com.accio.Online_FIR_System.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@Entity
@PrimaryKeyJoinColumn(name = "userID")
@DiscriminatorValue("OFFICER")
public class Officer extends User{

    // Unique officer identifier (different from the generated userID)

    @Column(name="officer_id", unique = true, nullable = false)
    private String officerId;

    // Many officers belong to one police station.
    @ManyToOne
    @JoinColumn(name = "policeStationID", nullable = false)
    private PoliceStation policeStation;
}
