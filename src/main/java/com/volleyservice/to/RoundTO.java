package com.volleyservice.to;

import com.volleyservice.enums.Phase;

import java.util.List;

public class RoundTO {
    private Long id;
    private Integer roundNumber;
    private Phase phase;
    private List<MatchTO> matches;
}
