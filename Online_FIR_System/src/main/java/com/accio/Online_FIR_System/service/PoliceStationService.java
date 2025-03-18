package com.accio.Online_FIR_System.service;


import com.accio.Online_FIR_System.entity.PoliceStation;
import com.accio.Online_FIR_System.repository.PoliceStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliceStationService {

    @Autowired
    PoliceStationRepository policeStationRepository;

    public void addPoliceStation(PoliceStation policeStation) {
        policeStationRepository.save(policeStation);
    }
}
