package com.accio.Online_FIR_System.repository;

import com.accio.Online_FIR_System.entity.Complain;
import com.accio.Online_FIR_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);


}
