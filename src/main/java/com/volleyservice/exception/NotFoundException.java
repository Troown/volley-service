package com.volleyservice.exception;

import org.springframework.data.crossstore.ChangeSetPersister;


public class NotFoundException extends RuntimeException{


    public static final String MISSING_TOURNAMENT_ERR_MSG = "Tournament does not exist";

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException withTournamentNotFound(){
        return new NotFoundException(MISSING_TOURNAMENT_ERR_MSG);
    }
}
