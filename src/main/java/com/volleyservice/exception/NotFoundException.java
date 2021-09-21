package com.volleyservice.exception;

import org.springframework.data.crossstore.ChangeSetPersister;


public class NotFoundException extends RuntimeException{

    public static final String MISSING_PLAYER_ERR_MSG = "Player does not exist";
    public static final String MISSING_TEAM_ERR_MSG = "Team does not exist";
    public static final String MISSING_MATCH_ERR_MSG = "Match does not exist";
    public static final String MISSING_TOURNAMENT_ERR_MSG = "Tournament does not exist";
    public static final String MISSING_ROUND_ERR_MSG = "Round does not exist";

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException withPlayerNotFound(){
        return new NotFoundException(MISSING_PLAYER_ERR_MSG);
    }
    public static NotFoundException withTeamNotFound(){
        return new NotFoundException(MISSING_TEAM_ERR_MSG);
    }
    public static NotFoundException withMatchNotFound(){
        return new NotFoundException(MISSING_MATCH_ERR_MSG);
    }
    public static NotFoundException withTournamentNotFound(){
        return new NotFoundException(MISSING_TOURNAMENT_ERR_MSG);
    }
    public static NotFoundException withRoundNotFound(){
        return new NotFoundException(MISSING_ROUND_ERR_MSG);
    }

}
