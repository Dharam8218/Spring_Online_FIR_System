package com.accio.Online_FIR_System.repository;

import com.accio.Online_FIR_System.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Integer> {
    Optional<PoliceStation> findByUniqueStationCode(String uniqueStationCode);
}
