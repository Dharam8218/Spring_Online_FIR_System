package com.accio.Online_FIR_System.Exception;

public class OfficerNotFoundException extends RuntimeException{


    public OfficerNotFoundException(String officerNotFound) {
        super(officerNotFound);
    }
}
