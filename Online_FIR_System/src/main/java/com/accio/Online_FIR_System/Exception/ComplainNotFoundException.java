package com.accio.Online_FIR_System.Exception;

public class ComplainNotFoundException extends RuntimeException {


    public ComplainNotFoundException(String complainNotFound) {
        super(complainNotFound);
    }
}
