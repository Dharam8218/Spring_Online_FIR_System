package com.accio.Online_FIR_System.repository;

import com.accio.Online_FIR_System.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OfficerRepository extends JpaRepository<Officer,Integer> {

    Officer findByUserName(String userName);
    Optional<Officer> findByOfficerId(String officerId);
    Optional<Officer> findByPassword(String password);
}
