package com.accio.Online_FIR_System.repository;

import com.accio.Online_FIR_System.entity.Complain;
import com.accio.Online_FIR_System.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComplainRepository extends JpaRepository<Complain,Integer> {

    Optional<Complain> findByUniqueID(String uniqueID);
    List<Complain> findByPoliceStation(PoliceStation policeStation);

    Complain findByComplainID(int complainID);

    @Query("select c.statusOfComplaint, count(c) from Complain c where c.filedDate < :localDate group by c.statusOfComplaint")
    List<Object[]> getComplainSummary(@Param("localDate") LocalDate localDate);
}
