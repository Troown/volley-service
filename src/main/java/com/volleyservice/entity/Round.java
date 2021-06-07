package com.volleyservice.entity;

import com.volleyservice.enums.Phase;

import java.util.List;

public class Round {
    private int roundNumber;
    private List<Match> matches;

    public Phase getPhase() {
        return Phase.FINAL;
    }
}
