package com.accio.Online_FIR_System.repository;

import com.accio.Online_FIR_System.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Integer> {
    Optional<PoliceStation> findByUniqueStationCode(String uniqueStationCode);



    //@Query("SELECT p FROM PoliceStation p WHERE (SELECT COUNT(c) FROM Complain c WHERE c.policeStation = p) > 0")
    @Query("SELECT p FROM PoliceStation p WHERE SIZE(p.complaints) > 0")
    List<PoliceStation> findStationsWithMoreThan100Complaints();


}
