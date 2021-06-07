package com.volleyservice.entity;

import java.util.List;

public class Tournament {
    private List<BVBTeam> registeredTeams;
    private List<Round> round;

    private String tournamentName;
    private String city;


    public String getTournamentName() {
        return tournamentName;
    }
}
